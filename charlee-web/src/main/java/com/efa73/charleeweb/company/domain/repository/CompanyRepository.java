package com.efa73.charleeweb.company.domain.repository;

import com.efa73.charleeweb.company.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
