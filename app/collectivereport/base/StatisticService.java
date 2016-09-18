package collectivereport.base;

import java.util.Map;

/**
 * Created by wangyiran on 9/9/2016.
 */
public interface StatisticService {
    public Map<String,Object> statistic(String scaleCode);
}
