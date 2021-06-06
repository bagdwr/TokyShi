package com.example.SushiStore.Controllers;

import com.example.SushiStore.Entity.*;
import com.example.SushiStore.Service.FoodService;
import com.example.SushiStore.Service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Value("${file.picture.defaultPicture}")
    private String defaultPicture;

    @Value("${file.picture.viewPath}")
    private String viewPath;

    @Value("${file.picture.uploadPath}")
    private String uploadPath;

    private Basket basket=new Basket();

    @GetMapping(value = "/")
    public String index(){
        return "userPages/user_sushi";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/register")
    public String Register(){
        return "register";
    }

    @GetMapping(value = "/Error")
    public String errorPage(Model model){
        return "error";
    }

    @PostMapping(value = "/signup")
    public String toRegister(@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "fullname") String fullname,
                             @RequestParam(name = "phoneNumber") String phoneNumber){
         Users user=new Users();
         user.setFullname(fullname);
         user.setEmail(email);
         user.setPassword(password);
         user.setPhone(phoneNumber);
         if (userService.createUser(user)!=null){
             return "redirect:/register?success";
         }
         return "redirect:/register?error";
    }

    @GetMapping(value = "/drinks")
    public String showDrinks(Model model){
        model.addAttribute("drinks",foodService.getAllDrinksSortedByName());
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "userPages/user_drinks";
    }

    @GetMapping(value = "/sushi")
    public String showSushi(Model model){
        model.addAttribute("sushi",foodService.getAllSushiSortedByPrice());
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "userPages/user_sushi";
    }

    @GetMapping(value = "/rolls")
    public String showRolls(Model model){
        model.addAttribute("rolls",foodService.getAllRollsSortedByPrice());
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "userPages/user_rolls";
    }

    @GetMapping(value = "/sets")
    public String showSets(Model model){
        model.addAttribute("sets",foodService.getAllSetsSortedByPrice());
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "userPages/user_sets";
    }

    //region addToBasket
    @PostMapping(value = "/addDrinkToBasket")
    public String addDrink(@RequestParam(name = "item_drink")Long drinkId){
        if (drinkId!=null){
            Drinks drink=foodService.getOneDrink(drinkId);
            Map<Drinks,Integer> basketDrinks=basket.getDrinks();
            if (!basketDrinks.containsKey(drink)){
                basketDrinks.put(drink,1);
                return "redirect:/drinks";
            }
            basketDrinks.put(drink,basketDrinks.get(drink)+1);
            basket.setDrinks(basketDrinks);
            return "redirect:/drinks";
        }
        return "redirect://";
    }
    @PostMapping(value = "/addSushiToBasket")
    public String addSushi(@RequestParam(name = "item_sushi")Long sushiId){
        if (sushiId!=null){
            Sushi sushi=foodService.getOneSushi(sushiId);
            Map<Sushi,Integer>basketSushi=basket.getSushi();
            if (!basketSushi.containsKey(sushi)){
                basketSushi.put(sushi,1);
                return "redirect:/sushi";
            }
            basketSushi.put(sushi,basketSushi.get(sushi)+1);
            return "redirect:/sushi";
        }
        return "redirect://";
    }
    @PostMapping(value = "/addRollToBasket")
    public String addRoll(@RequestParam(name = "item_roll")Long rollId){
        if (rollId!=null){
            Rolls rolls=foodService.getOneRolls(rollId);
            Map<Rolls,Integer>basketRolls=basket.getRolls();
            if (!basketRolls.containsKey(rolls)){
                basketRolls.put(rolls,1);
                return "redirect:/rolls";
            }
            basketRolls.put(rolls,basketRolls.get(rolls)+1);
            return "redirect:/rolls";
        }
        return "redirect://";
    }
    @PostMapping(value = "/addSetToBasket")
    public String addSet(@RequestParam(name = "item_set")Long setId){
        if (setId!=null){
            Sets set=foodService.getOneSet(setId);
            Map<Sets,Integer>basketSets=basket.getSets();
            if (!basketSets.containsKey(set)){
                basketSets.put(set,1);
                return "redirect:/sets";
            }
            basketSets.put(set,basketSets.get(set)+1);
            return "redirect:/sets";
        }
        return "redirect://";
    }

    //endregion

    @GetMapping(value = "/bonus")
    public String showBonusPage(Model model){
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "footerPages/Bonus";
    }
    @GetMapping(value = "/deliver")
    public String showDeliverPage(Model model){
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "footerPages/Deliver";
    }
    @GetMapping(value = "/contacts")
    public String showContactsPage(Model model){
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "footerPages/Contacts";
    }

    @GetMapping(value = "/basket")
    public String showBasket(Model model){
        model.addAttribute("basketItems",basket);
        model.addAttribute("basketAmount",basket.getOverallAmount());
        return "Basket";
    }
    @GetMapping(value = "/photo/{url}", produces ={MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody byte[] viewPicture(
            @PathVariable(name = "url")String path
    ) throws IOException {
      String pictureUrl=viewPath+defaultPicture+".jpeg";
      if (path!=null && !path.isEmpty() && !path.equals("null")){
          pictureUrl=viewPath+path+".jpeg";
      }
        InputStream inputStream;
      try {
          ClassPathResource pathResource=new ClassPathResource(pictureUrl);
          inputStream=pathResource.getInputStream();
      }catch (Exception ex){
          ex.printStackTrace();
          ClassPathResource pathResource=new ClassPathResource(viewPath+defaultPicture);
          inputStream=pathResource.getInputStream();
      }
      return IOUtils.toByteArray(inputStream);
    }
}
