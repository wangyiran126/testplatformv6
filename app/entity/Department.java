package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by wangyiran on 2/9/2016.
 */
@NodeEntity
public class Department {
    public static final String RootName = "init";

    public String getRootName() {
        return RootName;
    }

    @GraphId
    Long departId;


    @Relationship(type="have",direction = Relationship.OUTGOING)
    Set<Department> subDepartments;


    @JsonIgnore
    @Relationship(type = "have",direction = Relationship.INCOMING)
    Department parentDepartment;


    @JsonManagedReference
    @Relationship(type = "in",direction = Relationship.INCOMING)
    Set<User> users;

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {

        this.departId = departId;
    }
    public Set<Department> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(Set<Department> subDepartments) {
        this.subDepartments = subDepartments;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;


}
