package org.unicome.sample.hive;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class HiveConfig {

    private String url = "jdbc:hive2://192.168.88.100:10000/default";
    private String username = "root";
    private String password = "123456";
    private String driverClassName = "org.apache.hive.jdbc.HiveDriver";

    @Bean(name = "hiveDruidDataSource")
    @Qualifier("hiveDruidDataSource")
    public DruidDataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setFilters("stat");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(300);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setTestWhileIdle(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        return dataSource;
    }

    @Bean(name = "hiveTemplate")
    public JdbcTemplate hiveTemplate(@Qualifier("hiveDruidDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
