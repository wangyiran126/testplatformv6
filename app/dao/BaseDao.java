package dao;

import com.google.inject.Singleton;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

/**
 * Created by wangyiran on 2/9/2016.
 */
@Singleton
public class BaseDao {
    Driver driver = GraphDatabase.driver( "bolt://localhost", AuthTokens.basic( "neo4j", "wangyiran126" ) );
    Session session = driver.session();
}
