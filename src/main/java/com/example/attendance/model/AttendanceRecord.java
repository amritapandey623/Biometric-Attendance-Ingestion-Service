package com.example.attendance.model;

public class AttendanceRecord {

    private String status;
    private String note;

    public AttendanceRecord(String status, String note) {
        this.status = status;
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }
}