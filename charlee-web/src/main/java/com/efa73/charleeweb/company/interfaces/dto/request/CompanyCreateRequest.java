package com.efa73.charleeweb.company.interfaces.dto.request;

import com.efa73.charleeweb.company.domain.entity.Company;

public record CompanyCreateRequest(String name) {

    public static Company createEntity(CompanyCreateRequest companyCreateRequest) {
        return Company.createEntity(companyCreateRequest.name);
    }
}
