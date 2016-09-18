package collectivereport.factory;

import collectivereport.base.StatisticService;
import collectivereport.happinese.function.HappineseService;
import collectivereport.stresslevel.function.StressService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by wangyiran on 9/9/2016.
 */
public class ServiceFactory {
    private static Map<String,Supplier<StatisticService>> mapFactory = new HashMap<>();

    static {
        mapFactory.put("10003", HappineseService::new);
        mapFactory.put("10004", StressService::new);
    }
    private ServiceFactory() {

    }

    public static Optional<StatisticService> getByScaleCode(String scaleCode){
        return mapFactory.containsKey(scaleCode) ? Optional.of(mapFactory.get(scaleCode).get()): Optional.empty();
    }

    enum  CollectiveReportPath {
        Pressure("10003","pressureLevel"),//压力水平集体报告
        Happinese("10044","pressureLevel");
        CollectiveReportPath(String type, String path) {
            this.type = type;
            this.path = path;
        }
        private String type;
        private String path;

        public String getType() {
            return type;
        }

        public String getPath() {
            return path;
        }

        public static String getPath(String scaleCode) {
            for (CollectiveReportPath collectivePath :CollectiveReportPath.values()){
                if (collectivePath.getType().equals(scaleCode)){
                    return collectivePath.getPath();
                }
            }
            return "nonexistent";
        }

    }
}
