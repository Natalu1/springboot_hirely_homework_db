package pl.hirely.springboot.company.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.company.model.domain.Department;
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto;
import pl.hirely.springboot.company.model.helper.Calculation;
import pl.hirely.springboot.company.model.mapper.PositionMapper;
import pl.hirely.springboot.company.model.repository.DepartmentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationService {

    private final DepartmentRepository departmentRepository;

    public CalculationService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentSalaryDto> calculate() {
        List<DepartmentSalaryDto> departmentSalaryDtos = new ArrayList<>();

        departmentRepository.findWithEmployees()
                .forEach(department -> {
                    departmentSalaryDtos.add(new DepartmentSalaryDto(department.getName(),
                            getSumByDepartment(department)));
                    System.out.println("dep: " + department.getName() + " , salary: " +  getSumByDepartment(department).toString());
                });
        return departmentSalaryDtos;
    }

    private static BigDecimal getSumByDepartment(Department department) {
        return  department.getEmployees().stream()
                .map(employee -> PositionMapper.map(employee))
                .map(Calculation::calculateSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
