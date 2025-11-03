/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import models.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CourseDAO implements IDataAccess<Course> {
    
    @Override
    public boolean insert(Course course) {
        String sql = "INSERT INTO courses (title, description, instructor_id, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, course.getTitle());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getInstructorId());
            pstmt.setDouble(4, course.getPrice());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Course findById(int id) {
        String sql = "SELECT c.*, u.fullname as instructor_name " +
                     "FROM courses c " +
                     "LEFT JOIN users u ON c.instructor_id = u.id " +
                     "WHERE c.id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourse(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, u.fullname as instructor_name " +
                     "FROM courses c " +
                     "LEFT JOIN users u ON c.instructor_id = u.id " +
                     "ORDER BY c.id DESC";
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(extractCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    @Override
    public boolean update(Course course) {
        String sql = "UPDATE courses SET title=?, description=?, instructor_id=?, price=? WHERE id=?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, course.getTitle());
            pstmt.setString(2, course.getDescription());
            pstmt.setInt(3, course.getInstructorId());
            pstmt.setDouble(4, course.getPrice());
            pstmt.setInt(5, course.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // CUSTOM METHODS - Thể hiện tính đa hình
    public List<Course> findByInstructor(int instructorId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, u.fullname as instructor_name " +
                     "FROM courses c " +
                     "LEFT JOIN users u ON c.instructor_id = u.id " +
                     "WHERE c.instructor_id = ? " +
                     "ORDER BY c.id DESC";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, instructorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courses.add(extractCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    public List<Course> searchByTitle(String keyword) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, u.fullname as instructor_name " +
                     "FROM courses c " +
                     "LEFT JOIN users u ON c.instructor_id = u.id " +
                     "WHERE c.title LIKE ? " +
                     "ORDER BY c.id DESC";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courses.add(extractCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    // HELPER METHOD - Thể hiện tính trừu tượng
    private Course extractCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setInstructorId(rs.getInt("instructor_id"));
        course.setPrice(rs.getDouble("price"));
        course.setInstructorName(rs.getString("instructor_name"));
        return course;
    }
}