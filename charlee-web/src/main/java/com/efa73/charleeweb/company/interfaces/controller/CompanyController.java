package com.efa73.charleeweb.company.interfaces.controller;

import com.efa73.charleeweb.common.Api;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.service.CompanyService;
import com.efa73.charleeweb.company.interfaces.dto.CompanyRequest;
import com.efa73.charleeweb.company.interfaces.dto.CompanyResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("")
    public ResponseEntity<Api<CompanyResponse>> createCompany(@RequestBody CompanyRequest companyRequest) {

        Company company = companyService.createCompany(CompanyRequest.createEntity(companyRequest));
        CompanyResponse response = CompanyResponse.createResponse(company);

        return ResponseEntity.created(URI.create("/company/" + company.getId())).body(Api.of(response));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Api<CompanyResponse>> getCompany(@PathVariable Long companyId) {

        Company company = companyService.getCompany(companyId);

        CompanyResponse response = CompanyResponse.createResponse(company);

        return ResponseEntity.ok().body(Api.of(response));
    }
}
