package pl.hirely.springboot.company.controller;

import org.springframework.web.bind.annotation.*;
import pl.hirely.springboot.company.model.dto.NewDepartmentDto;
import pl.hirely.springboot.company.model.dto.NewEmployeeDto;
import pl.hirely.springboot.company.model.service.DepartmentService;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
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
