package service.archivesManage;

import com.fasterxml.jackson.databind.JsonNode;
import entity.*;
import org.neo4j.ogm.exception.CypherException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by wangyiran on 6/9/2016.
 */
@Service
public class ArchivesManageService {
    @Inject
    private DepartmentRepository departmentRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private UserTypeRepository userTypeRepository;
    @Inject
    private UserTypeExtRepository userTypeExtRepository;
    @Inject
    private OptionValueRepository optionValueRepository;
    @Inject
    private CheckboxRepository checkboxRepository;
    @Inject
    private DoubleDateRepository doubleDateRepository;
    @Inject
    private RadioRepository radioRepository;
    @Inject
    private SelectRepository selectRepository;
    @Inject
    private SingleDateRepository singleDateRepository;
    @Inject
    private TextRepository textRepository;
    @Inject
    private TextValueRepository textValueRepository;

    public DepartmentRepository getDepartmentRepository() {
        return departmentRepository;
    }


    @Transactional
    public Long saveDepartment(String name) {
        Department department = new Department();
//        department.setId(1l);
        department.setName(name);
        Department saveed = departmentRepository.save(department);
        return saveed.getDepartId();
    }

    @Transactional
    public void deleteDepartment(Long deptId) {
        departmentRepository.delete(deptId);
    }

    @Transactional
    public Department getHeritanceRootDepartment() {
        List<Department> departments =  departmentRepository.getDepartments();
        Optional<Department> optional = departments.stream().filter(t->Department.RootName.equals(t.getName())).findAny();
        if (optional.isPresent()) return optional.get();
        else return null;

    }

    @Transactional
    public Long addDepartment(String name, Long parentId) {
        return departmentRepository.addDepartment(name,parentId);
    }

    public Long createDepartment(String name) {
        return departmentRepository.createDepartment(name);
    }

    public void moveDepartment(Long movedDeptId,Long toDeptId) {
        departmentRepository.moveDepartment(movedDeptId,toDeptId);
    }

    public Long addUser(String name, String account) {
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user = userRepository.save(user);
        return user.getId();
    }

    /**
     * 添加用户到部门
     * @param userId
     * @param departmentId
     */
    public void addUserToDepartment(Long userId, Long departmentId) {
        userRepository.addUserToDepartment(userId,departmentId);
    }

    /**
     * 添加用户类型和扩展项
     * @param userTypeName
     * @param userTypeExtsIds
     * @return
     */
    @Transactional
    public Long addUserType(String userTypeName, List<Long> userTypeExtsIds) {
        //保存用户类型
        UserType userType = new UserType();
        userType.setUserTypeName(userTypeName);
        userTypeRepository.save(userType);
        //TODO 保持用户类型有用户扩展类型关系
        userTypeRepository.saveHaveUserTypeExt(userType.getId(),userTypeExtsIds);
        return userType.getId();
    }


    /**
     * 创建用户
     * @param account 账户名
     * @param userName  用户名
     * @param userTypeId   用户类型id
     * @param departmentId 所属部门id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(String account, String userName, Long userTypeId, Long departmentId) {
        //TODO 不回滚
        Department department = new Department();
        department.setName("444444");
        departmentRepository.save(department);
        User user = new User();
        user.setAccount(account);
        user.setName(userName);
        userRepository.save(user);
        Long userId = user.getId();
        userRepository.saveUserTypeAndDepartment(userId,userTypeId,departmentId);
        return userId;
    }

    public User getUserInfo(Long userId) {
        return userRepository.findOne(userId);
    }

    /**
     * 创建用户类型和扩展项
     * @param userTypeExtId
     * @param type
     * @param jsonNode
     * @param checkName
     */
    @Transactional
    public void createUserTypeExtHave(Long userTypeExtId, String type, JsonNode jsonNode, String checkName) {
        //处理扩展的选项
        if (UserTypeExt.RelationShip.CHECKBOX.getType().equals(type)){//如果是checkbox
            //存储选项对象
            List<OptionValue> optionValues = new ArrayList<>();
            jsonNode.elements().forEachRemaining(t->{
                String name = t.get("name").asText();//checkbox名
                String value = t.get("value").asText();//checkbox值
                OptionValue optionValue = new OptionValue();
                optionValue.setName(name);
                optionValue.setValue(value);
                optionValues.add(optionValue);
            });

            optionValueRepository.save(optionValues);
            List<Long> optionValueIds = new ArrayList<>();
            optionValues.forEach(t->{
                optionValueIds.add(t.getId());
            });

            //存储checkbox对象
            Checkbox checkbox = new Checkbox();
            checkbox.setName(checkName);
            checkboxRepository.save(checkbox);

            Long checkboxId = checkbox.getId();
            //存储关系
            checkboxRepository.saveOption(checkboxId,optionValueIds);
            userTypeExtRepository.saveCheckbox(userTypeExtId,checkboxId);
        }
    }

    @Transactional
    public Long createUserTypeExt(String userTypeExtName) {
        UserTypeExt userTypeExt = new UserTypeExt();
        userTypeExt.setName(userTypeExtName);
        userTypeExtRepository.save(userTypeExt);
        return userTypeExt.getId();
    }

    /**
     * 创建人的选择
     * @param type
     * @param jsonNode
     * @param userId
     */
    public void addUserTypeExtOfUser(String type, JsonNode jsonNode, Long userId) {
        if (UserTypeExt.RelationShip.CHECKBOX.getType().equals(type)){//如果是check
            //获取checkbox选中的id
            List<Long> checkIds = new ArrayList<>();
            jsonNode.get("optionValueIds").elements().forEachRemaining(
                    t->{
                       Long checkId =  t.get("checkedId").asLong();
                        checkIds.add(checkId);
                    }
            );
            checkboxRepository.createChecked(checkIds,userId);
        }else if (UserTypeExt.RelationShip.TEXT.getType().equals(type)){//如果是文本选项
            //text选项id
            Long textId = jsonNode.get("testId").asLong();
            //text填入值
            String value = jsonNode.get("value").asText();
            textValueRepository.create(textId,value,userId);
        }
    }
}
