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

    @Override
    public BigDecimal calculateTotalIncome() {
      long numberOfFirstPeriodMonths = Math.min(getNumberOfEmploymentMonths(), 12);
        long numberOfFSecondPeriodMonths = Math.max(getNumberOfEmploymentMonths() - 12, 0);

        BigDecimal firstYearIncome = BigDecimal.valueOf(numberOfFirstPeriodMonths).multiply(salary);
        BigDecimal newSalary = salary.multiply(BigDecimal.valueOf(1.2));
        BigDecimal otherYearsIncome = BigDecimal.valueOf(numberOfFSecondPeriodMonths)
                .multiply(newSalary);
        return firstYearIncome.add(otherYearsIncome);
    }


//    @Override
//    public BigDecimal calculateTotalIncome() {
//        if (getNumberOfEmploymentMonths() <= 12) {
//            return BigDecimal.valueOf(getNumberOfEmploymentMonths())
//                    .multiply(salary);
//        } else {
//            return BigDecimal.valueOf(12).multiply(salary)
//                    .add(BigDecimal.valueOf(getNumberOfEmploymentMonths() - 12))
//                    .multiply(salary.multiply(BigDecimal.valueOf(1.2)));
//        }
//    }
}
