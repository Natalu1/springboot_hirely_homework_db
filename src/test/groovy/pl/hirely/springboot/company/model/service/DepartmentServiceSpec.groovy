package pl.hirely.springboot.company.model.service

import pl.hirely.springboot.company.model.domain.Department
import pl.hirely.springboot.company.model.domain.Employee
import pl.hirely.springboot.company.model.domain.Position
import pl.hirely.springboot.company.model.mapper.StaffFactory
import pl.hirely.springboot.company.model.repository.DepartmentRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class DepartmentServiceSpec extends Specification {
    private DepartmentRepository departmentRepository = Mock()
    private StaffFactory staffFactory = new StaffFactory()
    @Subject
    private DepartmentService departmentSalaryService = new DepartmentService(departmentRepository, employeeFactory)

    def "should return empty list when no department found"() {
        given:
        departmentRepository.findAllDepartmentsWithEmployees() >> Collections.emptyList()

        when:
        def actual = departmentSalaryService.getAmountOfDepartmentSalary()

        then:
        actual.isEmpty()
    }

    def "should return zero amount when no employees in department"() {
        given:
        def departments = List.of(new Department(name: "first"), new Department(name: "second"))

        departmentRepository.findAllDepartmentsWithEmployees() >> departments

        when:
        def actual = departmentSalaryService.getAmountOfDepartmentSalary()

        then:
        actual*.departmentName as Set == ["first", "second"] as Set
        actual*.salaryAmount == [BigDecimal.ZERO, BigDecimal.ZERO]
    }

    def "should properly calculate department salaries"() {
        def now = LocalDate.now()
        def department1Employee1 = new Employee(
                email: "email11",
                baseSalary: BigDecimal.valueOf(1000),
                employmentDate: now,
                position: Position.CLEANER) //salary = 1000

        def department1Employee2 = new Employee(
                email: "email12",
                baseSalary: BigDecimal.valueOf(10000),
                employmentDate: now.minusYears(3),
                position: Position.DIRECTOR) //salary = 13000

        def department2Employee1 = new Employee(
                email: "email21",
                baseSalary: BigDecimal.valueOf(15000),
                employmentDate: now.minusYears(2),
                position: Position.DEVELOPER) //salary = 18000

        def department2Employee2 = new Employee(
                email: "email22",
                baseSalary: BigDecimal.valueOf(4000),
                employmentDate: now.minusYears(2),
                position: Position.CLEANER) //salary = 4000

        def department1 = new Department(name: "first", employees: [department1Employee1, department1Employee2])
        def department2 = new Department(name: "second", employees: [department2Employee1, department2Employee2])

        departmentRepository.findAllDepartmentsWithEmployees() >> [department1, department2]

        when:
        def actual = departmentSalaryService.getAmountOfDepartmentSalary()

        then:
        actual*.departmentName == ["first", "second"]
        actual.find(department -> department.departmentName == "first").salaryAmount == BigDecimal.valueOf(14000)
        actual.find(department -> department.departmentName == "second").salaryAmount == BigDecimal.valueOf(22000)
    }
}
