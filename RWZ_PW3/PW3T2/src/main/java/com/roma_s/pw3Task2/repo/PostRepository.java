package com.georgij.pw3Task2.repo;

import com.georgij.pw3Task2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
