package com.efa73.charleeweb.company.domain.service;

import com.efa73.charleeweb.common.exception.CustomErrorCode;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.repository.CompanyRepository;
import com.efa73.charleeweb.company.interfaces.dto.CompanyRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(Company company) {

        return companyRepository.save(company);
    }

    public Company getCompany(Long companyId) {

        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException(CustomErrorCode.ENTITY_NOT_FOUND.getMessage()));
    }

    public Company updateCompany(CompanyRequest companyRequest, Long companyId) {

        if (companyRepository.existsById(companyId)) {
            var company = Company.updateEntity(companyId, companyRequest.name(), companyRequest.siteLink());

            return companyRepository.save(company);
        } else {
            throw new EntityNotFoundException(CustomErrorCode.ENTITY_NOT_FOUND.getMessage());
        }
    }

    public void deleteCompany(Long companyId) {

        if (companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
        } else {
            throw new EntityNotFoundException(CustomErrorCode.ENTITY_NOT_FOUND.getMessage());
        }
    }
}
