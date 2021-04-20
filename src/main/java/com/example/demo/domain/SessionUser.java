package com.example.demo.domain;

import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;
    private String registrationId;

    public SessionUser(User user, String registrationId) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.registrationId = registrationId;
    }
}
