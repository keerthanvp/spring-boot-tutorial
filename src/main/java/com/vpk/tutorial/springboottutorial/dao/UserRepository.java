package com.vpk.tutorial.springboottutorial.dao;

import com.vpk.tutorial.springboottutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
