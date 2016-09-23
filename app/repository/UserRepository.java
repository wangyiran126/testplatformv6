package repository;

import entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by wangyiran on 21/9/2016.
 */
public interface UserRepository extends GraphRepository<User> {
    @Query("match (d:Department),(u:User) where ID(d)={departmentId} and ID(u)={userId} create (u)-[:in]->(d) return d,u")
    void addUserToDepartment(@Param("userId") Long userId, @Param("departmentId") Long departmentId);

    @Query("match (u:User),(ut:UserType),(d:Department) where ID(u) = {userId} and ID(ut) = userTypeId and ID(d) = {departmentId} create(u)-[h:have]->(ut),create (u)-[i:in]->(d)")
    void saveUserTypeAndDepartment(Long userId, Long userTypeId, Long departmentId);
}
