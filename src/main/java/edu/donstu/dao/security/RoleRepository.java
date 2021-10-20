package edu.donstu.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.donstu.service.models.security.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
