package pl.hirely.springboot.company.model.mapper;

import org.springframework.stereotype.Component;
import pl.hirely.springboot.company.model.domain.Employee;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;

import java.time.LocalDate;

@Component
public class EmployeeMapper {
    public Employee toEntity(NewEmployeeDto newEmployeeDto){
        Employee employee = new Employee();
        employee.setFullName(newEmployeeDto.getFullName());
        employee.setEmail(newEmployeeDto.getEmail());
        employee.setPosition(newEmployeeDto.getPosition());
        employee.setBaseSalary(newEmployeeDto.getBaseSalary());
        employee.setEmploymentDate(LocalDate.parse(newEmployeeDto.getEmploymentDate()));
        return employee;
    }
    public NewEmployeeDto fromEntity(Employee employee){
        NewEmployeeDto newEmployeeDto = new NewEmployeeDto();
        newEmployeeDto.setFullName(employee.getFullName());
        newEmployeeDto.setPosition(employee.getPosition());
        return newEmployeeDto;
    }
}
