package repository;

import entity.Checkbox;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wangyiran on 28/9/2016.
 */
public interface CheckboxRepository extends GraphRepository<Checkbox>{
    @Query("match(c:Checkbox),(ov:OptionValue) where ID(c)= {checkboxId} and ID(ov) in {optionValueIds} create (c)-[:have]->(ov)")
    void saveOption(@Param("checkboxId") Long checkboxId, @Param("optionValueIds") List<Long> optionValueIds);
}
