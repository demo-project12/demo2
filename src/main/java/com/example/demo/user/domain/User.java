package com.example.demo.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 20)
    private String id;

    @Column(length = 60)
    private String password;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String email;

    private int status;
}
