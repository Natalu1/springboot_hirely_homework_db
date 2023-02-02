package pl.hirely.springboot.company.controller;

import org.springframework.web.bind.annotation.*;
import pl.hirely.springboot.company.model.domain.Position;
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto;
import pl.hirely.springboot.company.model.dto.NewDepartmentDto;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;
import pl.hirely.springboot.company.model.service.SalaryCalculationService;
import pl.hirely.springboot.company.model.service.DepartmentService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final SalaryCalculationService salaryCalculationService;

    public DepartmentController(DepartmentService departmentService,
                                SalaryCalculationService salaryCalculationService) {
        this.departmentService = departmentService;
        this.salaryCalculationService = salaryCalculationService;
    }


    @GetMapping("/salary")
    public List<DepartmentSalaryDto> сalculateSumSalaryDepartment(){
        return salaryCalculationService.calculateSalaryByDepartment();
    }
    @GetMapping("/max")
    public List<DepartmentSalaryDto> maxSalaryDepartment(){
        return salaryCalculationService.maxSalaryDepartment();
    }

    @PostMapping
    public void addNewDepartment(@RequestBody NewDepartmentDto newDepartmentDto){
        departmentService.addNewDepartment(newDepartmentDto);
    }
    @GetMapping("/position")
    public List<NewEmployeeDto> findEmployeeByPosition(@PathVariable ("position") Position position){
        return departmentService.getEmployeesByPosition(position);
    }

}
