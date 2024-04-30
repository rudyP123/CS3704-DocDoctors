package com.example.usermanagement;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<String, User> userDatabase;

    public UserManager() {
        userDatabase = new HashMap<>();
    }

    public boolean registerUser(String username, String password, String email) {
        if (userDatabase.containsKey(username)) {
            return false;
        }
        User newUser = new User(username, password, email);
        userDatabase.put(username, newUser);
        return true;
    }

    public User getUser(String username) {
        return userDatabase.get(username);
    }

    public boolean updateUserEmail(String username, String newEmail) {
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        User user = userDatabase.get(username);
        user.setEmail(newEmail);
        return true;
    }

    public boolean deleteUser(String username) {
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        userDatabase.remove(username);
        return true;
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.registerUser("johnDoe", "password123", "johndoe@example.com");
        userManager.updateUserEmail("johnDoe", "john.doe@newdomain.com");
        userManager.deleteUser("johnDoe");
    }
}

class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
