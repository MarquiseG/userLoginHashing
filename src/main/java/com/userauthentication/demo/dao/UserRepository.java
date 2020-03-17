package com.userauthentication.demo.dao;

import com.userauthentication.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{ User findByUsername(String username);
}
