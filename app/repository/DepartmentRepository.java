package repository;

import entity.Department;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangyiran on 2/9/2016.
 */

public interface DepartmentRepository extends GraphRepository<Department> {
//    @Query("match(n:Department)-[r*]->(n2:Department) where ID(r) = {deptId}  return r")
    @Query("match(n:Department)-[r:belong*1]->(n2:Department) where ID(n) = {deptId}  return r")
    List<Department> getDepart(@Param("deptId")Long deptId);
//TODO 创建新节点关系
//    match(n:Department) where n.id = 5 create (n)-[r:have]->(b:Department{name:"2连"})
//match(n:Department),(m:Department) where ID(n)=11 and ID(m)=5 create (n)-[r:belong]->(m)
}
