package com.example.attendance.model;

public class Staff {

    private String punchId;
    private String name;
    private String role;

    public Staff(String punchId, String name, String role) {
        this.punchId = punchId;
        this.name = name;
        this.role = role;
    }

    public String getPunchId() {
        return punchId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}