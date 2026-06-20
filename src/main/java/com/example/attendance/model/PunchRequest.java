package com.example.attendance.model;

public class PunchRequest {

    private String punchId;
    private String deviceSerial;
    private String timestamp;

    public String getPunchId() {
        return punchId;
    }

    public void setPunchId(String punchId) {
        this.punchId = punchId;
    }

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}