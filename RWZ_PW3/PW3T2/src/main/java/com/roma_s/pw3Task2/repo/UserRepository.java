package com.georgij.pw3Task2.repo;

import com.georgij.pw3Task2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
