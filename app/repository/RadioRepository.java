package repository;

import entity.Checkbox;
import entity.Radio;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wangyiran on 28/9/2016.
 */
public interface RadioRepository extends GraphRepository<Radio>{
//    @Query("match (r:Radio),(ov:OptionValue) where ID(r) = {radioId} and ID(ov) in {optionValueIds} create (r)-[:have]->(ov)")
//    void saveRadio(@Param("radioId") Long radioId,@Param("optionValueIds") List<Long> optionValueIds);
}
