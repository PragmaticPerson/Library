package edu.donstu.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootConfiguration
@EnableTransactionManagement
@ComponentScan("edu.donstu.dao")
public class HibernateConfiguration {
    private DataSource dataSource;
    private Environment environment;

    @Autowired
    HibernateConfiguration(DataSource dataSource, Environment environment) {
        this.dataSource = dataSource;
        this.environment = environment;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource);
        sf.setPackagesToScan("edu.donstu.service.models");
        sf.setHibernateProperties(hibernateProperties());
        return sf;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sf) {
        var manager = new HibernateTransactionManager();
        manager.setSessionFactory(sf);
        return manager;
    }

    private Properties hibernateProperties() {
        var properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));
        return properties;
    }
}
