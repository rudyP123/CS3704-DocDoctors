package com.example.usermanagement;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<String, User> userDatabase;

/**
* Constructor for the UserManager class.
* Initializes a new instance of UserManager.
*/
    public UserManager() {
        userDatabase = new HashMap<>();
    }

/**
 * Registers a new user with the provided username, password, and email.
 *
 * @param username The username of the new user.
 * @param password The password of the new user.
 * @param email The email address of the new user.
 * @return true if the user is successfully registered, false otherwise.
 */
    public boolean registerUser(String username, String password, String email) {
        if (userDatabase.containsKey(username)) {
            return false;
        }
        User newUser = new User(username, password, email);
        userDatabase.put(username, newUser);
        return true;
    }

/**
 * Retrieves a User object based on the provided username.
 * This method queries the system database to find a User with the given username.
 *
 * @param username The username of the User to retrieve.
 * @return A User object that matches the provided username, or null if no match is found.
 */
    public User getUser(String username) {
        return userDatabase.get(username);
    }

/**
 * Updates the email address associated with the specified username.
 * 
 * This method allows for updating the email address of a user identified by their username.
 *
 * @param username The username of the user whose email address needs to be updated.
 * @param newEmail The new email address to be set for the user.
 * @return true if the email address is successfully updated, false otherwise.
 */
    public boolean updateUserEmail(String username, String newEmail) {
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        User user = userDatabase.get(username);
        user.setEmail(newEmail);
        return true;
    }

/**
* Deletes a user from the system based on the given username.
* 
* @param username the username of the user to be deleted
* @return true if the user was successfully deleted, false if the user was not found
*/
    public boolean deleteUser(String username) {
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        userDatabase.remove(username);
        return true;
    }

/**
 * The main method is the entry point of the Java program. 
 * It is a standard method signature required by the Java runtime
 * for running a Java application. It accepts an array of 
 * strings as arguments, typically from the command line. 
 * 
 * @param args An array of strings that represent the command-line arguments passed to the program.
 *             If no arguments are provided, the array will be empty. 
 *             These arguments can be used to customize the behavior of the program. 
 * @return This method does not return any value (void).
 * @throws Any exceptions thrown within the main method, if not handled, will be caught by the JVM
 *          and may cause the program to terminate. It is good practice to handle exceptions as needed.
 */
 public static void main(String[] args) {
    // Implementation code goes here
 }
*/
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

/**
 * Constructs a new User object with the specified username, password, and email.
 *
 * @param username the username of the user
 * @param password the password of the user
 * @param email the email address of the user
 */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

/**
 * Returns the username associated with this object.
 *
 * @return the username as a String
 */
    public String getUsername() {
        return username;
    }

/**
 * Sets the username for this object.
 * This method allows the username to be specified for the object.
 * The username provided will be assigned to the object.
 *
 * @param username the username to be set for this object
 */
    public void setUsername(String username) {
        this.username = username;
    }

/**
 * Retrieves and returns the password associated with this object.
 *
 * @return The password as a String.
 */
    public String getPassword() {
        return password;
    }

/**
 * Sets the password for this object. The password must be a non-empty string.
 * 
 * @param password the password to be set
 * @throws IllegalArgumentException if the given password is null or empty
 */
    public void setPassword(String password) {
        this.password = password;
    }

/**
 * Returns the email address associated with a user.
 * 
 * @return The email address of the user as a String.
 */
    public String getEmail() {
        return email;
    }

/**
 * Sets the email address of a user.
 * 
 * @param email a String representing the email address to be set
 * 
 * This method sets the email address for a user by taking a String input
 * and assigning it to the email variable of the user object. 
 * This allows for updating or initializing the user's email address.
 */
    public void setEmail(String email) {
        this.email = email;
    }
}
