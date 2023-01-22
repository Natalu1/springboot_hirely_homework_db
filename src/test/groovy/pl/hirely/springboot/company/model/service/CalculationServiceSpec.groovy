package pl.hirely.springboot.company.model.service


import pl.hirely.springboot.company.model.domain.Department
import pl.hirely.springboot.company.model.domain.Employee
import pl.hirely.springboot.company.model.repository.DepartmentRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class CalculationServiceSpec extends Specification {
    private DepartmentRepository departmentRepository = Mock()

    @Subject
    private CalculationService calculationService = new CalculationService(departmentRepository)

    def "shouldCalculateSumSalaryDepartment"() {
        given:
        def dep1 = new Department(id: 1L, name: "Administration");
        def dep2 = new Department(id: 2L, name: "Development");
        def director = new Employee(id: 1L, fullName: "asdesd", email: "dsd@gmail.com",
                position: "DIRECTOR", baseSalary: 20000, employmentDate: LocalDate.parse("2018-11-16"),
                department: dep1)
        def developer1 = new Employee(id: 2L, fullName: "dfgd gdfg asdesd", email: "ddd@gmail.com",
                position: "DEVELOPER", baseSalary: 10000, employmentDate: LocalDate.parse("2020-01-10"),
                department: dep2)
        def developer2 = new Employee(id: 3L, fullName: "dsfdsfsd grhhgfh", email: "eee@gmail.com",
                position: "DEVELOPER", baseSalary: 15000, employmentDate: LocalDate.parse("2022-10-15"),
                department: dep2)
        def cleaner = new Employee(id: 4L, fullName: "Eva Mentor", email: "eee@gmail.com",
                position: "CLEANER", baseSalary: 7000, employmentDate: LocalDate.parse("2016-01-15"),
                department: dep1)
        dep1.setEmployees(List.of(director, cleaner))
        dep2.setEmployees(List.of(developer1, developer2))

        departmentRepository.findWithEmployees() >> List.of(dep1, dep2)

        when:
        def actual = calculationService.calculate()

        then:
        actual.size() == 2
        actual*.nameDepartment as Set == ['Administration', 'Development'] as Set
        actual*.salary as Set == [35000, 27000] as Set

    }
}
