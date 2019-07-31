package com.shyam.properties.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource(value = { "classpath:persistence-mysql.properties" })
@Component
public class DatabaseProperties {

	@Autowired
	private Environment env;

	private String jdbcDriver;
	private String jdbcURL;
	private String mysqlDBUser;
	private String mysqlDBPassword;
	private String initialPoolSize;
	private String maxPoolSize;
	private String minPoolSize;
	private String maxIdleTime;
	private String mysqlDialect;
	private String showSQL;
	private String hibernateScanPackage;

	public String getJdbcDriver() {
		this.jdbcDriver = env.getProperty("jdbc.driver");
		return jdbcDriver;
	}

	public String getJdbcURL() {
		this.jdbcURL = env.getProperty("jdbc.url");
		return jdbcURL;
	}

	public String getMysqlDBUser() {
		this.mysqlDBUser = env.getProperty("jdbc.user");
		return mysqlDBUser;
	}

	public String getMysqlDBPassword() {
		this.mysqlDBPassword = env.getProperty("jdbc.password");
		return mysqlDBPassword;
	}

	public String getInitialPoolSize() {
		this.initialPoolSize = env.getProperty("connection.pool.initialPoolSize");
		return initialPoolSize;
	}

	public String getMaxPoolSize() {
		this.maxPoolSize = env.getProperty("connection.pool.maxPoolSize");
		return maxPoolSize;
	}

	public String getMinPoolSize() {
		this.minPoolSize = env.getProperty("connection.pool.minPoolSize");
		return minPoolSize;
	}

	public String getMaxIdleTime() {
		this.maxIdleTime = env.getProperty("connection.pool.maxIdleTime");
		return maxIdleTime;
	}

	public String getMysqlDialect() {
		this.mysqlDialect = env.getProperty("hibernate.dialect");
		return mysqlDialect;
	}

	public String getShowSQL() {
		this.showSQL = env.getProperty("hibernate.show_sql");
		return showSQL;
	}

	public String getHibernateScanPackage() {
		this.hibernateScanPackage = env.getRequiredProperty("hibernate.packagesToScan");
		return hibernateScanPackage;
	}
}
