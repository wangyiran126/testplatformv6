package repository;

import entity.TextValue;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by wangyiran on 30/9/2016.
 */
public interface TextValueRepository extends GraphRepository<TextValue>{
    @Query("match (t:Text),(u:User) where ID(t) = {textId} and ID(u) = userId create (tx:TextValue{value:{value}}),tx-[:fill]->(t),(u)-[:fill]->(tx)")
    void create(Long textId, String value, Long userId);
}
