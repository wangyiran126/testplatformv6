package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * Created by wangyiran on 21/9/2016.
 */

/**
 * 创建索引管理service
 */
@Service
public class IndexService {
    @Autowired
    private Neo4jOperations neo4jOperations;

    @PostConstruct
    public void createIndexes(){
        neo4jOperations.query("CREATE CONSTRAINT ON (u:User) ASSERT u.account IS UNIQUE",new HashMap<>());
    }
}
