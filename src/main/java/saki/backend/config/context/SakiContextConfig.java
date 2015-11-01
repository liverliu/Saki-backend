package saki.backend.config.context;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by liverliu on 15/10/31.
 */
@Configuration
@ComponentScan("saki.backend")
@EnableScheduling
@EnableTransactionManagement
@PropertySource("classpath:/properties/c3p0.properties")
public class SakiContextConfig {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("c3p0.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("c3p0.url"));
        dataSource.setUser(env.getProperty("c3p0.username"));
        dataSource.setPassword(env.getProperty("c3p0.password"));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("c3p0.maxIdleTime")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.maxPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.minPoolSize")));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("c3p0.initialPoolSize")));
        dataSource.setPreferredTestQuery(env.getProperty("c3p0.preferredTestQuery"));
        dataSource.setTestConnectionOnCheckin(Boolean.parseBoolean(env.getProperty("c3p0.testConnectionOnCheckin")));
        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("c3p0.idleConnectionTestPeriod")));
        dataSource.setMaxStatements(Integer.parseInt(env.getProperty("c3p0.maxStatements")));
        dataSource.setMaxStatementsPerConnection(Integer.parseInt(env.getProperty("c3p0.maxStatementsPerConnection")));
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(
            DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/sqlMapConfig.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/*Mapper.xml"));
        return factoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

}
