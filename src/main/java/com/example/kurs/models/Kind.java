package com.example.kurs.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kind", schema = "kurs")
public class Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
}
