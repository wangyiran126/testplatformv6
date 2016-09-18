package dao;

import com.google.gson.Gson;
import entity.Department;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;
import org.neo4j.driver.v1.types.Entity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyiran on 6/9/2016.
 */
@Component
public class DepartmentDao extends BaseDao{
    public void result(){
        StatementResult result = session.run("match(p:Department) return type(p)");
        while (result.hasNext()){
            Record record = result.next();
            Entity entity =record.get("p").asEntity();
            System.out.println( entity.get("have"));
            System.out.println(entity.get("name").asString());
        }
    }
    public static void main(String[] args) {
        new DepartmentDao().result();

    }

    public List<Department> getDepartment(Long deptId) {
        StatementResult result = session.run("match(n:Department)-[r:belong*1..3]->(n2:Department) where ID(n) = {id}  return r",
                Values.parameters("id",deptId));
        Gson gson = new Gson();
        while (result.hasNext()){
            Record record = result.next();
            List<Object> entity =record.get("r").asList();
            gson.toJson(entity);
//            Entity entity =record.get("r").asEntity();
            System.out.println(gson);
        }
        return null;
    }
}
