package com.psja.delivery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

@Configuration
public class AppConfig {

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/orchestra");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		
		return driverManagerDataSource;
	}
	
//	@Bean
//	LocalSessionFactoryBean entityManagerFactory() {
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		sessionFactoryBean.setDataSource( dataSource() );
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//		properties.setProperty("hibernate.show_sql", "true");
//		properties.setProperty("hibernate.hbm2ddl.auto", "update");
//		
//		sessionFactoryBean.setHibernateProperties(properties);
//		return sessionFactoryBean;
//	}
//	
//	@Bean
//	HibernateTransactionManager transactionManager() {
//		
//		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//		hibernateTransactionManager.setSessionFactory( entityManagerFactory().getObject() );
//		return hibernateTransactionManager;
//	}
}
