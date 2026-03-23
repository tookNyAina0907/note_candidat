package com.example.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.note.dao",
        entityManagerFactoryRef = "noteEntityManagerFactory",
        transactionManagerRef = "noteTransactionManager"
)
public class NoteConfig {

    @Primary
    @Bean(name = "noteDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.note")
    public DataSource noteDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "noteEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean noteEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("noteDataSource") DataSource noteDataSource) { // ← injecté
        return builder
                .dataSource(noteDataSource) // ← plus d'appel direct à la méthode
                .packages("com.example.note.model")
                .persistenceUnit("note")
                .build();
    }

    @Primary
    @Bean(name = "noteTransactionManager")
    public PlatformTransactionManager noteTransactionManager(
            @Qualifier("noteEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}