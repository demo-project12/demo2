package com.example.demo.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@NoArgsConstructor
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

    @Column(length = 50)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private String picture;

    @Enumerated(EnumType.STRING)  //JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지 결정
    @Column(nullable = false)     //기본적으로 int로 된 숫자가 저장됨, 숫자로하면 의미를 알 수 없어 문자열로 수정
    private Role role;

    private int status;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey() ;
    }
}
