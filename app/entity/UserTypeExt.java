package entity;

import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * Created by wangyiran on 21/9/2016.
 */
@NodeEntity
public class UserTypeExt {
    //用户类型名
    private String name;
    //种类
    private String type;
    //选项和值
    private List<OptionValue> options;

    public List<OptionValue> getOptions() {
        return options;
    }

    public void setOptions(List<OptionValue> options) {
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
