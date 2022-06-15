package com.example.kurs.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile", schema = "kurs")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "thirdname")
    private String thirdname;

    @Column(name = "birth_date")
    private String birthDate;

    @ManyToOne
    private Assosiation assosiation;

    @ManyToOne
    private Cathegory cathegory;

    @Column(name = "passport")
    private String passport;

    @Column(name = "workPhone")
    private String workPhone;

    @Column(name = "workEmail")
    private String workEmail;

    @Column(name = "mobilePhone")
    private String mobilePhone;

    @Column(name = "email")
    private String email;

    @Column(name = "citizenship")
    private String citizenship;

    @Column(name = "reg_place")
    private String regPlace;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "profilePic")
    private String profilePic;
}
