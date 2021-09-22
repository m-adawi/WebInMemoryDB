package WebInMemoryDB.WebInMemoryDB.config;

import DB.CommandGenerators.CommandsGenerator;
import DB.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Objects;

@Configuration
public class BeansConfig {
    @Autowired
    Environment env;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(Objects.requireNonNull(env.getProperty("datasource.driver-class-name")));
        ds.setUrl(env.getProperty("datasource.url"));
        ds.setUsername(env.getProperty("datasource.username"));
        ds.setPassword(env.getProperty("datasource.password"));
        return ds;
    }

    @Bean
    CommandsGenerator commandsGenerator() {
        return CommandsGenerator.getCommandGenerator();
    }

    @Bean
    Database database() {
        return Database.getDatabase();
    }
}
