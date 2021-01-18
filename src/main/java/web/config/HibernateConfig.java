package web.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "web")
@EnableTransactionManagement
@PropertySource(value = "classpath:app.properties")
//@EnableJpaRepositories("web.repository")
public class HibernateConfig {
    private Environment environment;

    @Autowired
    @Resource
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("PROP_DB_DRIVER"));
        dataSource.setUrl(environment.getRequiredProperty("PROP_DB_URL"));
        dataSource.setUsername(environment.getRequiredProperty("PROP_DB_USER"));
        dataSource.setPassword(environment.getRequiredProperty("PROP_DB_PASSWORD"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("PROP_ENTITYMANAGER_PACKAGES_TO_SCAN"));

        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("PROP_HIBERNATE_DIALECT", environment.getRequiredProperty("PROP_HIBERNATE_DIALECT"));
        properties.put("PROP_HIBERNATE_SHOW_SQL", environment.getRequiredProperty("PROP_HIBERNATE_SHOW_SQL"));
        properties.put("PROP_HIBERNATE_HBM2DDL_AUTO", environment.getRequiredProperty("PROP_HIBERNATE_HBM2DDL_AUTO"));
        properties.put("PROP_ENTITYMANAGER_PACKAGES_TO_SCAN", environment.getRequiredProperty("PROP_ENTITYMANAGER_PACKAGES_TO_SCAN"));
        return properties;
    }
}
