package collectivereport.happinese.function;

import collectivereport.base.StatisticService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by wangyiran on 9/9/2016.
 */
public class HappineseService implements StatisticService{
    @Override
    public Map<String, Object> statistic(String scaleCode) {
        return null;
    }

    class A{

    }
    @Test
    public void testSupplier(){
        Supplier<A> supplier = A::new;
        A a = supplier.get();
        A b = supplier.get();
        Assert.assertEquals(a,b);
    }
}
