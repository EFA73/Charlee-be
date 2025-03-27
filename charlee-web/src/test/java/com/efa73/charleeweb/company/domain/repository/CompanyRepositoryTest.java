package com.efa73.charleeweb.company.domain.repository;

import com.efa73.charleeweb.company.domain.entity.Company;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    void setUp() {
        company = Company.createEntity("업체 1", "업체 링크");
    }

    @Test
    @DisplayName("업체 생성 시 DB에 정상 저장되는지 테스트")
    void saveCompany() {

        var saved = companyRepository.save(company);

        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getName()).isEqualTo(company.getName());
        Assertions.assertThat(saved.getSiteLink()).isEqualTo(company.getSiteLink());
    }

    @Test
    @DisplayName("업체 조회 시 DB에서 정상 조회되는지 테스트")
    void findCompany() {
        // given: 업체 저장
        var saved = companyRepository.save(company);

        // when: 저장된 업체를 ID로 조회
        var foundOptional = companyRepository.findById(saved.getId());

        // then: 업체가 존재하며 저장된 값과 동일한지 검증
        Assertions.assertThat(foundOptional).isPresent();
        Company found = foundOptional.get();
        Assertions.assertThat(found.getId()).isEqualTo(saved.getId());
        Assertions.assertThat(found.getName()).isEqualTo(saved.getName());
        Assertions.assertThat(found.getSiteLink()).isEqualTo(saved.getSiteLink());
    }

    @Test
    @DisplayName("업체 수정 시 DB에 반영되는지 테스트")
    void updateCompany() {
        // given: 업체 저장
        var saved = companyRepository.save(company);

        // when: 저장된 업체의 이름과 링크 수정
        String updatedName = "수정된 업체명";
        String updatedSiteLink = "수정된 업체 링크";

        saved.updateEntity(updatedName, updatedSiteLink);
        var updated = companyRepository.save(saved);

        // then: 수정된 값이 DB에 반영되었는지 검증
        Assertions.assertThat(updated.getName()).isEqualTo(updatedName);
        Assertions.assertThat(updated.getSiteLink()).isEqualTo(updatedSiteLink);
    }

    @Test
    @DisplayName("업체 삭제 시 DB에서 정상 삭제되는지 테스트")
    void deleteCompany() {
        // given: 업체 저장
        var saved = companyRepository.save(company);

        // when: 업체 삭제
        companyRepository.delete(saved);

        // then: 해당 업체가 DB에서 삭제되었는지 확인
        var foundOptional = companyRepository.findById(saved.getId());
        Assertions.assertThat(foundOptional).isNotPresent();
    }

}
