package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public abstract class CalculationHelper {
    protected BigDecimal salary;
    protected LocalDate employmentDate;

    public CalculationHelper(BigDecimal salary, LocalDate employmentDate) {
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    public abstract BigDecimal calculateSalary();

    protected int calculateEmploymentYears() {
        return Period.between(employmentDate, LocalDate.now()).getYears();
    }


}
