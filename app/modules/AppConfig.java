package modules;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by wangyiran on 8/9/2016.
 */
@Configuration
@ComponentScans({@ComponentScan("entity"),@ComponentScan(basePackages = "service")})
@EnableNeo4jRepositories(basePackages = "repository")
@EnableTransactionManagement

public class AppConfig extends Neo4jConfiguration {
    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("entity");
    }

//    @Bean
//    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public Session getSession() throws Exception {
//        return super.getSession();
//    }


}
