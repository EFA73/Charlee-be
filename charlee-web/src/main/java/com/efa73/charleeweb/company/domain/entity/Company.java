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

    private String siteLink;

    public static Company createEntity(String name, String siteLink) {
        Company company = new Company();
        company.name = name;
        company.siteLink = siteLink;
        return company;
    }

    public static Company updateEntity(Long id, String name, String siteLink) {
        Company company = new Company();
        company.id = id;
        company.name = name;
        company.siteLink = siteLink;
        return company;
    }
}
