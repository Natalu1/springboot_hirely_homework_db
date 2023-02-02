package pl.hirely.springboot.company.controller;

import org.springframework.web.bind.annotation.*;
import pl.hirely.springboot.company.model.domain.Position;
import pl.hirely.springboot.company.model.dto.EmployeeIncomeDto;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;
import pl.hirely.springboot.company.model.service.DepartmentService;
import pl.hirely.springboot.company.model.service.SalaryCalculationService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/department/{departmentId}/employee")
public class EmployeeController {
    private final SalaryCalculationService salaryCalculationService;
    private final DepartmentService departmentService;

    public EmployeeController(SalaryCalculationService salaryCalculationService, DepartmentService departmentService) {
        this.salaryCalculationService = salaryCalculationService;
        this.departmentService = departmentService;
    }

    @GetMapping("/position/{position}")
    public List<EmployeeIncomeDto> getEmployeesIncome(@PathVariable("departmentId") Long departmentId,
                                                      @PathVariable("position") Position position) {
        return salaryCalculationService.getEmployeesIncome(departmentId, position);
    }

    @PostMapping
        public void addNewEmployees(@PathVariable("departmentId") Long id,
                                @RequestBody NewEmployeeDto newEmployeeDto){
        departmentService.addNewEmployee(id, newEmployeeDto);
    }

}
