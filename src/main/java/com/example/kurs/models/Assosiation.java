package com.example.kurs.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "assosiation", schema = "kurs")
public class Assosiation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
