package com.efa73.charleeweb.company.domain.service;

import com.efa73.charleeweb.common.exception.CharleeException;
import com.efa73.charleeweb.common.exception.CommonErrorCode;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.repository.CompanyRepository;
import com.efa73.charleeweb.company.interfaces.dto.request.CompanyRequest;
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
                .orElseThrow(() -> new CharleeException(CommonErrorCode.NOT_FOUND));
    }

    public Company updateCompany(CompanyRequest companyRequest, Long companyId) {

        var company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CharleeException(CommonErrorCode.NOT_FOUND));

        company.updateEntity(companyRequest.name(), companyRequest.siteLink());

        return companyRepository.save(company);
    }

    public void deleteCompany(Long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new CharleeException(CommonErrorCode.NOT_FOUND);
        }
        companyRepository.deleteById(companyId);
    }
}
