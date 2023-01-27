package pl.hirely.springboot.company.model.dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "DepartmentSalaryDto{" +
                "nameDepartment='" + nameDepartment + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentSalaryDto that = (DepartmentSalaryDto) o;

        if (!Objects.equals(nameDepartment, that.nameDepartment))
            return false;
        return Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        int result = nameDepartment != null ? nameDepartment.hashCode() : 0;
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }
}
