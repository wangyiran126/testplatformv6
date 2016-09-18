package modules;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by wangyiran on 8/9/2016.
 */
@org.springframework.context.annotation.Configuration
@ComponentScans({@ComponentScan("entity"),@ComponentScan("service"),@ComponentScan("dao")})
@EnableNeo4jRepositories(basePackages = "repository")
@EnableTransactionManagement

public class AppConfig extends Neo4jConfiguration {
    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("entity");
    }

//    @Bean
//    public Configuration getConfiguration() {
//        Configuration config = new Configuration();
//        config
//                .driverConfiguration()
//                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
//                .setCredentials("neo4j", "wangyiran126")
//                .setURI("http://localhost:7474");
//        return config;
//    }



}
