package repository;

import entity.Checkbox;
import entity.OptionValue;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by wangyiran on 28/9/2016.
 */
public interface OptionValueRepository extends GraphRepository<OptionValue>{
}
