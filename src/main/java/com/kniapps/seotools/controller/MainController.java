package com.kniapps.seotools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    
    @RequestMapping({"/","/home"})
    public String showHome(){
        
        //return "keyword-ranking";
        return "home";
    }
    
    @RequestMapping("/login")
    public String showLogin(){
        
        //return "keyword-ranking";
        return "login";
    }
    
    
}
