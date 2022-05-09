package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
    @PropertySource("classpath:db.properties")
    @EnableTransactionManagement
    @ComponentScan(value = "app")
    public class HiberConfig {

        @Autowired
        private Environment env;

        @Bean
        public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("db.driver"));
            dataSource.setUrl(env.getProperty("db.url"));
            dataSource.setUsername(env.getProperty("db.username"));
            dataSource.setPassword(env.getProperty("db.password"));
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            LocalContainerEntityManagerFactoryBean entityManager
                    = new LocalContainerEntityManagerFactoryBean();
            entityManager.setDataSource(getDataSource());
            entityManager.setPackagesToScan(new String[]{"app.model"});
            entityManager.setJpaVendorAdapter(vendorAdapter);
            entityManager.setJpaProperties(additionalProperties());
            return entityManager;
        }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("entitymanager.packages.to.scan", "app.model");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("db.characterEncoding", "utf8");
        return properties;
    }

        @Bean
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
            return transactionManager;
        }

    }

