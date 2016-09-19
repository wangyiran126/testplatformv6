package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * Created by wangyiran on 2/9/2016.
 */
@NodeEntity
public class Department {
    @GraphId
    Long departId;

    @Relationship(type="have",direction = Relationship.OUTGOING)
    Set<Department> subDepartments;
    @JsonIgnore
    @Relationship(type = "belong",direction = Relationship.INCOMING)
    Department parentDepartments;
    String name;

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
    @Relationship(type = "belong",direction = Relationship.INCOMING)
    public Department getParentDepartments() {
        return parentDepartments;
    }

    public void setParentDepartments(Department parentDepartments) {
        this.parentDepartments = parentDepartments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
