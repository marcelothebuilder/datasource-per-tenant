package io.github.marcelothebuilder.datasourcepertenant;

import io.github.marcelothebuilder.datasourcepertenant.model.common.Company;
import io.github.marcelothebuilder.datasourcepertenant.model.common.User;
import io.github.marcelothebuilder.datasourcepertenant.repository.common.CompanyRepository;
import io.github.marcelothebuilder.datasourcepertenant.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class Startup {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener(classes = ContextRefreshedEvent.class)
    public void test() {
        log.info("INITIALIZING DATABASE!!");

        Company company = companyRepository.save(Company.builder()
                .name("MyCompany")
                .jdbcConnection("jdbc:h2:~/my-company")
                .build());

        User user = userRepository.save(User.builder()
                .name("Jhonson")
                .email("jhonson@gmail.com")
                .password(passwordEncoder.encode("12345678"))
                .company(company)
                .build());

        log.debug("User {}", user.getName());

    }
}
