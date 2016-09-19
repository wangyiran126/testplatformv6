package repository;

import entity.Department;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wangyiran on 2/9/2016.
 */
public interface DepartmentRepository extends GraphRepository<Department> {
//    @Query("match(n:Department)-[r*]->(n2:Department) where ID(r) = {deptId}  return r")
    @Query("match(n:Department)-[r:belong*]->(n2:Department) where ID(n) = {deptId}  return r")
    List<Department> getDepart(@Param("deptId")Long deptId);

    @Query("match(d:Department) where ID(d)= {parentId}  create (d2:Department{name:{name}}),(d)-[:have]->(d2),(d2)-[:belong]->(d) return ID(d2)")
    Long addDepartment(@Param("name")String name, @Param("parentId")Long parentId);

//    void moveDepartment(String movedId, Long parentId);

    @Query("CALL apoc.create.node(['Department'], {name:{name}})")
    void createDepartment(@Param("name") String name);

//TODO 创建新节点关系
//    match(n:Department) where n.id = 5 create (n)-[r:have]->(b:Department{name:"2连"})
//match(n:Department),(m:Department) where ID(n)=11 and ID(m)=5 create (n)-[r:belong]->(m)

    //删除关系
//    match(d:Department)-[r:have]->(d2:Department) where ID(d) = 5 and ID(d2)= 11 delete r

    //从Mongodb获取数据
//    call apoc.mongodb.get("localhost","testResult","testResult",null) yield value

}
