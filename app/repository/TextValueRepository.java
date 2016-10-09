package repository;

import entity.TextValue;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by wangyiran on 30/9/2016.
 */
public interface TextValueRepository extends GraphRepository<TextValue>{
    @Query("match (t:Text),(u:User) where ID(t) = {textId} and ID(u) = {userId} create (tv:TextValue{value:{value}}),(t)-[:have]->(tv),(u)-[:fill]->(tv)")
    void create(@Param("textId") Long textId, @Param("value") String value, @Param("userId") Long userId);
}
