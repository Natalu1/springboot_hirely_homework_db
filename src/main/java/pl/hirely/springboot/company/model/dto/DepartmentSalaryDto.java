package pl.hirely.springboot.company.model.dto;

public class DepartmentSalaryDto {

    private String name;
    private double salary;

    public DepartmentSalaryDto(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
