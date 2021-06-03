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
import java.util.ArrayList;

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

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/register")
    public String Register(){
        return "register";
    }

    @GetMapping(value = "/Error")
    public String errorPage(){
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
             return "redixrect:/register?success";
         }
         return "redirect:/register?error";
    }

    @GetMapping(value = "/drinks")
    public String showDrinks(Model model){
        model.addAttribute("drinks",foodService.getAllDrinksSortedByName());
        return "userPages/user_drinks";
    }

    @GetMapping(value = "/sushi")
    public String showSushi(Model model){
        model.addAttribute("sushi",foodService.getAllSushiSortedByPrice());
        return "userPages/user_sushi";
    }

    @GetMapping(value = "/rolls")
    public String showRolls(Model model){
        model.addAttribute("rolls",foodService.getAllRollsSortedByPrice());
        return "userPages/user_rolls";
    }

    @GetMapping(value = "/sets")
    public String showSets(Model model){
        model.addAttribute("sets",foodService.getAllSetsSortedByPrice());
        return "userPages/user_sets";
    }

    @PostMapping(value = "/addDrinkToBasket")
    public String addToBasket(
            @RequestParam(name = "item_drink")Long drinkId,
            @RequestParam(name = "item_sushi") Long sushiId,
            @RequestParam(name = "item_set") Long setId,
            @RequestParam(name = "item_roll")Long rollId
            ){
        if (drinkId!=null){
            Drinks drink=foodService.getOneDrink(drinkId);
            return "redirect:/drinks";
        }
        if (sushiId!=null){
            Sushi sushi=foodService.getOneSushi(sushiId);
            return "redirect:/sushi";
        }
        if (setId!=null){
            Sets set=foodService.getOneSet(setId);
            return "redirect:/sets";
        }
        if (rollId!=null){
            Rolls rolls=foodService.getOneRolls(rollId);
            return "redirect:/rolls";
        }
        return "redirect:/";
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
