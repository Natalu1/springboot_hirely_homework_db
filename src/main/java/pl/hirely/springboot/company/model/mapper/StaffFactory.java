package pl.hirely.springboot.company.model.mapper;

import pl.hirely.springboot.company.model.domain.Employee;
import pl.hirely.springboot.company.model.helper.Staff;
import pl.hirely.springboot.company.model.helper.Cleaner;
import pl.hirely.springboot.company.model.helper.Developer;
import pl.hirely.springboot.company.model.helper.Director;

public class StaffFactory {

    public static Staff map(Employee employee) {
        return switch (employee.getPosition()) {
            case DIRECTOR -> new Director(employee.getBaseSalary(), employee.getEmploymentDate());
            case DEVELOPER -> new Developer(employee.getBaseSalary(), employee.getEmploymentDate());
            case CLEANER -> new Cleaner(employee.getBaseSalary(), employee.getEmploymentDate());
        };
    }
}
