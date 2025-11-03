/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import database.DatabaseConnection;
import dao.UserDAO;
import models.User;
import views.LoginFrame;
import javax.swing.*;

/**
 *
 * @author admin
 */
public class Main {
    
    public static void main(String[] args) {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Initialize database
        DatabaseConnection.initializeDatabase();
        
        // Tạo tài khoản mẫu
        createSampleData();
        
        // Hiển thị màn hình login
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
    
    private static void createSampleData() {
        UserDAO userDAO = new UserDAO();
        
        // Admin
        if (userDAO.findByRole("ADMIN").isEmpty()) {
            User admin = new User("admin", "123", "Admin", "admin@test.com", "ADMIN");
            userDAO.insert(admin);
            System.out.println("✓ Created: admin/123");
        }
        
        // Instructor
        if (userDAO.findByRole("INSTRUCTOR").isEmpty()) {
            User instructor = new User("gv1", "123", "Nguyen Van A", "gv1@test.com", "INSTRUCTOR");
            userDAO.insert(instructor);
            System.out.println("✓ Created: gv1/123");
        }
        
        // Student
        if (userDAO.findByRole("STUDENT").isEmpty()) {
            User student = new User("sv1", "123", "Tran Thi B", "sv1@test.com", "STUDENT");
            userDAO.insert(student);
            System.out.println("✓ Created: sv1/123");
        }
    }
}