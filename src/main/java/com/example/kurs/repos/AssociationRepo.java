package com.example.kurs.repos;

import com.example.kurs.models.Assosiation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationRepo extends JpaRepository<Assosiation, Long> {

}
