/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class Enrollment {
    // Private attributes
    private int id;
    private int studentId;
    private int courseId;
    private String enrolledDate;
    
    // Để hiển thị thông tin từ JOIN query
    private String studentName;
    private String courseTitle;
    
    // Constructor mặc định
    public Enrollment() {
    }
    
    // Constructor với tham số
    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    // GETTERS và SETTERS
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getEnrolledDate() {
        return enrolledDate;
    }
    
    public void setEnrolledDate(String enrolledDate) {
        this.enrolledDate = enrolledDate;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getCourseTitle() {
        return courseTitle;
    }
    
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    
    @Override
    public String toString() {
        return studentName + " → " + courseTitle;
    }
}