package io.github.marcelothebuilder.datasourcepertenant.web;

import io.github.marcelothebuilder.datasourcepertenant.model.common.Company;
import io.github.marcelothebuilder.datasourcepertenant.model.common.User;
import io.github.marcelothebuilder.datasourcepertenant.repository.common.CompanyRepository;
import io.github.marcelothebuilder.datasourcepertenant.repository.common.UserRepository;
import io.github.marcelothebuilder.datasourcepertenant.repository.customer.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@CrossOrigin
@Slf4j
public class CompanyResource {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    @Transactional
    public Iterable<Company> companies() {
        return companyRepository.findAll();
    }
}
