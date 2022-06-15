package com.example.kurs.controllers;

import com.example.kurs.models.Users;
import com.example.kurs.models.enums.Roles;
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
public class AccountCont {
    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    AssociationRepo associationRepo;

    @GetMapping("/acc/{id}")
    public String Table(Model model, @PathVariable Long id) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() + " " +
                profileRepo.getById(UserId).getThirdname();
        model.addAttribute("role", globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("usrRole", Roles.values());
        model.addAttribute("profileav", profileRepo.getById(UserId).getAvatar());
        model.addAttribute("login", usersRepo.getById(id).getUsername());
        model.addAttribute("pass", usersRepo.getById(id).getPassword());
        model.addAttribute("profile_id", usersRepo.getById(id).getProfileId().getId());
        model.addAttribute("selected_role", usersRepo.getById(id).getRole());
        return "account";
    }

    @PostMapping("/update")
    public String UpdateUser(Model model, @RequestParam String first, @RequestParam String second, @RequestParam String third, @RequestParam String role) {
        Users users = usersRepo.findByUsername(first);
        users.setPassword(second);
        users.setProfileId(profileRepo.getById(Long.valueOf(third)));
        users.setRole(Roles.valueOf(role));
        usersRepo.save(users);
        return "redirect:/admin/panel";
    }
}