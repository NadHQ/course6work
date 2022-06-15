package com.example.kurs.controllers;

import com.example.kurs.models.Assosiation;
import com.example.kurs.models.Profile;
import com.example.kurs.repos.AssociationRepo;
import com.example.kurs.repos.ProfileRepo;
import com.example.kurs.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchiveCont {
    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    AssociationRepo associationRepo;

    @GetMapping("/archive")
    public String Table(Model model) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() + " " +
                profileRepo.getById(UserId).getThirdname();
        List<Profile> profileList = profileRepo.findAllByAssosiation(associationRepo.getById(3L));
        profileList.addAll(profileRepo.findAllByAssosiation(associationRepo.getById(4L)));
        model.addAttribute("role", globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(UserId).getAvatar());
//        model.addAttribute("users", profileRepo.findAll());
        model.addAttribute("users", profileList);
        return "archive";
    }
}
