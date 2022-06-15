package com.example.kurs.repos;

import com.example.kurs.models.Cathegory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CathegoryRepo extends JpaRepository<Cathegory, Long> {
    Cathegory findByName(String str);
}
