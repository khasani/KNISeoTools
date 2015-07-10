package com.kniapps.seotools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    
    @RequestMapping("/")
    public String showDashboard(){
        
        //return "keyword-ranking";
        return "home";
    }
    
    @RequestMapping("/login")
    public String showLogin(){
        
        //return "keyword-ranking";
        return "login";
    }
    
}
