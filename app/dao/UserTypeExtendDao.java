package dao;

import org.junit.Test;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.Values;

import java.util.Date;

/**
 * Created by wangyiran on 2/9/2016.
 */

public class UserTypeExtendDao extends BaseDao{
    public void save(String name,String type,String value) {
        String statement = "create (t:UserTypeExtend {name: {name},type:{type},value:{value}})";
        try (Transaction tx = session.beginTransaction()) {
            tx.run(statement, Values.parameters("name", name, "type", type, "value", value));
            tx.success();
        }
    }


    public static void main(String[] args) {
        new UserTypeExtendDao().save("生日","date",String.valueOf(new Date().getTime()));

    }
}
