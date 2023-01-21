package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cleaner extends Calculation {


    public Cleaner(BigDecimal salary, LocalDate employmentDate) {
        super(salary, employmentDate);
    }

    @Override
    public BigDecimal calculateSalary() {
        return salary;
    }


}