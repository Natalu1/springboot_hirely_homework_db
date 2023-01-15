package pl.hirely.springboot.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto;
import pl.hirely.springboot.company.model.service.CalculationService;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    private final CalculationService calculationService;

    public SalaryController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }
    @GetMapping
    public List<DepartmentSalaryDto> calculate(){
        return calculationService.calculate();
    }



}
