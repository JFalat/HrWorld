package com.example.hrworld;
public class Employee {
    private String username;
    private String userRole;
    private String employeeName;
    private String status;

    // Konstruktor
    public Employee(String username, String userRole, String employeeName, String status) {
        this.username = username;
        this.userRole = userRole;
        this.employeeName = employeeName;
        this.status = status;
    }

    // Gettery
    public String getUsername() {
        return username;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getStatus() {
        return status;
    }

    // Metoda do wyświetlania informacji o pracowniku
    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", userRole='" + userRole + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
