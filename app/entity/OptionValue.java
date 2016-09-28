package entity;

/**
 * Created by wangyiran on 22/9/2016.
 */

import com.fasterxml.jackson.databind.JsonNode;
import entity.annotation.Unique;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类型选项和值
 */
@NodeEntity
public class OptionValue {
    //选项名
    private String name;
    //选项值 唯一
    @Unique
    private String value;
    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OptionValue() {
    }

    public OptionValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static List<OptionValue> create(JsonNode jsonNode) {
        List<OptionValue> optionValues = new ArrayList<>();
        jsonNode.elements().forEachRemaining(node->{
            String name = node.get("name").asText();
            String value = node.get("value").asText();
            optionValues.add(new OptionValue(name,value));
        });
        return optionValues;
    }
}
