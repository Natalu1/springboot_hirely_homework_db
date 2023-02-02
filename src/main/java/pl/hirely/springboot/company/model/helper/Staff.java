package pl.hirely.springboot.company.model.helper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public abstract class Staff {


    protected BigDecimal salary;
    protected LocalDate employmentDate;

    public Staff(BigDecimal salary, LocalDate employmentDate) {
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    public abstract BigDecimal calculateSalary();
    public abstract BigDecimal calculateTotalIncome();

    protected int calculateEmploymentYears() {
        return Period.between(employmentDate, LocalDate.now()).getYears();
    }

    protected long getNumberOfEmploymentMonths() {
        return Period.between(employmentDate, LocalDate.now()).toTotalMonths();
    }


}
