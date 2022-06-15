package com.example.kurs.controllers;

import com.example.kurs.repos.AssociationRepo;
import com.example.kurs.repos.ProfileRepo;
import com.example.kurs.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPanel {
    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    AssociationRepo associationRepo;

    @GetMapping("/admin/panel")
    public String Table(Model model) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() + " " +
                profileRepo.getById(UserId).getThirdname();
        model.addAttribute("role", globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(UserId).getAvatar());
//        model.addAttribute("users", profileRepo.findAll());
        model.addAttribute("users", usersRepo.findAll());
        return "admin_panel";
    }

    @GetMapping("/delete/{id}")
    public String DeleteUser(Model model, @PathVariable Long id) {
        usersRepo.deleteById(id);
        return "redirect:/index";
    }
}