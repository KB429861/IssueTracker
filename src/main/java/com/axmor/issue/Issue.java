package com.axmor.issue;

import java.util.Date;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private String author;
    private Date startDate;
    private String status;

    public Issue() {
    }

    public Issue(String summary, String author, String description) {
        this.summary = summary;
        this.author = author;
        this.description = description;
        this.startDate = new Date();
        this.status = "Opened";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
