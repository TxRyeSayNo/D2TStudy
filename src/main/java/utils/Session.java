/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import models.User;

/**
 *
 * @author admin
 */
public class Session {
    // Static variable - Chỉ có 1 instance trong toàn bộ ứng dụng
    private static User currentUser = null;
    
    /**
     * Lưu thông tin user đang đăng nhập
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
        if (user != null) {
            System.out.println("✓ User logged in: " + user.getUsername() + " (" + user.getRole() + ")");
        }
    }
    
    /**
     * Lấy thông tin user hiện tại
     */
    public static User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Kiểm tra đã đăng nhập chưa
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Kiểm tra user hiện tại có phải Admin không
     */
    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.isAdmin();
    }
    
    /**
     * Kiểm tra user hiện tại có phải Instructor không
     */
    public static boolean isInstructor() {
        return isLoggedIn() && currentUser.isInstructor();
    }
    
    /**
     * Kiểm tra user hiện tại có phải Student không
     */
    public static boolean isStudent() {
        return isLoggedIn() && currentUser.isStudent();
    }
    
    /**
     * Đăng xuất - Clear session
     */
    public static void logout() {
        if (currentUser != null) {
            System.out.println("✓ User logged out: " + currentUser.getUsername());
            currentUser = null;
        }
    }
    
    /**
     * Lấy ID của user hiện tại
     */
    public static int getCurrentUserId() {
        return isLoggedIn() ? currentUser.getId() : -1;
    }
    
    /**
     * Lấy username của user hiện tại
     */
    public static String getCurrentUsername() {
        return isLoggedIn() ? currentUser.getUsername() : "Guest";
    }
    
    /**
     * Lấy fullname của user hiện tại
     */
    public static String getCurrentFullname() {
        return isLoggedIn() ? currentUser.getFullname() : "Guest";
    }
}
