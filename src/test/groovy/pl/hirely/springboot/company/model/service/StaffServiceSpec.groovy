package pl.hirely.springboot.company.model.service


import pl.hirely.springboot.company.model.domain.Department
import pl.hirely.springboot.company.model.domain.Employee
import pl.hirely.springboot.company.model.domain.Position
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto
import pl.hirely.springboot.company.model.mapper.StaffFactory
import pl.hirely.springboot.company.model.repository.DepartmentRepository
import pl.hirely.springboot.company.model.repository.EmployeeRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class StaffServiceSpec extends Specification {
    private DepartmentRepository departmentRepository = Mock()
    private StaffFactory staffFactory = new StaffFactory()
    private EmployeeRepository employeeRepository = Mock()

    @Subject
    private SalaryCalculationService calculationService = new SalaryCalculationService(departmentRepository, employeeRepository, staffFactory)

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
        actual.find({it.nameDepartment == 'Administration'}).salary  ==  35000.0
        actual.find({it.nameDepartment == 'Development'}).salary  == 27000.0
    }

    def "should property calculate Employees income"() {
        def now = LocalDate.now()
        def departmentId = 1L
        def department1Employee1 = new Employee(
                fullName: "Den Nowak",
                baseSalary: 1000,
                employmentDate: LocalDate.now().minusYears(1),
                position: Position.CLEANER) //salary = 1000

        def department1Employee2 = new Employee(
                fullName: "Adam Kowal",
                baseSalary: 15000,
                employmentDate: now.minusYears(2),
                position: Position.DEVELOPER) //salary = 18000


        employeeRepository.findAllByPositionAndDepartmentId(Position.CLEANER, 1L) >> [department1Employee1]
        employeeRepository.findAllByPositionAndDepartmentId(Position.DEVELOPER, 1L) >> [department1Employee2]

        when:
        def actual = calculationService.getEmployeesIncome(1L, Position.CLEANER)
        def actual1 = calculationService.getEmployeesIncome(1L, Position.DEVELOPER)

        then:
        actual.find(dto->dto.fullName == "Den Nowak").totalIncome == 12000
        actual1.find(dto->dto.fullName == "Adam Kowal").totalIncome == 396000

    }




//    EmployeeRepository employeeRepository = Mock()
//    DepartmentRepository departmentRepository = Mock()
//
//    @Subject
//    EmployeeService employeeService = new EmployeeService(employeeRepository, departmentRepository)
//
//    def "should throw not found exception when department not found"() {
//        def departmentId = 1L
//        def newEmployee = new CreateEmployeeDto()
//        departmentRepository.findById(departmentId) >> Optional.empty()
//
//        when:
//        employeeService.createEmployee(departmentId, newEmployee)
//
//        then:
//        def exception = thrown(NotFoundException)
//        exception.message == "Not found Department entity for id: 1"
//    }
//
//    def "should create new employee when department found"() {
//        def departmentId = 1L
//        def newEmployee = new CreateEmployeeDto(
//                fullName: "name",
//                email: "email",
//                position: Position.DIRECTOR,
//                baseSalary: BigDecimal.valueOf(1000),
//                employmentDate: LocalDate.of(2010, 1, 1))
//        def existingDepartment = new Department(name: "department1")
//
//        departmentRepository.findById(departmentId) >> Optional.of(existingDepartment)
//
//        when:
//        employeeService.createEmployee(departmentId, newEmployee)
//
//        then:
//        1 * employeeRepository.save({Employee employee ->
//            employee.fullName == "name"
//            employee.email == "email"
//            employee.position == Position.DIRECTOR
//            employee.baseSalary == BigDecimal.valueOf(1000)
//            employee.employmentDate == LocalDate.of(2010, 1, 1)})
//    }
}
