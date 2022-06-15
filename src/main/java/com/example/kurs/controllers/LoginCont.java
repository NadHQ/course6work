package com.example.kurs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;

@Controller
public class LoginCont {

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

}
