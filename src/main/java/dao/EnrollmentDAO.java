/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import models.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class EnrollmentDAO implements IDataAccess<Enrollment> {
    
    @Override
    public boolean insert(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setInt(2, enrollment.getCourseId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Enrollment findById(int id) {
        String sql = "SELECT e.*, u.fullname as student_name, c.title as course_title " +
                     "FROM enrollments e " +
                     "LEFT JOIN users u ON e.student_id = u.id " +
                     "LEFT JOIN courses c ON e.course_id = c.id " +
                     "WHERE e.id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractEnrollment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Enrollment> findAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.*, u.fullname as student_name, c.title as course_title " +
                     "FROM enrollments e " +
                     "LEFT JOIN users u ON e.student_id = u.id " +
                     "LEFT JOIN courses c ON e.course_id = c.id " +
                     "ORDER BY e.enrolled_date DESC";
        try (Statement stmt = DatabaseConnection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                enrollments.add(extractEnrollment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
    
    @Override
    public boolean update(Enrollment enrollment) {
        // Enrollments thường không cần update
        return false;
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // CUSTOM METHODS - Thể hiện tính đa hình
    public List<Enrollment> findByStudent(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.*, u.fullname as student_name, c.title as course_title " +
                     "FROM enrollments e " +
                     "LEFT JOIN users u ON e.student_id = u.id " +
                     "LEFT JOIN courses c ON e.course_id = c.id " +
                     "WHERE e.student_id = ? " +
                     "ORDER BY e.enrolled_date DESC";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                enrollments.add(extractEnrollment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
    
    public List<Enrollment> findByCourse(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.*, u.fullname as student_name, c.title as course_title " +
                     "FROM enrollments e " +
                     "LEFT JOIN users u ON e.student_id = u.id " +
                     "LEFT JOIN courses c ON e.course_id = c.id " +
                     "WHERE e.course_id = ? " +
                     "ORDER BY e.enrolled_date DESC";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                enrollments.add(extractEnrollment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
    
    public boolean isEnrolled(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE student_id = ? AND course_id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int countEnrollmentsByCourse(int courseId) {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE course_id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int countEnrollmentsByStudent(int studentId) {
        String sql = "SELECT COUNT(*) FROM enrollments WHERE student_id = ?";
        try (PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // HELPER METHOD - Thể hiện tính trừu tượng
    private Enrollment extractEnrollment(ResultSet rs) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(rs.getInt("id"));
        enrollment.setStudentId(rs.getInt("student_id"));
        enrollment.setCourseId(rs.getInt("course_id"));
        enrollment.setEnrolledDate(rs.getString("enrolled_date"));
        enrollment.setStudentName(rs.getString("student_name"));
        enrollment.setCourseTitle(rs.getString("course_title"));
        return enrollment;
    }
}
