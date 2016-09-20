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
    @Query("match(n:Department)-[r:have*]<-(n2:Department) where ID(n) = {deptId}  return r")
    List<Department> getParentDepartment(@Param("deptId")Long deptId);

    @Query("match(d:Department) where ID(d)= {parentId}  create (add:Department{name:{name}}),(d)-[:have]->(add) return ID(add)")
    Long addDepartment(@Param("name")String name, @Param("parentId")Long parentId);

//    @Query("CALL apoc.create.node(['Department'], {name:{name}})")
//    void createDepartment(@Param("name") String name);

    @Query("create (d:Department {name:{name}}) return ID(d)")
    Long createDepartment(@Param("name") String name);

    @Query("match (d:Depratment)-[r]-(o) where ID(d)={deptId} return d")
    Department getDepartment(@Param("deptId") Long deptId);

    @Query("match (d:Department)-[r]-(o) return r")
    List<Department> getDepartments();

//TODO 创建新节点关系
//    match(n:Department) where n.id = 5 create (n)-[r:have]->(b:Department{name:"2连"})
//match(n:Department),(m:Department) where ID(n)=11 and ID(m)=5 create (n)-[r:belong]->(m)

    //删除关系
//    match(d:Department)-[r:have]->(d2:Department) where ID(d) = 5 and ID(d2)= 11 delete r

    //从Mongodb获取数据
//    call apoc.mongodb.get("localhost","testResult","testResult",null) yield value

//    TODO
    //组合neo4j数据，然后插入Mongodb
//    match (d:Department)<-[r:in]-(u:User) with {depart:d,user:u} as x  return x

}
