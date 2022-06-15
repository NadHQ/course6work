package com.example.kurs.controllers;

import com.example.kurs.models.Bid;
import com.example.kurs.models.Profile;
import com.example.kurs.models.Users;
import com.example.kurs.repos.BidRepo;
import com.example.kurs.repos.KindRepo;
import com.example.kurs.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class ProfileCont {

    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    BidRepo bidRepo;
    @Autowired
    KindRepo kindRepo;

    @GetMapping("/profile")
    public String Login(Model model) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() + " " +
                profileRepo.getById(UserId).getThirdname();

        if (bidRepo.existsBidByProfileIdAndKindId(globalClass.getUser().getProfileId(), kindRepo.getById(2L))) {
            model.addAttribute("weekendExist", "exist");
        } else {
            model.addAttribute("weekendExist", "notExist");
        }

        if (bidRepo.existsBidByProfileIdAndKindId(globalClass.getUser().getProfileId(), kindRepo.getById(1L))) {
            model.addAttribute("pensionExist", "exist");
        } else {
            model.addAttribute("pensionExist", "notExist");
        }
        model.addAttribute("role", globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(UserId).getAvatar());
        model.addAttribute("first_name", profileRepo.getById(UserId).getName());
        model.addAttribute("second_name", profileRepo.getById(UserId).getSurname());
        model.addAttribute("third_name", profileRepo.getById(UserId).getThirdname());
        model.addAttribute("cathegory", profileRepo.getById(UserId).getCathegory().getName());
        model.addAttribute("association", profileRepo.getById(UserId).getAssosiation().getName());
        model.addAttribute("workphone", profileRepo.getById(UserId).getWorkPhone());
        model.addAttribute("workmail", profileRepo.getById(UserId).getWorkEmail());
        model.addAttribute("address", profileRepo.getById(UserId).getRegPlace());
        model.addAttribute("mobilePhone", profileRepo.getById(UserId).getMobilePhone());
        model.addAttribute("email", profileRepo.getById(UserId).getEmail());
        model.addAttribute("profilePicture", profileRepo.getById(UserId).getProfilePic());
        model.addAttribute("birth", profileRepo.getById(UserId).getBirthDate());
        model.addAttribute("citizenship", profileRepo.getById(UserId).getCitizenship());
        model.addAttribute("passnumber", profileRepo.getById(UserId).getPassport());
        return "profile";
    }

    @PostMapping("/changephoto")
    public String Change(Model model, @RequestParam MultipartFile file) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() +
                profileRepo.getById(UserId).getThirdname();

        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\dkova\\Files\\Projects\\Kurs\\src\\main\\resources\\img\\dogs\\" + UserId + ".png"));
            stream.write(bytes);
            stream.close();
            Profile profile = profileRepo.getById(UserId);
            profile.setProfilePic(UserId + ".png");
            profileRepo.save(profile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/profile";
    }

    @PostMapping("/weekend")
    public String AddWeekend(Model model) {
        System.out.println("RAZDWATRI");
        bidRepo.save(new Bid(globalClass.getUser().getProfileId(), kindRepo.getById(2L)));

        return "redirect:/profile";
    }

    @PostMapping("/pension")
    public String AddPension(Model model) {
        bidRepo.save(new Bid(globalClass.getUser().getProfileId(), kindRepo.getById(1L)));

        return "redirect:/profile";
    }

}
