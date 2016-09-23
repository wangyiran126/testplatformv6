package entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by wangyiran on 21/9/2016.
 */
@NodeEntity
public class UserType {
    @GraphId
    private Long id;
    private String userTypeName;
    @Relationship(type = "contain",direction = Relationship.OUTGOING)
    Set<UserTypeExt> userTypeExts;

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public Set<UserTypeExt> getUserTypeExts() {
        return userTypeExts;
    }

    public void setUserTypeExts(Set<UserTypeExt> userTypeExts) {
        this.userTypeExts = userTypeExts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
