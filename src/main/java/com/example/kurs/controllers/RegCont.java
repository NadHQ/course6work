package com.example.kurs.controllers;


import com.example.kurs.models.Users;
import com.example.kurs.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegCont {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/reg")
    public String Reg() {
        return "reg";
    }

    @PostMapping("/reg")
    public String AddUser(@RequestParam String username, @RequestParam String password) {
        usersRepo.save(new Users(username, password));
        return "redirect:/login";
    }
}
