package com.example.ticketing.api.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint(20)", nullable = false)
    private Long id;

    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(name = "password", columnDefinition = "varchar(100)", nullable = false)
    private String password;


    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

