package pl.hirely.springboot.company.model.service


import pl.hirely.springboot.company.model.domain.Department
import pl.hirely.springboot.company.model.domain.Employee
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto
import pl.hirely.springboot.company.model.repository.DepartmentRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class StaffServiceSpec extends Specification {
    private DepartmentRepository departmentRepository = Mock()

    @Subject
    private SalaryCalculationService calculationService = new SalaryCalculationService(departmentRepository)

    def "shouldCalculateSumSalaryDepartment"() {
        given:
        def dep1 = new Department(id: 1L, name: "Administration");
        def dep2 = new Department(id: 2L, name: "Development");
        def director = new Employee(id: 1L, fullName: "asdesd", email: "dsd@gmail.com",
                position: "DIRECTOR", baseSalary: 20000, employmentDate: LocalDate.now().minusYears(4),
                department: dep1)
        def developer1 = new Employee(id: 2L, fullName: "dfgd gdfg asdesd", email: "ddd@gmail.com",
                position: "DEVELOPER", baseSalary: 10000, employmentDate: LocalDate.now().minusYears(2),
                department: dep2)
        def developer2 = new Employee(id: 3L, fullName: "dsfdsfsd grhhgfh", email: "eee@gmail.com",
                position: "DEVELOPER", baseSalary: 15000, employmentDate: LocalDate.now().minusMonths(10),
                department: dep2)
        def cleaner = new Employee(id: 4L, fullName: "Eva Mentor", email: "eee@gmail.com",
                position: "CLEANER", baseSalary: 7000, employmentDate: LocalDate.now().minusYears(1),
                department: dep1)
        dep1.setEmployees(List.of(director, cleaner))
        dep2.setEmployees(List.of(developer1, developer2))

        departmentRepository.findWithEmployees() >> List.of(dep1, dep2)

        when:
        def actual = calculationService.calculateSalaryByDepartment()

        then:
        actual.size() == 2
        actual.find({it.nameDepartment == 'Administration'})  == new DepartmentSalaryDto( 'Administration', 35000.0)
        actual.find({it.nameDepartment == 'Development'})  == new DepartmentSalaryDto( 'Development', 27000.0)
    }
}
