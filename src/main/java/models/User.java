/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class User {
    // Private attributes - CHỈ TRUY CẬP QUA GETTERS/SETTERS
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String role;
    
    // Constructor mặc định
    public User() {
    }
    
    // Constructor với tham số
    public User(String username, String password, String fullname, String email, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
    }
    
    // GETTERS và SETTERS - Public methods để truy cập private data
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
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
    
    public String getFullname() {
        return fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    // BUSINESS LOGIC METHODS - Thể hiện tính trừu tượng
    public boolean isAdmin() {
        return "ADMIN".equals(this.role);
    }
    
    public boolean isInstructor() {
        return "INSTRUCTOR".equals(this.role);
    }
    
    public boolean isStudent() {
        return "STUDENT".equals(this.role);
    }
    
    @Override
    public String toString() {
        return fullname + " (" + role + ")";
    }
}
