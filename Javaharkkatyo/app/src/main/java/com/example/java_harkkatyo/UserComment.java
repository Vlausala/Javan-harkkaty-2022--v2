package com.example.java_harkkatyo;

public class UserComment {

    private String username;
    private String lakeName;
    private String comment;

    public UserComment(String username, String lakename, String comment) {
        this.username = username;
        this.lakeName = lakename;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLakeName() {
        return lakeName;
    }

    public void setLakeName(String lakeName) {
        this.lakeName = lakeName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

