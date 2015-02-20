package com.bananaj.user.service;

import com.bananaj.user.domain.User;
import com.bananaj.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    void greet() {
        System.out.println("greet");
    }

    public String saySomething() {
        return "said";
    }

    public void addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    public User getUserByUserName(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }
}