package com.example.kurs.controllers;


import com.example.kurs.models.Cathegory;
import com.example.kurs.models.Profile;
import com.example.kurs.models.enums.Roles;
import com.example.kurs.repos.AssociationRepo;
import com.example.kurs.repos.CathegoryRepo;
import com.example.kurs.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class AddProfileCont {

    @Autowired
    GlobalClass globalClass;

    @Autowired
    ProfileRepo profileRepo;

    @Autowired
    AssociationRepo associationRepo;

    @Autowired
    CathegoryRepo cathegoryRepo;

    @GetMapping("/add/customer")
    public String AddCustomer(Model model) {
        Long UserId = globalClass.getUser().getProfileId().getId();
        String userName = profileRepo.getById(UserId).getName() + " " +
                profileRepo.getById(UserId).getThirdname();
        List<String> lsit = new ArrayList<>();
        for (long i = 1; i < 5; i++) {
            lsit.add(cathegoryRepo.getById(i).getName());
        }
        model.addAttribute("role", globalClass.getUserRole());
        model.addAttribute("username", userName);
        model.addAttribute("profileav", profileRepo.getById(UserId).getAvatar());
        model.addAttribute("usrRole", lsit);

        return "add_customer";
    }

    @PostMapping("/add/customer/page")
    public String ConfirmAdd(@RequestParam String first_name, @RequestParam String secondname, @RequestParam String third_name,
                             @RequestParam String birth, @RequestParam String citizenship, @RequestParam String passnumber,
                             @RequestParam String cathegory, @RequestParam String workphone, @RequestParam String workmail,
                             @RequestParam String address, @RequestParam String mobilePhone, @RequestParam String email,
                             @RequestParam MultipartFile file) throws IOException {

        String str = UUID.randomUUID().toString();
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream("C:\\Users\\dkova\\Files\\Projects\\Kurs\\src\\main\\resources\\img\\dogs\\" + str + ".png"));
        stream.write(bytes);
        stream.close();

        Profile profile = new Profile();
        profile.setName(first_name);
        profile.setSurname(secondname);
        profile.setThirdname(third_name);
        profile.setBirthDate(birth);
        profile.setAssosiation(associationRepo.getById(1L));
        profile.setCathegory(cathegoryRepo.findByName(cathegory));
        profile.setPassport(passnumber);
        profile.setWorkPhone(workphone);
        profile.setWorkEmail(workmail);
        profile.setMobilePhone(mobilePhone);
        profile.setEmail(email);
        profile.setCitizenship(citizenship);
        profile.setRegPlace(address);
        profile.setAvatar("avatar5.jpeg");
        profile.setProfilePic(str + ".png");
        profileRepo.save(profile);

        return "redirect:/table";
    }
}
