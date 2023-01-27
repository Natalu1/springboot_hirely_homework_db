package pl.hirely.springboot.company.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.company.model.domain.Department;
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto;
import pl.hirely.springboot.company.model.helper.Staff;
import pl.hirely.springboot.company.model.mapper.StaffFactory;
import pl.hirely.springboot.company.model.repository.DepartmentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalaryCalculationService {

    private final DepartmentRepository departmentRepository;

    public SalaryCalculationService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentSalaryDto> calculateSalaryByDepartment() {

        return departmentRepository.findWithEmployees()
                .stream()
                .map(department ->
                        new DepartmentSalaryDto(department.getName(),
                                getSumByDepartment(department)))
                .peek(System.out::println)
                .toList();
    }

    private BigDecimal getSumByDepartment(Department department) {
        return department.getEmployees().stream()
                .map(employee -> StaffFactory.map(employee))
                .map(Staff::calculateSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
