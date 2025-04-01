package com.efa73.charleeweb.company.domain.entity;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.common.Common;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Company(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public static Company createEntity(String name, Account account) {
        return new Company(name, account);
    }

    public void updateEntity(String email, String password, String name) {
        this.account.updateEntity(email, password);
        this.name = name;
    }
}
