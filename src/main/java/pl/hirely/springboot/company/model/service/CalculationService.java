package pl.hirely.springboot.company.model.service;

import org.springframework.stereotype.Service;
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto;
import pl.hirely.springboot.company.model.helper.CalculationHelper;
import pl.hirely.springboot.company.model.mapper.CalculationHelperMapper;
import pl.hirely.springboot.company.model.repository.DepertmentRepository;
import pl.hirely.springboot.company.model.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationService {

    private final EmployeeRepository employeeRepository;
    private final DepertmentRepository depertmentRepository;

    public CalculationService(EmployeeRepository employeeRepository, DepertmentRepository depertmentRepository) {
        this.employeeRepository = employeeRepository;
        this.depertmentRepository = depertmentRepository;
    }

    public List<DepartmentSalaryDto> calculate() {
        List<DepartmentSalaryDto> departmentSalaryDtos = new ArrayList<>();

        depertmentRepository.findWithEmployees()
                .forEach(department -> {
                    BigDecimal sumByDepartment = department.getEmployees().stream()
                            .map(employee -> CalculationHelperMapper.map(employee))
                            .map(CalculationHelper::calculateSalary)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    departmentSalaryDtos.add(new DepartmentSalaryDto(department.getName(), sumByDepartment.doubleValue()));
                    System.out.println("dep: " + department.getName() + " , salary: " + sumByDepartment.toString());
                });
        return departmentSalaryDtos;
    }

}
