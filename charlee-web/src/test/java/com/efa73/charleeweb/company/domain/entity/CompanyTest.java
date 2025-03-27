package com.efa73.charleeweb.company.domain.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CompanyTest {

    private Company company;

    @BeforeEach
    void setUp() {
        company = Company.createEntity("name", "siteLink");
    }

    @Test
    void updateEntity() {
        // when
        String updateName = "updateName";
        String updateSiteLink = "updateSiteLink";
        company.updateEntity(updateName, updateSiteLink);

        // then
        Assertions.assertThat(company.getName()).isEqualTo(updateName);
        Assertions.assertThat(company.getSiteLink()).isEqualTo(updateSiteLink);
    }
}
