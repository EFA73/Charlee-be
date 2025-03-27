package com.efa73.charleeweb.company.interfaces.dto;

import com.efa73.charleeweb.company.domain.entity.Company;

public record CompanyResponse(Long id, String name, String siteLink) {

    public static CompanyResponse createDto(Company company) {
        return new CompanyResponse(company.getId(), company.getName(), company.getSiteLink());
    }
}
