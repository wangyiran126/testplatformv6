package service.archivesManage;

import dao.DepartmentDao;
import entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DepartmentRepository;
import tyrex.util.ArraySet;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by wangyiran on 6/9/2016.
 */
@Service
@Transactional
public class ArchivesManageService {
    @Inject
    private DepartmentRepository departmentRepository;

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

    public Department test() {
        Department department = new Department();
        department.setName("1.1团");
        Department sub = new Department();
        sub.setName("1.1营");
        sub.setParentDepartments(department);
        Department sub2 = new Department();
        sub2.setName("1.2营长");
        sub2.setParentDepartments(department);
        Set<Department> subs = new ArraySet();
        subs.add(sub);
        subs.add(sub2);
        department.setSubDepartments(subs);
        Department subsub = new Department();
        subsub.setName("1.1连");
        Set<Department> subset = new ArraySet();
        subset.add(subsub);
        sub.setSubDepartments(subset);
        departmentRepository.save(department);

//        return departmentRepository.getDepartmentByName("1.1团");
        return department;
    }

    public Department getDepartment(Long deptId) {
        List<Department> departments = departmentRepository.getDepart(deptId);
        Optional<Department> optional =  departments.stream().filter(d -> d.getId().equals(deptId)).findFirst();
        Department parentDepartment = null;
        Department department = null;
        if (optional.isPresent()){
             department = optional.get();
            Integer depth = departments.size();
                    while (depth >= 0){
                        if (parentDepartment == null)
                        parentDepartment =department.getParentDepartments();
                        else parentDepartment = parentDepartment.getParentDepartments();
                        if (depth == 0){
                            parentDepartment.setParentDepartments(null);
                        }
                        depth--;
                    }
        }
        return department;
    }
//
//    public List<Department> getDepartment2(Long deptId) {
//        List<Department> departments = departmentDao.getDepartment(deptId);
//        return departments;
//    }

    public Long saveDepartment(String name) {
        Department department = new Department();
        department.setId(1l);
        department.setName(name);
        Department saveed = departmentRepository.save(department);
        return saveed.getId();
    }
}
