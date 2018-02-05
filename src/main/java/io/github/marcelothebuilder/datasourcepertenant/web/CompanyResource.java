package io.github.marcelothebuilder.datasourcepertenant.web;

import io.github.marcelothebuilder.datasourcepertenant.model.Company;
import io.github.marcelothebuilder.datasourcepertenant.repository.config.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin
@Slf4j
public class CompanyResource {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public Iterable<Company> companies() {
        return companyRepository.findAll();
    }
}
