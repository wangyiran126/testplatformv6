package service.archivesManage;

import entity.Department;
import entity.User;
import entity.UserTypeExt;
import modules.AppConfig;
import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;
/**
 * Created by wangyiran on 8/10/2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringTest {
    @Autowired
    private ArchivesManageService archivesManageService;

    @Mock
    private JsonNode jsonNode;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
//        checkboxMock =  Mockito.mock(Map.class);


    }

    @Test
    public void testJsonnode(){
       when(jsonNode.asText()).thenReturn("name");
        Assert.assertEquals("name",jsonNode.asText());

    }

    @Test
    public void testNotNull(){
       Department department=  archivesManageService.getHeritanceRootDepartment();
        Assert.assertNotNull(department);
    }

    @Test
    public void testcreateUserTypeExtHave(){
        //添加checkbox用户扩展信息
//        Map checkboxMock = new HashMap<>();
//        checkboxMock.put("男","1");
//        checkboxMock.put("女","0");
//        archivesManageService.createUserTypeExtHave(13l, UserTypeExt.RelationShip.CHECKBOX.getType(),checkboxMock,"性别");
        //添加text用户扩展信息
//        archivesManageService.createUserTypeExtHave(13l, UserTypeExt.RelationShip.TEXT.getType(),null,"说明");
        //添加radio用户扩展信息
        Map radioMap = new HashMap<>();
        radioMap.put("90后","1");
        radioMap.put("80后","0");
        archivesManageService.createUserTypeExtHave(13l,UserTypeExt.RelationShip.SINGLERADIO.getType(),radioMap,"年龄范围");
    }

//    @Test
//    public void testaddUserType(){
//        archivesManageService.addUserType("用户类型2", Arrays.asList(13l));
//    }

    @Test
    public void testaddUserTypeExtOfUser(){
        //添加用户checkbox选项
//        Map<Object,Object> params = new HashMap<>();
//        params.put(35l,null);
//        archivesManageService.addUserTypeExtOfUser(UserTypeExt.RelationShip.CHECKBOX.getType(),params,15l);
        //添加用户text选项
//        Map<Object,Object> params = new HashMap<>();
//        params.put(36l,"南无阿弥陀佛");
//        archivesManageService.addUserTypeExtOfUser(UserTypeExt.RelationShip.TEXT.getType(),params,115l);
        //添加用户radio选项
        Map<Object,Object> params = new HashMap<>();
        params.put(1l,"");
        archivesManageService.addUserTypeExtOfUser(UserTypeExt.RelationShip.SINGLERADIO.getType(),params,115l);
    }

    @Test
    public void testaddUserType(){
         archivesManageService.addUserType(13l, Arrays.asList(51l));
    }

    @Test
    public void testcreateUserTypeExt(){
        archivesManageService.createUserTypeExt("年龄范围");
    }

    /**
     * 测试过滤用户类型选项
     */
    @Test
    public void testfilterUserType(){
        List<String> textValue = Arrays.asList("南无阿弥陀佛");
        List<Long> checkboxIds = Arrays.asList(119l,30l,1l);
        List<User> filteredUser = archivesManageService.filterUserType(textValue,checkboxIds);
        Assert.assertTrue(filteredUser.size()>0);
    }
}
