package com.conduit.model;

public class UserRequest {
    private User user;

    public UserRequest() {}

    public UserRequest(User user) {
        this.user = user;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
