package com.axmor.comment;

import java.util.Date;

public class Comment {

    private int id;
    private int issueId;
    private Date date;
    private String author;
    private String text;

    public Comment() {
    }

    public Comment(int issueId, Date date, String author, String text) {
        this.issueId = issueId;
        this.date = date;
        this.author = author;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
