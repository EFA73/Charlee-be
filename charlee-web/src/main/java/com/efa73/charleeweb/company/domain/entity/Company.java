package com.efa73.charleeweb.company.domain.entity;

import com.efa73.charleeweb.common.Common;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(name = "uq_company_name", columnNames = {"name"})
})
@Getter
@NoArgsConstructor
public class Company extends Common {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public static Company createEntity(String name) {
        Company company = new Company();
        company.name = name;
        return company;
    }

    public void updateEntity(String name) {
        this.name = name;
    }
}
