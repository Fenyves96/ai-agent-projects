package com.contevent.backend.service;

import com.contevent.backend.model.Company;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CompanyService {

    private final Map<Long, Company> companies = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Optional<Company> findById(Long id) {
        return Optional.ofNullable(companies.get(id));
    }

    public Company create(Company company) {
        company.setId(idCounter.getAndIncrement());
        companies.put(company.getId(), company);
        return company;
    }
}