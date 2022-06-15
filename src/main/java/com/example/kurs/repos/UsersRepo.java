package com.example.kurs.repos;

import com.example.kurs.models.Profile;
import com.example.kurs.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    List<Users> findAll();
    void deleteByUsername(String usr);
    Users findByProfileId(Profile profile);
}
