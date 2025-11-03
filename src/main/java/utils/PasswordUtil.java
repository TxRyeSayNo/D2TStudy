/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author admin
 */
public class PasswordUtil {
    
    /**
     * Hash password using SHA-256
     * @param password Plain text password
     * @return Hashed password as hexadecimal string
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Verify password against hashed password
     * @param plainPassword Plain text password to verify
     * @param hashedPassword Stored hashed password
     * @return true if passwords match, false otherwise
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        String newHash = hashPassword(plainPassword);
        return newHash != null && newHash.equals(hashedPassword);
    }
    
    // For testing
    public static void main(String[] args) {
        String password = "123";
        String hashed = hashPassword(password);
        
        System.out.println("Plain: " + password);
        System.out.println("Hashed: " + hashed);
        System.out.println("Verify: " + verifyPassword("123", hashed));
        System.out.println("Wrong: " + verifyPassword("wrong", hashed));
    }
}
