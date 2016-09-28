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
//    @Autowired
//    public ArchivesManageService(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }


//    @Autowired
//    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }

    public DepartmentRepository getDepartmentRepository() {
        return departmentRepository;
    }


//    public Department getDepartment(Long deptId) {
//        List<Department> departments = departmentRepository.getParentDepartment(deptId);
//        Optional<Department> optional =  departments.stream().filter(d -> d.getDepartId().equals(deptId)).findFirst();
//        Department parentDepartment = null;
//        Department department = null;
//        if (optional.isPresent()){
//             department = optional.get();
//            Integer depth = departments.size();
//                    while (depth >= 0){
//                        if (parentDepartment == null)
//                        parentDepartment =department.getParentDepartments();
//                        else parentDepartment = parentDepartment.getParentDepartments();
//                        if (depth == 0){
//                            parentDepartment.setParentDepartments(null);
//                        }
//                        depth--;
//                    }
//        }
//        return department;
//    }

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
     * @param userTypeExts
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

//    @Transactional
//    public Long createUserTypeExt(String name, String type, List<OptionValue> optionValues) {
//        optionValueRepository.save(optionValues);
//        List<Long> optionValuesIds = new ArrayList<>();
//        optionValues.forEach(t->{
//            optionValuesIds.add(t.getId());
//        });
//
//        UserTypeExt userTypeExt = new UserTypeExt();
//        userTypeExt.setName(name);
//        userTypeExt.setType(type);
//        userTypeExtRepository.save(userTypeExt);
//         userTypeExtRepository.saveHaveOption(userTypeExt.getId(),optionValuesIds);
//        UserTypeExt userTypeExt1 = userTypeExtRepository.save(userTypeExt);
//        return userTypeExt1.getId();
//    }

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
        if (UserTypeExt.RelationShip.CHECKBOX.getType().equals(type)){
            List<OptionValue> optionValues = new ArrayList<>();
            jsonNode.elements().forEachRemaining(t->{
                String name = t.get("name").asText();
                String value = t.get("value").asText();
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
            Checkbox checkbox = new Checkbox();
            checkbox.setName(checkName);
            checkboxRepository.save(checkbox);
            Long checkboxId = checkbox.getId();
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
}
