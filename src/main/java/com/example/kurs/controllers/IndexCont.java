package com.example.kurs.controllers;

import com.example.kurs.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont {
    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @GetMapping("/index")
    public String Index(Model model) {
        Long profl_id = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(profl_id).getName() + " " +
                profileRepo.getById(profl_id).getThirdname();
        System.out.println(globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(profl_id).getAvatar());
        model.addAttribute("role", globalClass.getUserRole());
        return "index";
    }
}
