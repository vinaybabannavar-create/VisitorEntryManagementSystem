package com.visitor.dto;

import java.sql.Timestamp;

public class VisitorDTO {
    private int visitorId;
    private String name;
    private String purpose;
    private String contactNumber;
    private Timestamp entryTime;

    public VisitorDTO() {
    }

    public VisitorDTO(String name, String purpose, String contactNumber) {
        this.name = name;
        this.purpose = purpose;
        this.contactNumber = contactNumber;
    }

    public VisitorDTO(int visitorId, String name, String purpose, String contactNumber, Timestamp entryTime) {
        this.visitorId = visitorId;
        this.name = name;
        this.purpose = purpose;
        this.contactNumber = contactNumber;
        this.entryTime = entryTime;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public String toString() {
        return "Visitor [ID=" + visitorId + ", Name=" + name + ", Purpose=" + purpose + 
               ", Contact=" + contactNumber + ", Entry Time=" + entryTime + "]";
    }
}
