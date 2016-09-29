package entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by wangyiran on 28/9/2016.
 */
@NodeEntity
public class Text {
    //选项名
    String name;
    @Relationship(direction = "fill",type = Relationship.INCOMING)
    List<TextValue> textValues;

    @GraphId
    private Long id;

    public List<TextValue> getTextValues() {
        return textValues;
    }

    public void setTextValues(List<TextValue> textValues) {
        this.textValues = textValues;
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

}
