package com.example.SushiStore.Controllers;

import com.example.SushiStore.Entity.*;
import com.example.SushiStore.Repositories.RoleRepository;
import com.example.SushiStore.Service.FoodService;
import com.example.SushiStore.Service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasAnyRole('ROLE_MODERATOR','ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private FoodService foodService;

    @Value("${file.picture.defaultPicture}")
    private String defaultPicture;

    @Value("${file.picture.viewPath}")
    private String viewPath;

    @Value("${file.picture.uploadPath}")
    private String uploadPath;

    @GetMapping("")
    public String adminka(){
         return "adminPages/adminIndex";
     }

    @GetMapping(value = "/showusers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "adminPages/adminUsers";
    }

    @GetMapping(value = "/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminEdit(
            @PathVariable(name = "id") Long id,
            Model model) {
        Users user= userService.getUserById(id);
        if (user!=null){
            List<Roles>forAssign=userService.getAllRoles();
            List<Roles> roles=user.getRoles();
            forAssign.removeAll(roles);

            List<Roles>forUnassign=user.getRoles();
            Roles role= userService.findRole("ROLE_USER");
            forUnassign.remove(role);
            model.addAttribute("user",user);
            model.addAttribute("assignRoles",forAssign);
            model.addAttribute("unassignRoles",forUnassign);
            return "adminPages/adminEdit";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping(value = "/deleteUser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(
            Model model,
            @PathVariable(name = "id") Long id
    ){

            if (userService.deleteUser(id)){
                return "redirect:/admin/showusers";
            }else {
                return "redirect:/admin/edit/"+id+"?error";
            }
    }

    @PostMapping(value = "/saveUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveUser(
            @RequestParam(name = "id")Long id,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "fullname") String fullname,
            @RequestParam(name = "phoneNumber") String phoneNumber
    ){
        Users user= userService.getUserById(id);
        if (user!=null){
            user.setEmail(email);
            user.setFullname(fullname);
            user.setPhone(phoneNumber);
            userService.saveUser(user);
            return "redirect:/admin/showusers";
        }else{
            return "redirect:/admin/saveUser/"+id+"?error";
        }
    }

    @PostMapping(value = "/assignRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String assign(
            @RequestParam(name = "user_id")Long id,
            @RequestParam(name="roleName")String Rolename
    ){
        Roles role= userService.findRole(Rolename);
        if (role!=null){
            Users user= userService.getUserById(id);
            if (user!=null){
                List<Roles>roles=user.getRoles();
                roles.add(role);
                user.setRoles(roles);
                userService.saveUser(user);
                return "redirect:/admin/edit/"+id+"#rolesDiv";
            }
        }
        return "redirect:/admin/edit/"+id+"?assignRoleError";
    }

    @PostMapping(value = "/unassignRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unassign(
            @RequestParam(name = "user_id")Long id,
            @RequestParam(name="roleName")String Rolename
    ){
        Roles role= userService.findRole(Rolename);
        if (role!=null){
            Users user= userService.getUserById(id);
            if (user!=null){
                List<Roles>roles=user.getRoles();
                roles.remove(role);
                user.setRoles(roles);
                userService.saveUser(user);
                return "redirect:/admin/edit/"+id+"#rolesDiv";
            }

        }
        return "redirect:/admin/edit/"+id+"?unassignRoleError";
    }
    //region ingredients
    @GetMapping(value = "/ingredients")
    public String ingredientsTable(Model model){
        model.addAttribute("ingredients",foodService.getAllIngredients());
        return "foods/ingredientList";
    }

    @GetMapping(value = "/ingredientEdit/{idshka}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String ingredientEdit(
            @PathVariable(name = "idshka") Long id,
            Model model
    ){
        Ingredients ingredient=foodService.getOneIngredientById(id);
        if (ingredient!=null){
            model.addAttribute("ingredient",ingredient);
            return "foods/editIngredient";
        }
        return "redirect:/admin/ingredients";
    }

    @PostMapping(value = "/saveIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveIngredient(
            @RequestParam(name = "id")Long id,
            @RequestParam(name = "ingName")String name
    ){
        Ingredients ingredients= foodService.getOneIngredientById(id);
        if (ingredients!=null){
            ingredients.setIngredientName(name);
            if (!ingredients.getIngredientName().trim().isEmpty()){
                if(foodService.saveIngredient(ingredients)!=null){
                    return "redirect:/admin/ingredients";
                }
            }
        }
        return "redirect:/admin/ingredientEdit/"+id+"?error";
    }

    @GetMapping(value = "/deleteIngredient/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteIngredient(
            @PathVariable(name = "id")Long id
    ){
            Ingredients ingredients=foodService.getOneIngredientById(id);
            if (ingredients!=null){
                foodService.deleteIngredient(ingredients);
            }
            return "redirect:/admin/ingredients";
    }

    @GetMapping(value = "/createIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createForm(){
        return "foods/createIngredientForm";
    }

    @PostMapping(value = "/addIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createIng(
            @RequestParam(name = "ingName")String name
    ){
        Ingredients ingredient=new Ingredients();
        ingredient.setIngredientName(name);
        if (!ingredient.getIngredientName().trim().isEmpty()){
            foodService.createIngredient(ingredient);
        }

        return "redirect:/admin/ingredients";
    }
    //endregion

    //region drink
    @GetMapping(value = "/drinksList")
    public String retunDrinksList(Model model){
        ArrayList<Drinks>drinks= foodService.getAllDrinks();
        model.addAttribute("drinks",drinks);
        return "foods/drinksList";
    }

    @GetMapping(value = "/createDrink")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreateDrink(){
        return "foods/createDrinkForm";
    }

    @GetMapping(value = "/drinkedit/{url}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editDrink(
            @PathVariable(name = "url") Long id,
            Model model
    ){
        Drinks drink=foodService.getOneDrink(id);
        if (drink!=null){
            model.addAttribute("drink",drink);
            return "foods/editDrink";
        }
        return "redirect:/admin/drinksList";
    }
    @PostMapping(value = "/addDrink")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createDrink(
            @RequestParam(name = "drink_name") String name,
            @RequestParam(name = "drink_price") Integer price,
            @RequestParam(name = "drink_picture")MultipartFile file
            ){
        if (name!=null && price!=null){
            Drinks drink=new Drinks();
            drink.setName(name);
            drink.setPrice(price);
            try {
                if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/jpg")){
                    int number= (int) (Math.random()*100000+1);
                    String pictureName= DigestUtils.sha1Hex("PICTURE"+drink.toString()+number+"_image");
                    byte[] bytes=file.getBytes();
                    Path path= Paths.get(uploadPath+pictureName+".jpeg");
                    Files.write(path,bytes);
                    drink.setDrink_picture(pictureName);
                    if(foodService.createDrink(drink)!=null)
                        return "redirect:/admin/drinksList";
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
        return "redirect:/admin/createDrink?error";
    }

    @PostMapping(value = "/saveDrinkPicture")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveDrinkPicture(
            @RequestParam(name = "drink_picture")MultipartFile file,
            @RequestParam(name = "drink_id") Long id
    ){
        Drinks drink=foodService.getOneDrink(id);
        if (drink!=null){
            try {
                  if (file.getContentType().equals("image/jpeg")|| file.getContentType().equals("image/png") || file.getContentType().equals("image/jpg")){
                          int number= (int) (Math.random()*100000+1);
                          String pictureName=DigestUtils.sha1Hex("PICTURE"+drink.toString()+number+"_image");
                          byte[]bytes=file.getBytes();
                          Path path=Paths.get(uploadPath+pictureName+".jpeg");
                          Files.write(path,bytes);
                          drink.setDrink_picture(pictureName);
                           if (foodService.saveDrink(drink)!=null){
                                return "redirect:/admin/drinkedit/"+id;
                              }
                   }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "redirect:/admin/drinkEdit/"+id+"?imageCreationError";
    }

    @PostMapping(value = "/saveDrink{url}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveDrink(
            @RequestParam(name = "drink_name") String name,
            @RequestParam(name = "drink_price") Integer price,
            @RequestParam(name = "drink_id") Long id
    ){
        Drinks drink=foodService.getOneDrink(id);
        if (drink!=null){
            if (name!=null && price!=null){
                drink.setPrice(price);
                drink.setName(name);
                if (foodService.saveDrink(drink)!=null){
                    return "redirect:/admin/drinksList";
                }
            }
        }
        return "redirect:/admin/drinkEdit/"+id+"?error";
    }

    @GetMapping(value = "/deleteDrink/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteDrink(
            @PathVariable(name = "id")Long id
    ){
        Drinks drink=foodService.getOneDrink(id);
        if (drink!=null){
            foodService.deleteDrink(drink);
        }
        return "redirect:/admin/drinksList";
    }
    //endregion
    @GetMapping(value = "/viewphoto/{url}",produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] viewDrinkPicture(
            @PathVariable(name = "url") String url
    )throws IOException {
             String pictureUrl=viewPath+defaultPicture+".jpeg";
             if (url!=null && !url.equals("null")){
                 pictureUrl=viewPath+url+".jpeg";
             }
             InputStream in;
             try {
                 ClassPathResource pathResource=new ClassPathResource(pictureUrl);
                 in=pathResource.getInputStream();
             }catch (Exception ex){
                 ClassPathResource pathResource=new ClassPathResource(viewPath+defaultPicture);
                 in=pathResource.getInputStream();
                 ex.printStackTrace();
             }
             return IOUtils.toByteArray(in);
    }

    //region Sushi
    @GetMapping(value = "/adminSushi")
    public String showListSushi(Model model){
           model.addAttribute("sushi",foodService.getAllSushi());
           return "foods/sushiList";
    }

    @GetMapping(value = "/createSushi")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createSushi(Model model){

        return "foods/createSushiForm";
    }

    @PostMapping(value = "/addSushi")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addSushi(
            @RequestParam(name = "sushi_name")String name,
            @RequestParam(name = "sushi_price")Integer price,
            @RequestParam(name = "sushi_picture")MultipartFile file
    ){
       try {
           if (name!=null && price!=null && file!=null){
               Sushi sushi=new Sushi();
               sushi.setName(name);
               sushi.setPrice(price);
               if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png")){
                   int number= (int) (Math.random()*100000+1);
                   String pictureName=DigestUtils.sha1Hex("PICTURE"+sushi.toString()+number+"_image");
                   byte[]bytes=file.getBytes();
                   Path path=Paths.get(uploadPath+pictureName+".jpeg");
                   Files.write(path,bytes);
                   sushi.setSushi_picture(pictureName);
                   if (foodService.createSushi(sushi)!=null){
                       return "redirect:/admin/adminSushi";
                   }

               }
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
        return "redirect:/createSushi?error";
    }

    @GetMapping(value = "/sushiEdit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveSushi(
            Model model,
            @PathVariable(name = "id")Long id
    ){
        Sushi sushi=foodService.getOneSushi(id);
        if (sushi!=null){
            model.addAttribute("sushi",sushi);
            model.addAttribute("unassignIg",sushi.getIngredients());
            ArrayList<Ingredients>assignIng=foodService.getAllIngredients();
            assignIng.removeAll(sushi.getIngredients());
            model.addAttribute("assignIg",assignIng);
        }
        return "foods/sushiEdit";
    }

    @PostMapping(value = "/saveSushiPicture")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveSushiPicture(
            @RequestParam(name = "sushi_picture")MultipartFile file,
            @RequestParam(name = "sushi_id")Long id
    ){
        try {
            Sushi sushi= foodService.getOneSushi(id);
            if (sushi!=null){
                if (file!=null && (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png"))){
                    int number= (int) (Math.random()*100000+1);
                    String pictureName=DigestUtils.sha1Hex("PICTURE"+sushi.toString()+number+"_image");
                    byte[]bytes=file.getBytes();
                    Path path=Paths.get(uploadPath+pictureName+".jpeg");
                    Files.write(path,bytes);
                    sushi.setSushi_picture(pictureName);
                    if (foodService.saveSushi(sushi)!=null){
                        return "redirect:/admin/sushiEdit/"+id;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/adminSushi";
    }

    @PostMapping(value = "/saveSushi")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveSushi(
            @RequestParam(name = "sushi_id")Long id,
            @RequestParam(name = "sushi_name")String name,
            @RequestParam(name = "sushi_price")Integer price
    ){
        Sushi sushi= foodService.getOneSushi(id);
        if (sushi!=null){
            if (name!=null && price!=null){
                sushi.setPrice(price);
                sushi.setName(name);
                if (foodService.saveSushi(sushi)!=null){
                    return "redirect:/admin/adminSushi";
                }
            }
        }
        return "redirect:/admin/adminSushi";
    }

    @PostMapping(value = "/unassignSushiIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unassignSushiIng(
            @RequestParam(name = "sushi_id")Long id,
            @RequestParam(name = "ingredient_id")Long ing_id
    ){
        Ingredients ingredient=foodService.getOneIngredientById(ing_id);
        if (ingredient!=null){
            Sushi sushi= foodService.getOneSushi(id);
            if (sushi!=null){
                List<Ingredients>ingredients=sushi.getIngredients();
                ingredients.remove(ingredient);
                sushi.setIngredients(ingredients);
                if (foodService.saveSushi(sushi)!=null){
                    return "redirect:/admin/sushiEdit/"+id+"#rolesDiv";
                }
            }
        }
        return "redirect:/admin/adminSushi";
    }

    @PostMapping(value = "/assignSushiIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String assignSushiIng(
            @RequestParam(name = "sushi_id")Long id,
            @RequestParam(name = "ingredient_id")Long ing_id
    ){
        Ingredients ingredient=foodService.getOneIngredientById(ing_id);
        if (ingredient!=null){
            Sushi sushi= foodService.getOneSushi(id);
            if (sushi!=null){
                List<Ingredients>ingredients=sushi.getIngredients();
                ingredients.add(ingredient);
                sushi.setIngredients(ingredients);
                if (foodService.saveSushi(sushi)!=null){
                    return "redirect:/admin/sushiEdit/"+id+"#rolesDiv";
                }
            }
        }
        return "redirect:/admin/adminSushi";
    }

    @GetMapping(value = "/deleteSushi/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteSushi(
            @PathVariable(name = "id")Long id
    ){
        Sushi sushi= foodService.getOneSushi(id);
        if (sushi!=null){
            foodService.deleteSushi(sushi);
        }
        return "redirect:/admin/adminSushi";
    }
    //endregion

    //region rolls

    @GetMapping(value = "/adminRolls")
    public String showRollsList(Model model){
        model.addAttribute("rolls",foodService.getAllRolls());
        return "foods/rollsList";
    }

    @GetMapping(value = "/createRoll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreateForm(){
        return "foods/createRollsForm";
    }

    @PostMapping(value = "/addRoll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createRoll(
            @RequestParam(name = "roll_name")String name,
            @RequestParam(name = "roll_amount")Integer amount,
            @RequestParam(name = "roll_price")Integer price,
            @RequestParam(name="roll_picture")MultipartFile file
    ){
        try {
            if (name!=null && amount!=null && price!=null && file!=null){
                Rolls roll=new Rolls();
                roll.setName(name);
                roll.setAmount(amount);
                roll.setPrice(price);
                if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png")) {
                    int number= (int) (Math.random()*100000+1);
                    String pictureName=DigestUtils.sha1Hex("PICTURE"+roll.toString()+number+"_image");
                    byte[]bytes=file.getBytes();
                    Path path=Paths.get(uploadPath+pictureName+".jpeg");
                    Files.write(path,bytes);
                    roll.setUrl(pictureName);
                    if (foodService.createRolls(roll)!=null){
                        return "redirect:/admin/adminRolls";
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/createRoll?error";
    }

    @GetMapping(value = "/rollEdit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String ShowEditForm(
            Model model,
            @PathVariable(name = "id")Long id
    ){
        Rolls roll= foodService.getOneRolls(id);
        if (roll!=null){
            model.addAttribute("roll",roll);

            List<Ingredients>ingredients=roll.getIngredients();
            model.addAttribute("unassignIng",ingredients);

            List<Ingredients>ing=foodService.getAllIngredients();
            ing.removeAll(ingredients);
            model.addAttribute("assignIng",ing);
            return "foods/rollsEdit";
        }
        return "redirect:/admin/adminRolls";
    }

    @PostMapping(value = "/saveRollPicture")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveRollPict(
            @RequestParam(name = "roll_id")Long id,
            @RequestParam(name = "roll_picture")MultipartFile file
    ){
        try {
            Rolls roll= foodService.getOneRolls(id);
            if (roll!=null){
                if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png")) {
                    int number= (int) (Math.random()*100000+1);
                    String pictureName=DigestUtils.sha1Hex("PICTURE"+roll.toString()+number+"_image");
                    byte[]bytes=file.getBytes();
                    Path path=Paths.get(uploadPath+pictureName+".jpeg");
                    Files.write(path,bytes);
                    roll.setUrl(pictureName);
                    if (foodService.saveRolls(roll)!=null){
                        return "redirect:/admin/rollEdit/"+id;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/admin/adminRolls";
    }

    @PostMapping(value = "/saveRoll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveRoll(
            @RequestParam(name = "roll_id")Long id,
            @RequestParam(name = "roll_name")String name,
            @RequestParam(name = "roll_amount")Integer amount,
            @RequestParam(name = "roll_price")Integer price
    ){
        if (name!=null && amount!=null && price!=null){
            Rolls roll= foodService.getOneRolls(id);
            if (roll!=null){
                roll.setPrice(price);
                roll.setName(name);
                roll.setAmount(amount);
                if (foodService.saveRolls(roll)!=null){
                    return "redirect:/admin/adminRolls";
                }
            }
        }
        return "redirect:/admin/rollEdit/"+id+"?error";
    }

    @PostMapping(value = "/unassignRollIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String unassignIng(
            @RequestParam(name = "ingredient_id")Long ing_id,
            @RequestParam(name = "roll_id")Long id
    ){
        Ingredients ingredient= foodService.getOneIngredientById(ing_id);
        Rolls roll= foodService.getOneRolls(id);
        if (roll!=null && ingredient!=null){
            List<Ingredients>ingredients=roll.getIngredients();
            ingredients.remove(ingredient);
            roll.setIngredients(ingredients);
            if(foodService.saveRolls(roll)!=null){
                return "redirect:/admin/rollEdit/"+id+"#rolesDiv";
            }
        }
        return "redirect:/admin/adminRolls";
    }

    @PostMapping(value = "/assignRollIngredient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String assignIng(
            @RequestParam(name = "ingredient_id")Long ing_id,
            @RequestParam(name = "roll_id")Long id
    ){
        Ingredients ingredient= foodService.getOneIngredientById(ing_id);
        Rolls roll= foodService.getOneRolls(id);
        if (roll!=null && ingredient!=null){
            List<Ingredients>ingredients=roll.getIngredients();
            ingredients.add(ingredient);
            roll.setIngredients(ingredients);
            if (foodService.saveRolls(roll)!=null){
                return "redirect:/admin/rollEdit/"+id+"#rolesDiv";
            }
        }
        return "redirect:/admin/adminRolls";
    }

    @GetMapping(value = "/deleteRoll/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRoll(
            @PathVariable(name = "id")Long id
    ){
        Rolls roll= foodService.getOneRolls(id);
        if (roll!=null){
            foodService.deleteRoll(roll);
        }
        return "redirect:/admin/adminRolls";
    }
    //endregion

    //region sets
    @GetMapping(value = "/adminSets")
    public String showList(Model model){
        model.addAttribute("sets",foodService.getAllSets());
        return "foods/setsList";
    }
    //endregion
}
