package com.vpk.tutorial.springboottutorial.dao;

import com.vpk.tutorial.springboottutorial.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
