package repository;

import entity.UserType;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wangyiran on 22/9/2016.
 */
public interface UserTypeRepository extends GraphRepository<UserType> {

    @Query("match(ut:UserType),(ute:UserTypeExt) where ID(ut)={userTypeId} and ID(ute) in {userTypeExtsIds} create (ut)-[:have]->(ute)")
    void saveHaveUserTypeExt(@Param("userTypeId") Long userTypeId, @Param("userTypeExtsIds") List<Long> userTypeExtsIds);
}
