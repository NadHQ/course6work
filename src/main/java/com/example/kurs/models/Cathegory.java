package com.example.kurs.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cathegory", schema = "kurs")
public class Cathegory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
