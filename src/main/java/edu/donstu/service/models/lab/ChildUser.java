package edu.donstu.service.models.lab;

import java.util.Set;

import edu.donstu.service.models.security.Role;
import edu.donstu.service.models.security.User;

public class ChildUser extends User {

    public ChildUser() {
    }

    public ChildUser(int id, String username, String email, String password, String passwordConfirm, Set<Role> roles) {
        super(id, username, email, password, passwordConfirm, roles);
    }
}
