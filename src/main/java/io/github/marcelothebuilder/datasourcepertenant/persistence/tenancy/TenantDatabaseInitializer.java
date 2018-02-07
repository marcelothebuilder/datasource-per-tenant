package io.github.marcelothebuilder.datasourcepertenant.persistence.tenancy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@Slf4j
public class TenantDatabaseInitializer {
    @EventListener
    public void initialize(TenantDataSourceInitializedEvent e) throws SQLException {
        log.info("Initializing tenant database");

        DataSource ds = e.getDataSource();
        Connection con = ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(slurp("quicky-initialize-customer.sql"));
        stmt.execute();
    }

    private String slurp(String classpathFile) {
        try {
            URL resource = this.getClass().getClassLoader().getResource(classpathFile);
            Path path = Paths.get(resource.toURI());
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return String.join(" ", lines);
        } catch (Exception e) {
            throw new UncheckedIOException(new IOException(e));
        }
    }
}
