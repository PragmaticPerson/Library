package edu.donstu.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.donstu.service.models.security.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    
    User findByEmail(String email);
}
