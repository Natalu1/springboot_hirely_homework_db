package pl.hirely.springboot.company.model.dto;

import java.math.BigDecimal;

public class EmployeeIncomeDto {

    private String fullName;

    private BigDecimal totalIncome;

    public EmployeeIncomeDto() {
    }

    public EmployeeIncomeDto(String fullName, BigDecimal totalIncome) {
        this.fullName = fullName;
        this.totalIncome = totalIncome;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }
}
