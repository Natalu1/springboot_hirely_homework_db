package pl.hirely.springboot.company.model.mapper;

import pl.hirely.springboot.company.model.domain.Employee;
import pl.hirely.springboot.company.model.helper.CalculationHelper;
import pl.hirely.springboot.company.model.helper.CleanerHelper;
import pl.hirely.springboot.company.model.helper.DeveloperHelper;
import pl.hirely.springboot.company.model.helper.DirectorHelper;

public class CalculationHelperMapper {

    public static CalculationHelper map(Employee employee) {
        return switch (employee.getPosition()) {
            case DIRECTOR -> new DirectorHelper(employee.getBaseSalary(), employee.getEmploymentDate());
            case DEVELOPER -> new DeveloperHelper(employee.getBaseSalary(), employee.getEmploymentDate());
            case CLEANER -> new CleanerHelper(employee.getBaseSalary(), employee.getEmploymentDate());
            default -> throw new RuntimeException("Wrong position");
        };
    }
}
