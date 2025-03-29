package com.efa73.charleeweb.company.domain.service;

import com.efa73.charleeweb.common.exception.CharleeException;
import com.efa73.charleeweb.common.exception.CommonErrorCode;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public Company createCompany(String email, String password, String name) {
        String encodedPassword = passwordEncoder.encode(password);
        Company company = Company.createEntity(email, encodedPassword, name);

        return companyRepository.save(company);
    }

    public Company getCompany(Long companyId) {
        return findCompany(companyId);
    }

    public Company updateCompany(Long companyId, String email, String password, String name) {
        String encodedPassword = passwordEncoder.encode(password);

        var foundCompany = findCompany(companyId);

        foundCompany.updateEntity(email, encodedPassword, name);

        return companyRepository.save(foundCompany);
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
