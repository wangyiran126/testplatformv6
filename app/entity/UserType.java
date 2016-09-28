package entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangyiran on 21/9/2016.
 */
@NodeEntity
public class UserType {
    @GraphId
    private Long id;
    //用户类型名
    private String userTypeName;
    //用户类型扩展
    @Relationship(type = "have",direction = Relationship.OUTGOING)
    Set<UserTypeExt> userTypeExts = new HashSet<>();




    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
