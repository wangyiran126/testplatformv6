package service.archivesManage;

import entity.Department;
import modules.AppConfig;
import org.codehaus.jackson.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void testNotNull(){
       Department department=  archivesManageService.getHeritanceRootDepartment();
        Assert.assertNotNull(department);
    }

    @Test
    public void testcreateUserTypeExtHave(){
        doReturn
    }
}
