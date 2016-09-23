package entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.cypher.query.SortOrder;

/**
 * Created by wangyiran on 2/9/2016.
 */
@NodeEntity
public class User {
    @GraphId
    private Long id;
    private String name;

    private String account;

    //用户的用户类型
    @Relationship(type = "have",direction = Relationship.OUTGOING)
    private UserType userType;

    //用户所属部门
    @Relationship(type = "in",direction = Relationship.OUTGOING)
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
