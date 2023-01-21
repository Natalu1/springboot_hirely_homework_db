package pl.hirely.springboot.company.model.dto;

import java.math.BigDecimal;

public class DepartmentSalaryDto {

    private String nameDepartment;
    private BigDecimal salary;

    public DepartmentSalaryDto(String nameDepartment, BigDecimal salary) {
        this.nameDepartment = nameDepartment;
        this.salary = salary;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
