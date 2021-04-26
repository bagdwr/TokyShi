package com.example.SushiStore.Controllers;

import com.example.SushiStore.Entity.Roles;
import com.example.SushiStore.Entity.Users;
import com.example.SushiStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

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
             return "redirect:/register?success";
         }
         return "redirect:/register?error";

    }
}
