package org.dreamteam.onlineshop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

@Service
@Profile("!test")
public class DatabaseInitService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Value("classpath:data.sql")
    private org.springframework.core.io.Resource dataScript;

    public DatabaseInitService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public void initializeDatabase() {
        Long productCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products", Long.class);
        if (productCount == null) {
            productCount = 0L;
        }
        if (productCount == 0) {
            try (Reader reader = new InputStreamReader(dataScript.getInputStream())) {
                ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(), dataScript);
            } catch (Exception e) {
                throw new RuntimeException("Error executing SQL script", e);
            }
        }

//        Long adminCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users WHERE user_role = 'ADMIN'", Long.class);
//        if (adminCount == null) {
//            adminCount = 0L;
//        }
//        if (adminCount == 0) {
//            jdbcTemplate.update("INSERT INTO users (user_name, user_password, user_email, user_address, user_role) VALUES (?, ?, ?, ?, ?)",
//                    "admin", "admin123", "admin@example.com", "Admin Address", "ADMIN");
//        }
    }
}

