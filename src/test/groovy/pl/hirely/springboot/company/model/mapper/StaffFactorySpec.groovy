package pl.hirely.springboot.company.model.mapper

import pl.hirely.springboot.company.model.domain.Employee
import pl.hirely.springboot.company.model.domain.Position
import pl.hirely.springboot.company.model.helper.Cleaner
import pl.hirely.springboot.company.model.helper.Developer
import pl.hirely.springboot.company.model.helper.Director
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class StaffFactorySpec extends Specification {
    @Subject
    StaffFactory staffFactory = new StaffFactory()

    def "should return staff position from ENUM position"() {
        given:
        Employee employee = new Employee(position: position,
                baseSalary:  BigDecimal.valueOf(20000),
                employmentDate:  LocalDate.now().minusYears(4))

        when:
        def actual = staffFactory.map(employee)

        then:
        actual.class == expectedActual
        actual.salary == BigDecimal.valueOf(20000)
        actual.employmentDate == LocalDate.now().minusYears(4)

        where:
        position           || expectedActual

        Position.DIRECTOR  || Director.class
        Position.DEVELOPER || Developer.class
        Position.CLEANER   || Cleaner.class
    }

}
