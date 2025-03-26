package com.efa73.charleeweb.company.interfaces.dto;

import com.efa73.charleeweb.company.domain.entity.Company;

public record CompanyRequest(String name, String siteLink) {

    public static Company createEntity(CompanyRequest companyRequest) {
        return Company.createEntity(companyRequest.name, companyRequest.siteLink);
    }
}
