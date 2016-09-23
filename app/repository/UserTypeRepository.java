package repository;

import entity.UserType;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by wangyiran on 22/9/2016.
 */
public interface UserTypeRepository extends GraphRepository<UserType> {
}
