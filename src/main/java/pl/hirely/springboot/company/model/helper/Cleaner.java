package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cleaner extends Staff {


    public Cleaner(BigDecimal salary, LocalDate employmentDate) {
        super(salary, employmentDate);
    }

    @Override
    public BigDecimal calculateSalary() {
        return salary;
    }

    @Override
    public BigDecimal calculateTotalIncome() {
        return salary.multiply(BigDecimal.valueOf(getNumberOfEmploymentMonths()));
    }
}
