package com.indianeagle.internal.config;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

class MySQLConfig {

    private static final String ENV = StringUtils.isNotBlank(System.getenv("ENV_SYSTEM")) ? System.getenv("ENV_SYSTEM") :(StringUtils.isNotBlank(System.getProperty("ENV_SYSTEM")) ? System.getProperty("ENV_SYSTEM") : "dev");

    @Autowired
    Environment environment;

    @Bean
    DataSource mySQLDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty("${ENV}.internal.datasource.url"));
        driverManagerDataSource.setUsername(environment.getProperty("${ENV}.internal.datasource.username"));
        driverManagerDataSource.setPassword(environment.getProperty("${ENV}.internal.datasource.password"));
        driverManagerDataSource.setDriverClassName(environment.getProperty("${ENV}.internal.datasource.driver-class-name"));
       return driverManagerDataSource;
    }

}
