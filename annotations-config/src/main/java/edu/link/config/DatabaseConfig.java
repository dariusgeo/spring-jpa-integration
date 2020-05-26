package edu.link.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:jpa-training.properties")
@EnableTransactionManagement
@EnableJpaRepositories("edu.link.jpa.dao")
public class DatabaseConfig {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driverClass";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "edu.link.jpa.model";
	
	@Resource
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig dataSource = new HikariConfig();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return new HikariDataSource(dataSource);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setPackagesToScan("edu.link.jpa.model");
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setJpaProperties(hibProperties());

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		return properties;
	}

	@Bean(name = "transactionManager")
	@Autowired
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

}
