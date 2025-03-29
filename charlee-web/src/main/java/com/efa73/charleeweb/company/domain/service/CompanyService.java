package com.efa73.charleeweb.company.domain.service;

import com.efa73.charleeweb.common.exception.CharleeException;
import com.efa73.charleeweb.common.exception.CommonErrorCode;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.repository.CompanyRepository;
import com.efa73.charleeweb.company.interfaces.dto.request.CompanyCreateRequest;
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

        return findCompany(companyId);
    }

    public Company updateCompany(CompanyCreateRequest companyCreateRequest, Long companyId) {

        var company = findCompany(companyId);

        company.updateEntity(companyCreateRequest.name());

        return companyRepository.save(company);
    }

    public void deleteCompany(Long companyId) {

        existsCompany(companyId);
        
        companyRepository.deleteById(companyId);
    }

    private Company findCompany(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new CharleeException(CommonErrorCode.NOT_FOUND));
    }

    private void existsCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new CharleeException(CommonErrorCode.NOT_FOUND);
        }
    }
}
