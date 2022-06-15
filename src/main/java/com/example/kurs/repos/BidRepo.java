package com.example.kurs.repos;

import com.example.kurs.models.Bid;
import com.example.kurs.models.Kind;
import com.example.kurs.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepo extends JpaRepository<Bid,Long> {
    boolean existsBidByProfileIdAndKindId(Profile profile, Kind kind);

    @Override
    List<Bid> findAll();


}
