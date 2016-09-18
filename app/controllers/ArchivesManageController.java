package controllers;

import collectivereport.base.StatisticService;
import collectivereport.factory.ServiceFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Department;
import org.springframework.transaction.annotation.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.archivesManage.ArchivesManageService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by wangyiran on 29/8/2016.
 */
public class ArchivesManageController extends Controller {
    @Inject
    private ArchivesManageService archivesManageService;


    public Result test(String scaleCode,Integer code){
        Optional<StatisticService> optional = ServiceFactory.getByScaleCode(scaleCode);
        if (optional.isPresent()){
        StatisticService statisticService = optional.get();
        statisticService.statistic(scaleCode);
        }

        ObjectNode objectNode = Json.newObject();
        objectNode.put("scaleCode",scaleCode);
        objectNode.put("code",code);
        return ok(objectNode);
    }


    public Result test2(Integer scaleCode){
        return ok(String.valueOf(scaleCode));
    }

    public Result getDepartment(Long deptId ){
        Department department = archivesManageService.getDepartment(deptId);
        JsonNode jsonNode = Json.toJson(department);
        return ok(jsonNode);
    }


    public Result saveDepartment(String name){
        Long deptId = archivesManageService.saveDepartment(name);
        JsonNode jsonNode = Json.toJson(deptId);

        return ok(jsonNode);
    }


}
