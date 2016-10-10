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
    /**
     * 存储radio和选项的关系
     * @param radioId
     * @param optionValueIds
     */
    @Query("match (r:Radio),(ov:OptionValue) where ID(r)= {radioId} and ID(ov) in {optionValueIds} create (r)-[:have]->(ov)")
    void saveOption(@Param("radioId") Long radioId, @Param("optionValueIds") List<Long> optionValueIds);

    @Query("match (ov:OptionValue),(u:User) where ID(ov) = {optionValueId} and ID(u)={userId} create (u)-[:fill]->(ov)")
    void createSelected(@Param("optionValueId") Long optionValueId, @Param("userId") Long userId);
//    @Query("match (r:Radio),(ov:OptionValue) where ID(r) = {radioId} and ID(ov) in {optionValueIds} create (r)-[:have]->(ov)")
//    void saveRadio(@Param("radioId") Long radioId,@Param("optionValueIds") List<Long> optionValueIds);
}
