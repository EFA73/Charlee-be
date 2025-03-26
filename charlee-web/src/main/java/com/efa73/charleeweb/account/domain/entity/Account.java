package com.efa73.charleeweb.user.domain.entity;

import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.common.Common;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "\"user\"") //TODO: 테스트 통과하기 위해 임시로 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public static User of(String name, String email, String password, String phone, Role role) {
        return new User(name, email, password, phone, role);
    }

    private User(String name, String email, String password, String phone, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }
}
