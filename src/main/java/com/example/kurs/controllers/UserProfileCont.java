package com.example.kurs.controllers;

import com.example.kurs.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserProfileCont {
    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @GetMapping("/user/{id}")
    public String Index(Model model, @PathVariable Long id) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() + " " +
                profileRepo.getById(UserId).getThirdname();
        model.addAttribute("role", globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(id).getAvatar());
        model.addAttribute("first_name", profileRepo.getById(id).getName());
        model.addAttribute("second_name", profileRepo.getById(id).getSurname());
        model.addAttribute("third_name", profileRepo.getById(id).getThirdname());
        model.addAttribute("cathegory", profileRepo.getById(id).getCathegory().getName());
        model.addAttribute("association", profileRepo.getById(id).getAssosiation().getName());
        model.addAttribute("workphone", profileRepo.getById(id).getWorkPhone());
        model.addAttribute("workmail", profileRepo.getById(id).getWorkEmail());
        model.addAttribute("address", profileRepo.getById(id).getRegPlace());
        model.addAttribute("mobilePhone", profileRepo.getById(id).getMobilePhone());
        model.addAttribute("email", profileRepo.getById(id).getEmail());
        model.addAttribute("profilePicture", profileRepo.getById(id).getProfilePic());
        model.addAttribute("birth", profileRepo.getById(id).getBirthDate());
        model.addAttribute("citizenship", profileRepo.getById(id).getCitizenship());
        model.addAttribute("passnumber", profileRepo.getById(id).getPassport());
        return "user";
    }
}
