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
    Long id;

    @Relationship(type="have",direction = Relationship.OUTGOING)
    Set<Department> subDepartments;
    @Relationship(type = "belong",direction = Relationship.INCOMING)
    Department parentDepartments;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Department> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(Set<Department> subDepartments) {
        this.subDepartments = subDepartments;
    }

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
