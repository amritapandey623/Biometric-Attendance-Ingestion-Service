package com.example.attendance.model;

public class PunchLog {

    private String punchId;
    private String timestamp;

    public PunchLog(String punchId, String timestamp) {
        this.punchId = punchId;
        this.timestamp = timestamp;
    }

    public String getPunchId() {
        return punchId;
    }

    public String getTimestamp() {
        return timestamp;
    }
}