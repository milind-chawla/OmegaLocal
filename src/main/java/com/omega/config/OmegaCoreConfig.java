package com.omega.config;

import java.io.File;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.omega.context.OmegaContextProvider;
import com.omega.repository.BookDao;
import com.omega.repository.BookDaoJpaImpl;
import com.omega.service.ActorService;
import com.omega.service.ActorServiceImpl;
import com.omega.service.BookService;
import com.omega.service.BookServiceImpl;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariDataSource;

import akka.actor.ActorSystem;

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement
public class OmegaCoreConfig {

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		        
        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        dataSource.setUsername("user1");
        dataSource.setPassword("user1");
        dataSource.setAutoCommit(false);
        dataSource.setConnectionTimeout(5000);
        dataSource.setIdleTimeout(600000);
        dataSource.setMaxLifetime(1800000);
        dataSource.setConnectionTestQuery("SELECT 1");
        dataSource.setMaximumPoolSize(10);
        dataSource.setInitializationFailFast(false);
        //dataSource.setIsolateInternalQueries(false);
        //dataSource.setAllowPoolSuspension(false);
        //dataSource.setReadOnly(false);
        //dataSource.setRegisterMbeans(false);
        dataSource.setCatalog("user1");
        //dataSource.setConnectionInitSql(null);
        //dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setValidationTimeout(10000);
        dataSource.setLeakDetectionThreshold(5000);
        
        return dataSource;
	}
	
	@Bean
	public AbstractJpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}
	
	@Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		
        emfb.setPersistenceUnitName("OmegaUnit1");
        emfb.setDataSource(dataSource());
        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setPackagesToScan("com.omega.domain");
        
        return emfb;
    }
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		
        try {
			txManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return txManager;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		jdbcTemplate.setDataSource(dataSource());
        
		return jdbcTemplate;
    }
	
	@Bean
	public String uploadDirectory() {
		return "";
	}
	
	@Bean
	public OmegaContextProvider omegaContextProvider() {
		return new OmegaContextProvider();
	}
	
	@Bean
	public ActorService actorService() {
		String configFile = getClass().getClassLoader().getResource("application.local.conf").getFile();
        Config config = ConfigFactory.parseFile(new File(configFile));
        
        return new ActorServiceImpl(ActorSystem.create("OmegaActorSystemLocal", config));
	}
	
	@Bean
	public BookDao bookDao() {
		return new BookDaoJpaImpl(actorService());
	}
	
	@Bean
	public BookService bookService() {
		return new BookServiceImpl(bookDao());
	}
}
