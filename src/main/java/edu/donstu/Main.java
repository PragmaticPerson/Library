package edu.donstu;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.donstu.service.models.lab.ChildUser;
import edu.donstu.service.models.security.Role;
import edu.donstu.service.models.security.User;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Role role = new Role(1, "ROLE_ADMIN");

        System.out.println("Empty ChildUser:\n");
        ChildUser cu = new ChildUser();
        cu.showInfo();

        ChildUser cu2 = new ChildUser(1, "User2", "mail2@mail.ru", "pass2", "pass2", Collections.singleton(role));
        System.out.println(cu2);

        User u = new User(1, "User", "mail@mail.ru", "pass", "pass", Collections.singleton(role));
        System.out.println("Orig:\n" + u);

        try {
            User u2 = u.clone();
            System.out.println("Clone:\n" + u2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
