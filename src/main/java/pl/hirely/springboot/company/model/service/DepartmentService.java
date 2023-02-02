package pl.hirely.springboot.company.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.company.model.domain.Department;
import pl.hirely.springboot.company.model.domain.Employee;
import pl.hirely.springboot.company.model.domain.Position;
import pl.hirely.springboot.company.model.dto.NewDepartmentDto;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;
import pl.hirely.springboot.company.model.mapper.DepartmentMapper;
import pl.hirely.springboot.company.model.mapper.EmployeeMapper;
import pl.hirely.springboot.company.model.repository.DepartmentRepository;
import pl.hirely.springboot.company.model.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             EmployeeRepository employeeRepository, DepartmentMapper departmentMapper,
                             EmployeeMapper employeeMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
    }

    public void addNewDepartment(NewDepartmentDto newDepartmentDto) {
        Department department = departmentMapper.toEntity(newDepartmentDto);
        departmentRepository.save(department);
    }

    public List<NewEmployeeDto> getEmployeesByPosition(Position position) {
       return employeeRepository.findAllByPosition(position)
               .stream()
               .map(employee -> employeeMapper.fromEntity(employee))
               .toList();
    }

    public void addNewEmployee(Long departmentId, NewEmployeeDto newEmployeeDto) {
        departmentRepository.findById(departmentId).ifPresentOrElse(department -> addEmployee(department, newEmployeeDto),
                this::throwNotFoundException);
    }

    private void addEmployee(Department department, NewEmployeeDto newEmployeeDto) {
        department.addNewEmployee(employeeMapper.toEntity(newEmployeeDto));
    }

    private void throwNotFoundException() {
        throw new RuntimeException("Department is not found");
    }
}
