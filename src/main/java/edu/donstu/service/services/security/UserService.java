package edu.donstu.service.services.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.donstu.dao.security.UserRepository;
import edu.donstu.service.models.security.Role;
import edu.donstu.service.models.security.User;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean save(User newUser) {
        User user = userRepository.findByUsername(newUser.getUsername());

        if (user != null) {
            return false;
        }

        newUser.setRoles(Collections.singleton(new Role(2, "ROLE_USER")));
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return true;
    }

    public User get(int id) {
        return userRepository.getById(id);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}