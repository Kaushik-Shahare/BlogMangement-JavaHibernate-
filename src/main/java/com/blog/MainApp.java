package com.blog;

import com.blog.dao.UserDAO;
import com.blog.entities.User;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---- -------- -------- -------- -------- -------- -------- -------- -------- ----");
            System.out.println("---- User Management System ----");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. List All Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create User
                    User newUser = new User();
                    System.out.print("Enter username: ");
                    newUser.setUsername(scanner.nextLine());
                    System.out.print("Enter password: ");
                    newUser.setPassword(scanner.nextLine());
                    System.out.print("Enter role: ");
                    newUser.setRole(scanner.nextLine());
                    userDAO.saveUser(newUser);
                    System.out.println("User created successfully!");
                    break;

                case 2:
                    // Read User
                    System.out.print("Enter user ID to read: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    User user = userDAO.getUser(readId);
                    if (user != null) {
                        System.out.println("User ID: " + user.getId());
                        System.out.println("Username: " + user.getUsername());
                        System.out.println("Role: " + user.getRole());
                    } else {
                        System.out.println("User not found!");
                    }
                    break;

                case 3:
                    // Update User
                    System.out.print("Enter user ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    User existingUser = userDAO.getUser(updateId);
                    if (existingUser != null) {
                        System.out.println("Current details:");
                        System.out.println("Username: " + existingUser.getUsername());
                        System.out.print("Enter new username (leave empty to keep current): ");
                        String newUsername = scanner.nextLine();
                        if (!newUsername.isEmpty()) {
                            existingUser.setUsername(newUsername);
                        }
                        System.out.print("Enter new password (leave empty to keep current): ");
                        String newPassword = scanner.nextLine();
                        if (!newPassword.isEmpty()) {
                            existingUser.setPassword(newPassword);
                        }
                        System.out.print("Enter new role (leave empty to keep current): ");
                        String newRole = scanner.nextLine();
                        if (!newRole.isEmpty()) {
                            existingUser.setRole(newRole);
                        }
                        userDAO.updateUser(existingUser);
                        System.out.println("User updated successfully!");
                    } else {
                        System.out.println("User not found!");
                    }
                    break;

                case 4:
                    // Delete User
                    System.out.print("Enter user ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    userDAO.deleteUser(deleteId);
                    System.out.println("User deleted successfully!");
                    break;

                case 5:
                    // List All Users
                    List<User> users = userDAO.getAllUsers();
                    if (!users.isEmpty()) {
                        System.out.println("---- List of Users ----");
                        for (User u : users) {
                            System.out.println(
                                    "ID: " + u.getId() + ", Username: " + u.getUsername() + ", Role: " + u.getRole());
                        }
                    } else {
                        System.out.println("No users found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);

        scanner.close();
    }
}