package repository;

import entity.Checkbox;
import entity.Text;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by wangyiran on 28/9/2016.
 */
public interface TextRepository extends GraphRepository<Text>{
}
