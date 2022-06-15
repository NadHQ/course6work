package com.example.kurs.repos;

import com.example.kurs.models.Assosiation;
import com.example.kurs.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    List<Profile> findAllByAssosiation(Assosiation assosiation);
    Profile findByPassport(String passport);
}
