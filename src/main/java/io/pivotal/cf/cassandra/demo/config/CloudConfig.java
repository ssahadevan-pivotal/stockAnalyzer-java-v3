package io.pivotal.cf.cassandra.demo.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;

@Configuration
public class CloudConfig extends AbstractCloudConfig {

    @Bean
    public CassandraSessionFactoryBean session() {
        return connectionFactory().service(CassandraSessionFactoryBean.class);
    }
}
