package com.kniapps.seotools;

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
    
    @RequestMapping("/login_")
    public String showLogin_(){
        
        //return "keyword-ranking";
        return "login_";
    }
    
}
