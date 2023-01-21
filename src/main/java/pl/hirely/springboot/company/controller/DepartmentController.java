package pl.hirely.springboot.company.controller;

import org.springframework.web.bind.annotation.*;
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto;
import pl.hirely.springboot.company.model.dto.NewDepartmentDto;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;
import pl.hirely.springboot.company.model.service.CalculationService;
import pl.hirely.springboot.company.model.service.DepartmentService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final CalculationService calculationService;

    public DepartmentController(DepartmentService departmentService, CalculationService calculationService) {
        this.departmentService = departmentService;
        this.calculationService = calculationService;
    }


    @GetMapping("/salary")
    public List<DepartmentSalaryDto> calculate(){
        return calculationService.calculate();
    }

    @PostMapping
    public void addNewDepartment(@RequestBody NewDepartmentDto newDepartmentDto){
        departmentService.addNewDepartment(newDepartmentDto);
    }
    @PostMapping("/{id}/employee")
    @Transactional
    public void addNewEmployees(@PathVariable("id") Long id,
                                @RequestBody NewEmployeeDto newEmployeeDto){
        departmentService.addNewEmployee(id, newEmployeeDto);
    }
}
