package com.efa73.charleeweb.company.service;

import com.efa73.charleeweb.company.entity.Company;
import com.efa73.charleeweb.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public String createCompany(Company company) {

        var saved = companyRepository.save(company);

        return saved.getName();
    }

    public Company getCompany(Long companyId) {

        return companyRepository.findById(companyId).orElse(null);
    }
}
