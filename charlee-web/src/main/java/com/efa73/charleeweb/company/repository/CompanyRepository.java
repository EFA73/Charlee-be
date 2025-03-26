package com.efa73.charleeweb.company.repository;

import com.efa73.charleeweb.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
