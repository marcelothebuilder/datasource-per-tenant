package io.github.marcelothebuilder.datasourcepertenant.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/probe")
@CrossOrigin
@Slf4j
public class ProbeResource {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void ok() {
        log.info("Responding OK");
    }
}
