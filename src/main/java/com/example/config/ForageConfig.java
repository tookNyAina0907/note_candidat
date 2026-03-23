package com.example.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.forage.DAO",
        entityManagerFactoryRef = "forageEntityManagerFactory",
        transactionManagerRef = "forageTransactionManager"
)
public class ForageConfig {

    @Bean(name = "forageDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.forage")
    public DataSource forageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "forageEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean forageEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("forageDataSource") DataSource forageDataSource) { // ← injecté
        return builder
                .dataSource(forageDataSource) // ← plus d'appel direct à la méthode
                .packages("com.example.forage.model")
                .persistenceUnit("forage")
                .build();
    }

    @Bean(name = "forageTransactionManager")
    public PlatformTransactionManager forageTransactionManager(
            @Qualifier("forageEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}