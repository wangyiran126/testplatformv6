package controllers.archiveManage;

import collectivereport.base.StatisticService;
import collectivereport.factory.ServiceFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.*;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;
import service.archivesManage.ArchivesManageService;

import javax.inject.Inject;
import java.util.*;

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

    /**
     * 移动部门 movedDeptId到toDeptId下
     * @param movedDeptId
     * @param toDeptId
     * @return
     */
    public Result moveDepartment(Long movedDeptId,Long toDeptId){
        archivesManageService.moveDepartment(movedDeptId,toDeptId);
        return ok();
    }

    public Result createDepartment(String name){
        Long id =  archivesManageService.createDepartment(name);
        JsonNode jsonNode = Json.toJson(id);
        return ok(jsonNode);
    }

    /**
     * 添加用户
     * @param name
     * @param account
     * @return
     */
    public Result addUser(String name,String account){
        Long userId = archivesManageService.addUser(name,account);
        JsonNode jsonNode = Json.toJson(userId);
        return ok(jsonNode);
    }

    /**
     * 添加用户到部门
     * @param userId
     * @param departmentId
     * @return
     */
    public Result addUserToDepartment(Long userId,Long departmentId){
        archivesManageService.addUserToDepartment(userId,departmentId);
        return ok();
    }

    /**
     * 添加用户类型和扩展项
     * TODO
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result addUserType(){
        JsonNode json = request().body().asJson();
        //用户类型名
        String userTypeName = json.get("userTypeName").asText();
        //用户扩展ids
        JsonNode userTypeExtJsonNode = json.get("userExtIds");
        List<Long> userTypeExtIds = new ArrayList<>();
        userTypeExtJsonNode.elements().forEachRemaining(node->{
            userTypeExtIds.add(node.asLong());
        });
        Long userTypeId = archivesManageService.addUserType(userTypeName,userTypeExtIds);
        return ok();

    }

    /**
     * 创建一个用户扩展选项关系
     * TODO
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result createUserTypeExtHave(){
        JsonNode json = request().body().asJson();
        //用户扩展项名
        Long userTypeExtId = json.get("userTypeExtId").asLong();
        //扩展项类型
        String type = json.get("type").asText();
        //选项
        JsonNode jsonNode = json.get("optionValues");
        String checkName = json.get("checkName").asText();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.convertValue(jsonNode, Map.class);
        archivesManageService.createUserTypeExtHave(userTypeExtId,type,params,checkName);
        return ok();
    }

    /**
     * 添加用户类型扩展的页面
     */
    public Result createUserTypeExtHtml(){
        Content html =  views.html.archiveManage.createUserTypeExt.render();
        return ok(html);
    }

    /**
     * 添加用户类型的页面
     */
    public Result addUserTypeHtml(){
        Content html = views.html.archiveManage.addUserType.render();
        return ok(html);
    }

    /**
     * 创建用户，添加信息
     */
    public Result createUser(){
        JsonNode json = request().body().asJson();
        //账户名称
        String account = json.get("account").asText();
        //用户名
        String userName = json.get("name").asText();
        //用户类型id
        Long userTypeId = json.get("userTypeId").asLong();
        //部门id
        Long departmentId = json.get("departmentId").asLong();

       Long userId =  archivesManageService.createUser(account,userName,userTypeId,departmentId);
        JsonNode jsonNode1 = Json.toJson(userId);
        return ok(jsonNode1);
    }

    /**
     * 创建用户，添加信息
     */
    public Result createUserHtml(){
        Content html = views.html.archiveManage.createUser.render();
        return ok(html);
    }

    /**
     * 获取用户信息
     * @return
     */
    public Result getUserInfo(Long userId){
        User userInfo = archivesManageService.getUserInfo(userId);
        JsonNode jsonNode1 = Json.toJson(userInfo);
        return ok();
    }

    /**
     * 添加用户的用户类型和扩展信息页面
     * @return
     */
    public Result addUserTypeOfUserHtml(){
        Content html = views.html.archiveManage.addUserTypeOfUserHtml.render();
        return ok(html);
    }

    public Result createUserTypeExt(String userTypeExtName){
        Long extId = archivesManageService.createUserTypeExt(userTypeExtName);
        JsonNode jsonNode1 = Json.toJson(extId);
        return ok(jsonNode1);
    }


    //TODO 怎么把用户类型复制到用户

    /**
     * 添加用户的用户类型扩展信息
     * @return
     */
    public Result addUserTypeExtOfUser(){
        JsonNode json = request().body().asJson();
//        Long userTypeId = json.get("userTypeId").asLong();
//        //用户类型扩展id
//        Long userTypeExtId = json.get("userTypeExtId").asLong();
//        //扩展类型
        String type = json.get("type").asText();
//        Long valueId = json.get("valueId").asLong();
        JsonNode jsonNode = json.get("value");
        Long userId = json.get("userId").asLong();
        ObjectMapper mapper = new ObjectMapper();

        Map<Object, Object> params = mapper.convertValue(jsonNode, Map.class);
        archivesManageService.addUserTypeExtOfUser(type,params,userId);
            return null;
    }
}
