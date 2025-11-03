/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author admin
 */
public class Course {
    // Private attributes
    private int id;
    private String title;
    private String description;
    private int instructorId;
    private double price;
    
    // Để hiển thị tên instructor (từ JOIN query)
    private String instructorName;
    
    // Constructor mặc định
    public Course() {
    }
    
    // Constructor với tham số
    public Course(String title, String description, int instructorId, double price) {
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.price = price;
    }
    
    // GETTERS và SETTERS
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getInstructorId() {
        return instructorId;
    }
    
    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    
    // BUSINESS LOGIC METHODS
    public boolean isFree() {
        return this.price == 0;
    }
    
    public String getPriceText() {
        if (isFree()) {
            return "Miễn phí";
        }
        return String.format("%.0f VNĐ", price);
    }
    
    @Override
    public String toString() {
        return title + " - " + getPriceText();
    }
}
