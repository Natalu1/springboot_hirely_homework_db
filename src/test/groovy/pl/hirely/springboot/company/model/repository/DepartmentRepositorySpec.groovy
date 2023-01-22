package pl.hirely.springboot.company.model.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification
import spock.lang.Subject


@DataJpaTest
class DepartmentRepositorySpec extends Specification {
    @Autowired
    @Subject
    private DepartmentRepository departmentRepository

    def "should find all departments with employees"() {
        when:
        def actual = departmentRepository.findWithEmployees()

        then:
        actual.size() == 2
        actual*.name as Set == ['IT','IBM'] as Set
        actual.find({it.id == 1L}).employees*.fullName as Set ==
                ['Roman Romanowski', 'Tomasz Tomaszewski', 'Adam Kowalski', 'Max Potter','Kolin Dark','Eva Mentor'] as Set
        actual.find({it.id == 2L}).employees*.fullName as Set ==
        ['Roman Petrowski', 'Tomasz Igielski', 'Greg Leonowski', 'Alla Bilyk'] as Set

    }
}
