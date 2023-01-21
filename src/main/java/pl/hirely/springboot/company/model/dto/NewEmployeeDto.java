package pl.hirely.springboot.company.model.dto;

import pl.hirely.springboot.company.model.domain.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NewEmployeeDto {
    private String fullName;
    private String email;
    private Position position;
    private BigDecimal baseSalary;
    private String employmentDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String  getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }


}
