package collectivereport.stresslevel.function;

import collectivereport.base.StatisticService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 9/9/2016.
 */
public class StressService implements StatisticService {
    Function<String,Double,String,Map<String,Object>> function = (a,b,c) ->{
        return new HashMap<String,Object>();
    };

    @Override
    public Map<String, Object> statistic(String scaleCode) {
        return null;
    }

    @FunctionalInterface
    interface Function<A,B,C,R>{
        public R apply(A a,B b,C c);
    }
    Map<String,Object> statistics(){
        return function.apply("1",2.2,"d");
    }
}
