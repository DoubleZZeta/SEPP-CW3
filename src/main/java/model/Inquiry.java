package model;

import java.time.LocalDateTime;

public class Inquiry {
    private final LocalDateTime createdAt;
    private final String inquirerEmail;
    private final String subject;
    private final String content;
    private String assignedTo;

    public Inquiry(String inquirerEmail, String subject, String content) {
        this.createdAt = LocalDateTime.now();
        this.inquirerEmail = inquirerEmail;
        this.subject = subject;
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getInquirerEmail() {
        return inquirerEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
