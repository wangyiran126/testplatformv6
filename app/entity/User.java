package entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by wangyiran on 2/9/2016.
 */
@NodeEntity
public class User {
    @GraphId
    private Long id;
    private String name;

    private String account;
}
