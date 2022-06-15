package com.example.kurs.repos;

import com.example.kurs.models.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepo extends JpaRepository<Kind, Long> {
}
