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
    /**
     * 存储check和选项关系
     * @param checkboxId checkboxid
     * @param optionValueIds 选项id
     */
    @Query("match(c:Checkbox),(ov:OptionValue) where ID(c)= {checkboxId} and ID(ov) in {optionValueIds} create (c)-[:have]->(ov)")
    void saveOption(@Param("checkboxId") Long checkboxId, @Param("optionValueIds") List<Long> optionValueIds);

    /**
     * 用户选中的check
     * @param checkIds
     * @param userId
     */
    @Query("match (u:User),(ov:OptionValue) where ID(u) = {userId} and ID(ov) = {checkIds} create (u)-[:fill]->(ov)")
    void createChecked(@Param("checkIds") List<Long> checkIds, @Param("userId") Long userId);
}
