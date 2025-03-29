package com.efa73.charleeweb.company.interfaces.controller;

import com.efa73.charleeweb.common.Api;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.service.CompanyService;
import com.efa73.charleeweb.company.interfaces.dto.request.CompanyCreateRequest;
import com.efa73.charleeweb.company.interfaces.dto.response.CompanyResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("")
    public ResponseEntity<Api<CompanyResponse>> createCompany(@RequestBody CompanyCreateRequest request) {
        Company company = companyService.createCompany(
                request.email(),
                request.password(),
                request.name()
        );

        CompanyResponse response = CompanyResponse.createDto(company);

        return ResponseEntity.created(URI.create("/api/company/" + company.getId()))
                .body(Api.of(response));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Api<CompanyResponse>> getCompany(@PathVariable Long companyId) {
        Company company = companyService.getCompany(companyId);

        CompanyResponse response = CompanyResponse.createDto(company);

        return ResponseEntity.ok()
                .body(Api.of(response));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Api<CompanyResponse>> updateCompany(@RequestBody CompanyCreateRequest request,
                                                              @PathVariable Long companyId) {
        Company updatedCompany = companyService.updateCompany(
                companyId,
                request.email(),
                request.password(),
                request.name()
        );

        CompanyResponse response = CompanyResponse.createDto(updatedCompany);

        return ResponseEntity.ok()
                .body(Api.of(response));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Api<CompanyResponse>> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);

        return ResponseEntity.noContent().build();
    }
}
