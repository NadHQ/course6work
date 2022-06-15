package com.example.kurs.controllers;

import com.example.kurs.models.Profile;
import com.example.kurs.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BidsCont {
    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    KindRepo kindRepo;

    @Autowired
    BidRepo bidRepo;

    @Autowired
    AssociationRepo associationRepo;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/bids")
    public String Index(Model model) {
        Long profl_id = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(profl_id).getName() + " " +
                profileRepo.getById(profl_id).getThirdname();
        System.out.println(globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(profl_id).getAvatar());
        model.addAttribute("role", globalClass.getUserRole());

        model.addAttribute("users", bidRepo.findAll());
        return "bids";
    }

    @PostMapping("/addbids")
    public String AddFunc(Model model, @RequestParam String passport, @RequestParam String bidtype, @RequestParam String bidid) {
        if (bidtype.equals(kindRepo.getById(1L).getName())) {
            DismissAdd(passport, bidid);
        } else
            WeekendAdd(passport, bidid);

        return "redirect:/bids";
    }

    public void WeekendAdd(String passport, String id) {
        Profile profile = profileRepo.findByPassport(passport);
        profile.setAssosiation(associationRepo.getById(2L));
        profileRepo.save(profile);
        bidRepo.deleteById(Long.valueOf(id));
    }

    public void DismissAdd(String passport, String id) {
        Profile profile = profileRepo.findByPassport(passport);
        profile.setAssosiation(associationRepo.getById(3L));
        usersRepo.findByProfileId(profile).setProfileId(null);
        profileRepo.save(profile);
        bidRepo.deleteById(Long.valueOf(id));
    }

}
