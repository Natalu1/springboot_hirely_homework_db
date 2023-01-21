package pl.hirely.springboot.company.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.company.model.domain.Department;
import pl.hirely.springboot.company.model.dto.NewDepartmentDto;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;
import pl.hirely.springboot.company.model.mapper.DepartmentMapper;
import pl.hirely.springboot.company.model.mapper.EmployeeMapper;
import pl.hirely.springboot.company.model.repository.DepertmentRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class DepartmentService {
    private final DepertmentRepository depertmentRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    public DepartmentService(DepertmentRepository depertmentRepository, DepartmentMapper departmentMapper,
                             EmployeeMapper employeeMapper) {
        this.depertmentRepository = depertmentRepository;
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
    }

    public void addNewDepartment(NewDepartmentDto newDepartmentDto){
        Department department = departmentMapper.toEntity(newDepartmentDto);
        depertmentRepository.save(department);
    }

    public void addNewEmployee(Long departmentId, NewEmployeeDto newEmployeeDto) {
        depertmentRepository.findById(departmentId).ifPresentOrElse(department -> addEmployee(department, newEmployeeDto),
                this::throwNotFoundException);
    }

    private void addEmployee(Department department, NewEmployeeDto newEmployeeDto){
        department.addNewEmployee(employeeMapper.toEntity(newEmployeeDto));
    }
    private void throwNotFoundException(){
        throw new RuntimeException("Department is not found");
    }
}
