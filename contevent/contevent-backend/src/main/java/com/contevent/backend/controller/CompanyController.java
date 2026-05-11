package com.contevent.backend.controller;

import com.contevent.backend.model.Company;
import com.contevent.backend.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Map<String, String> body) {
        Company company = new Company();
        company.setName(body.get("name"));
        company.setDescription(body.get("description"));
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(company));
    }
}