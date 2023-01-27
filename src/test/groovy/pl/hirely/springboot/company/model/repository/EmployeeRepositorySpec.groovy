package pl.hirely.springboot.company.model.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification
import spock.lang.Subject

@DataJpaTest
class EmployeeRepositorySpec extends Specification {

    @Autowired
    @Subject
    private EmployeeRepository employeeRepository

    def "should find all employees"() {
        when:
        def actual = employeeRepository.findAll()

        then:
        actual.size() == 10
        actual*.fullName as Set == ['Roman Romanowski', 'Tomasz Tomaszewski', 'Adam Kowalski', 'Max Potter',
                                    'Kolin Dark', 'Eva Mentor', 'Roman Petrowski', 'Tomasz Igielski',
                                    'Greg Leonowski', 'Alla Bilyk'] as Set
    }
}
