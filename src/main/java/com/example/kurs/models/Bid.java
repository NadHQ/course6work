package com.example.kurs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bid", schema = "kurs")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Profile profileId;

    @ManyToOne
    private Kind kindId;

    public Bid(Profile profileId, Kind kindId) {
        this.profileId = profileId;
        this.kindId = kindId;
    }
}
