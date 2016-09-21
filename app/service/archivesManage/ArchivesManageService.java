package service.archivesManage;

import entity.Department;
import entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DepartmentRepository;
import repository.UserRepository;

import javax.inject.Inject;
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
}
