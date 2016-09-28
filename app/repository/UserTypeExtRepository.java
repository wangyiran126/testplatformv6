package repository;

import entity.OptionValue;
import entity.UserTypeExt;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wangyiran on 23/9/2016.
 */
public interface UserTypeExtRepository extends GraphRepository<UserTypeExt> {
    /**
     * 存储checkbox关系
     * @param userTypeExtId
     * @param checkboxId
     */
    @Query("match(ute:UserTypeExt),(cb:Checkbox) where ID(ute) = {userTypeExtId} and ID(cb) = {checkboxId} create (ute)-[:checkbox]->(cb)")
    void saveCheckbox(@Param("userTypeExtId")Long userTypeExtId, @Param("checkboxId")Long checkboxId);


    /**
     * 创建用户类型扩展项和选项关系
     * @param userTypeExtId 用户扩展项id
     * @param optionValuesIds 选项id
     */
//    @Query("match (utx:UserTypeExt),(ov:OptionValue) where ID(utx)={userTypeExtId} and ID(ov) in {optionValuesIds} create (utx)-[:have]->(ov) return ov")
//    void saveHaveOption(@Param("userTypeExtId") Long userTypeExtId, @Param("optionValuesIds") List<Long> optionValuesIds);

}
