package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Developer extends Staff {


    public Developer(BigDecimal salary, LocalDate employmentDate) {
        super(salary, employmentDate);
    }

    @Override
    public BigDecimal calculateSalary() {
        return calculateEmploymentYears() > 0
                ? salary.multiply(new BigDecimal("1.2"))
                : salary;
    }

}
