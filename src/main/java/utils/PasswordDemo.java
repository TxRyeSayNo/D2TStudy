/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author admin
 */
public class PasswordDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMO PASSWORD HASHING ===\n");
        
        // Test 1: Hash một password
        String password1 = "123";
        String hashed1 = PasswordUtil.hashPassword(password1);
        
        System.out.println("Test 1: Hash password");
        System.out.println("Plain text: " + password1);
        System.out.println("Hashed:     " + hashed1);
        System.out.println("Length:     " + hashed1.length() + " characters");
        System.out.println();
        
        // Test 2: Cùng password → Cùng hash
        String password2 = "123";
        String hashed2 = PasswordUtil.hashPassword(password2);
        
        System.out.println("Test 2: Cùng password luôn cho cùng hash");
        System.out.println("Password 1: " + password1 + " → " + hashed1);
        System.out.println("Password 2: " + password2 + " → " + hashed2);
        System.out.println("Same hash?  " + hashed1.equals(hashed2));
        System.out.println();
        
        // Test 3: Verify password đúng
        System.out.println("Test 3: Verify password đúng");
        boolean correct = PasswordUtil.verifyPassword("123", hashed1);
        System.out.println("Input: 123");
        System.out.println("Stored hash: " + hashed1);
        System.out.println("Verify result: " + correct + " ✓");
        System.out.println();
        
        // Test 4: Verify password sai
        System.out.println("Test 4: Verify password sai");
        boolean wrong = PasswordUtil.verifyPassword("wrong", hashed1);
        System.out.println("Input: wrong");
        System.out.println("Stored hash: " + hashed1);
        System.out.println("Verify result: " + wrong + " ✗");
        System.out.println();
        
        // Test 5: Hash các password phổ biến
        System.out.println("Test 5: Hash các password phổ biến");
        String[] passwords = {"admin", "123456", "password", "abc123"};
        for (String pwd : passwords) {
            System.out.println(pwd + " → " + PasswordUtil.hashPassword(pwd));
        }
        System.out.println();
        
        // Test 6: SHA-256 là one-way function
        System.out.println("Test 6: Bảo mật");
        System.out.println("✓ SHA-256 là one-way function");
        System.out.println("✓ Không thể reverse từ hash → plain text");
        System.out.println("✓ Brute-force rất khó với 2^256 possibilities");
        System.out.println("✓ Password không bao giờ lưu dạng plain text trong DB");
        
        System.out.println("\n=== KẾT THÚC DEMO ===");
    }
}
