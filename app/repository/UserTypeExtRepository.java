package repository;

import entity.OptionValue;
import entity.User;
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
     * 存储用户扩展类型和checkbox关系
     * @param userTypeExtId
     * @param checkboxId
     */
    @Query("match(ute:UserTypeExt),(cb:Checkbox) where ID(ute) = {userTypeExtId} and ID(cb) = {checkboxId} create (ute)-[:have]->(cb)")
    void saveCheckbox(@Param("userTypeExtId")Long userTypeExtId, @Param("checkboxId")Long checkboxId);

    @Query("match(ute:UserTypeExt),(t:Text) where ID(ute) = {userTypeExtId} and ID(t) = {textId} create (ute)-[:have]->(t)")
    void saveText(@Param("userTypeExtId") Long userTypeExtId, @Param("textId") Long textId);

    @Query("match (u:User)-[f:fill]->(ov:OptionValue),(u)-[:fill]->(tv:TextValue) with u,collect(ID(ov)) as ids,collect(tv.value) as tvs  WHERE ALL (v IN {optionValueIds} WHERE v IN ids) and ALL (v2 in {textValue} WHERE v2 IN tvs) return u")
//    @Query("match (u:User)-[:fill]->(ov:OptionValue),(u)-[:fill]->(tv:TextValue) where ID(ov) in {optionValueIds} and tv.value in {textValue} return u")
    List<User> filterUserType(@Param("textValue") List<String> textValue, @Param("optionValueIds") List<Long> optionValueIds);

    @Query("match (ute:UserTypeExt),(r:Radio) where ID(r) = {radioId} and ID(ute) in {userTypeExtId} create (ute)-[:have]->(r)")
    void saveRadio(@Param("userTypeExtId") Long userTypeExtId,@Param("radioId") Long radioId);


    /**
     * 创建用户类型扩展项和选项关系
     * @param userTypeExtId 用户扩展项id
     * @param optionValuesIds 选项id
     */
//    @Query("match (utx:UserTypeExt),(ov:OptionValue) where ID(utx)={userTypeExtId} and ID(ov) in {optionValuesIds} create (utx)-[:have]->(ov) return ov")
//    void saveHaveOption(@Param("userTypeExtId") Long userTypeExtId, @Param("optionValuesIds") List<Long> optionValuesIds);

}
