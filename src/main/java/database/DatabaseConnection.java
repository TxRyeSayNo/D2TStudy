/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author admin
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:courses.db";
    private static Connection connection = null;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public static void initializeDatabase() {
        try (Statement stmt = getConnection().createStatement()) {
            
            // CHỈ 3 BẢNG ĐƠN GIẢN
            
            // 1. USERS
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL, " +
                "fullname TEXT NOT NULL, " +
                "email TEXT, " +
                "role TEXT NOT NULL CHECK(role IN ('ADMIN', 'INSTRUCTOR', 'STUDENT'))" +
                ")"
            );
            
            // 2. COURSES
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS courses (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "description TEXT, " +
                "instructor_id INTEGER NOT NULL, " +
                "price REAL DEFAULT 0, " +
                "FOREIGN KEY(instructor_id) REFERENCES users(id) ON DELETE CASCADE" +
                ")"
            );
            
            // 3. ENROLLMENTS (Student đăng ký Course)
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS enrollments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_id INTEGER NOT NULL, " +
                "course_id INTEGER NOT NULL, " +
                "enrolled_date TEXT DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(student_id) REFERENCES users(id) ON DELETE CASCADE, " +
                "FOREIGN KEY(course_id) REFERENCES courses(id) ON DELETE CASCADE, " +
                "UNIQUE(student_id, course_id)" +
                ")"
            );
            
            System.out.println("✓ Database initialized!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
