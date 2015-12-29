package io.pivotal.cf.cassandra.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    private CassandraOperations cassandraTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String cql = "create table if not exists person (id text, name text, age int, primary key(id))";
        cassandraTemplate.execute(cql);

        cql = "create table if not exists tickerHistory (id text , name text , ticker text , price text , pe text , recommendation text , yield text , primary key(id))";
        cassandraTemplate.execute(cql);
    }

}
