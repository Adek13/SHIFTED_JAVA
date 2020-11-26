package org.mahasiswa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(scanBasePackages = "org.mahasiswa")
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("StartApplication...");
        runJDBC();
    }

    void runJDBC() {

        log.info("Creating table mahasiswa...");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS mahasiswa ( " +
                "idmahasiswa INT NOT NULL AUTO_INCREMENT," +
                "fullname VARCHAR(50)," +
                "address VARCHAR(100)," +
                "status VARCHAR(30)," +
                "absensi INT," +
                "PRIMARY KEY (idmahasiswa)" +
                ")");

        log.info("Creating table details...");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS details (" +
                "iddetail INT NOT NULL AUTO_INCREMENT," +
                "idmahasiswa INT," +
                "physics INT," +
                "calculus INT," +
                "biologi INT," +
                "PRIMARY KEY (iddetail)" +
                ")");

    }
}
