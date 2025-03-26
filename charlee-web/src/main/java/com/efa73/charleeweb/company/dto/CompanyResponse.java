package com.efa73.charleeweb.company.dto;

import com.efa73.charleeweb.company.entity.Company;

public record CompanyResponse(Long id, String name, String siteLink) {

    public static CompanyResponse createResponse(Company company) {
        return new CompanyResponse(company.getId(), company.getName(), company.getSiteLink());
    }
}
