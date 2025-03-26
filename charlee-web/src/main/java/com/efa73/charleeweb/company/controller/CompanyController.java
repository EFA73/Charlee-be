package com.efa73.charleeweb.company.controller;

import com.efa73.charleeweb.company.dto.CompanyRequest;
import com.efa73.charleeweb.company.dto.CompanyResponse;
import com.efa73.charleeweb.company.entity.Company;
import com.efa73.charleeweb.company.service.CompanyService;
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

    @PostMapping("/")
    public ResponseEntity<?> createCompany(@RequestBody CompanyRequest companyRequest) {

        String response = companyService.createCompany(CompanyRequest.createEntity(companyRequest));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable Long companyId) {

        Company company = companyService.getCompany(companyId);

        CompanyResponse response = CompanyResponse.createResponse(company);

        return ResponseEntity.ok().body(response);
    }
}
