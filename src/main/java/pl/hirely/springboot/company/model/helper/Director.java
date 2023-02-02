package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Director extends Staff {


    public Director(BigDecimal salary, LocalDate employmentDate) {
        super(salary, employmentDate);
    }

    @Override
   public BigDecimal calculateSalary() {
        return salary.add(salary.multiply(new BigDecimal("0.1"))
                .multiply(new BigDecimal(calculateEmploymentYears())));
    }
    @Override
    public BigDecimal calculateTotalIncome() {
        return null;
    }

}
