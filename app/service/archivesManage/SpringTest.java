package service.archivesManage;

import entity.Checkbox;
import entity.Department;
import modules.AppConfig;
import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import  static org.mockito.Mockito.*;
/**
 * Created by wangyiran on 8/10/2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringTest {
    @Autowired
    private ArchivesManageService archivesManageService;

    @Mock
    private JsonNode checkboxMock;

    @Before
    public void init(){
//        MockitoAnnotations.initMocks(this);
        checkboxMock =  Mockito.mock(JsonNode.class);

    }

    @Test
    public void testNotNull(){
       Department department=  archivesManageService.getHeritanceRootDepartment();
        Assert.assertNotNull(department);
    }

    @Test
    public void testcreateUserTypeExtHave(){
//        doReturn("选项1").when(checkboxMock.get("name"));
//        doReturn("1").when(checkboxMock.get("value"));
//        doReturn("选项2").when(checkboxMock.get("name"));
//        doReturn("2").when(checkboxMock.get("value"));

        when(checkboxMock.get("name").asText()).thenReturn("选项1").thenReturn("选项2");
        when(checkboxMock.get("value").asText()).thenReturn("1").thenReturn("2");

        Assert.assertEquals("选项1",checkboxMock.get("name"));
        Assert.assertEquals("1",checkboxMock.get("value"));
        Assert.assertEquals("选项2",checkboxMock.get("name"));
        Assert.assertEquals("2",checkboxMock.get("value"));
    }
}
