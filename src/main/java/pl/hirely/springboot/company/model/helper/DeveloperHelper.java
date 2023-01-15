package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DeveloperHelper extends CalculationHelper {


    public DeveloperHelper(BigDecimal salary, LocalDate employmentDate) {
        super(salary, employmentDate);
    }

    @Override
    public BigDecimal calculateSalary() {
        return calculateEmploymentYears() > 0
                ? salary.add(salary.multiply(new BigDecimal("0.2")))
                : salary;
    }

}
