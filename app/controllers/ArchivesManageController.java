package controllers;

import collectivereport.base.StatisticService;
import collectivereport.factory.ServiceFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Department;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.archivesManage.ArchivesManageService;

import javax.inject.Inject;
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

//    public Result getDepartment(Long deptId ){
//        Department department = archivesManageService.getDepartment(deptId);
//        JsonNode jsonNode = Json.toJson(department);
//        return ok(jsonNode);
//    }


    public Result saveDepartment(String name){
        Long deptId = archivesManageService.saveDepartment(name);
        JsonNode jsonNode = Json.toJson(deptId);

        return ok(jsonNode);
    }


    public Result deleteDepartment(Long deptId){
         archivesManageService.deleteDepartment(deptId);
        return ok();
    }

    /**
     * 获取部门结构
     * @return
     */
    public Result getHeritanceRootDepartment(){
        Department departments = archivesManageService.getHeritanceRootDepartment();
        JsonNode jsonNode = Json.toJson(departments);

        return ok(jsonNode);
    }

    /**
     * 添加部门
     * @param name 部门名称
     * @param parentId 副部门id
     * @return
     */
    public Result addDepartment(String name,Long parentId){
        Long id = archivesManageService.addDepartment(name,parentId);
        JsonNode jsonNode = Json.toJson(id);
        return ok(jsonNode);
    }

    public Result moveDepartment(Long movedId,Long parentId){
        archivesManageService.moveDepartment(movedId,parentId);
        return ok();
    }

    public Result createDepartment(String name){
        Long id =  archivesManageService.createDepartment(name);
        JsonNode jsonNode = Json.toJson(id);
        return ok(jsonNode);
    }

//    public Result addPerson(Long deptId,String username,)

}
