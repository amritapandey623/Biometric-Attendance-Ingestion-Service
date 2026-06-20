package com.example.attendance.model;

public class Student {

    private String punchId;
    private String name;
    private String classSection;

    public Student(String punchId, String name, String classSection) {
        this.punchId = punchId;
        this.name = name;
        this.classSection = classSection;
    }

    public String getPunchId() {
        return punchId;
    }

    public String getName() {
        return name;
    }

    public String getClassSection() {
        return classSection;
    }
}