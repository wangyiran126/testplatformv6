package collectivereport.factory;

import java.util.Collections;

/**
 * Created by wangyiran on 10/8/2016.
 */
public enum CollectiveReportPath {
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
