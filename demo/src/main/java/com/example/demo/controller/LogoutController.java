package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout() {
        return "redirect:https://dev-5xq0oa3mwa2eiavj.us.auth0.com/v2/logout?client_id=OFwkKXTBj2SU9ONp9R3IzD90bz21lmlC&returnTo=http://localhost:8080";
    }
    }

