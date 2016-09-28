package entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by wangyiran on 21/9/2016.
 */
@NodeEntity
public class UserTypeExt {
    @GraphId
    private Long id;
    //用户类型名
    private String name;

    //文本选项
    @Relationship(type = "text",direction = Relationship.OUTGOING)
    private List<Text> textOptions;
    //单选项
    @Relationship(type = "singleRadio",direction = Relationship.OUTGOING)
    private List<Radio> singleRadios;

    //单选项
    @Relationship(type = "checkbox",direction = Relationship.OUTGOING)
    private List<Checkbox> checkboxes;

    @Relationship(type = "select")
    private List<Select> selects;

    @Relationship(type = "singleDate",direction = Relationship.OUTGOING)
    private List<SingleDate> singleDates;

    @Relationship(type = "doubleDate",direction = Relationship.OUTGOING)
    private List<DoubleDate> doubleDates;

    public enum RelationShip{
        TEXT("text"),SINGLERADIO("singleRadio"),CHECKBOX("checkbox"),SELECT("select"),SINGLEDATES("singleDate"),DOUBLEDATES("doubleDate");
        private String type;

        RelationShip(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
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

    public List<Text> getTextOptions() {
        return textOptions;
    }

    public void setTextOptions(List<Text> textOptions) {
        this.textOptions = textOptions;
    }

    public List<Radio> getSingleRadios() {
        return singleRadios;
    }

    public void setSingleRadios(List<Radio> singleRadios) {
        this.singleRadios = singleRadios;
    }

    public List<Checkbox> getCheckboxes() {
        return checkboxes;
    }

    public void setCheckboxes(List<Checkbox> checkboxes) {
        this.checkboxes = checkboxes;
    }

    public List<Select> getSelects() {
        return selects;
    }

    public void setSelects(List<Select> selects) {
        this.selects = selects;
    }

    public List<SingleDate> getSingleDates() {
        return singleDates;
    }

    public void setSingleDates(List<SingleDate> singleDates) {
        this.singleDates = singleDates;
    }

    public List<DoubleDate> getDoubleDates() {
        return doubleDates;
    }

    public void setDoubleDates(List<DoubleDate> doubleDates) {
        this.doubleDates = doubleDates;
    }
}
