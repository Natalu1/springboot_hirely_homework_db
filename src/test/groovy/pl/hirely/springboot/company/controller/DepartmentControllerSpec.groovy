package pl.hirely.springboot.company.controller

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import pl.hirely.springboot.company.model.dto.DepartmentSalaryDto
import pl.hirely.springboot.company.model.service.SalaryCalculationService
import pl.hirely.springboot.company.model.service.DepartmentService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = DepartmentController)
class DepartmentControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    private SalaryCalculationService calculationService = Mock()
    @SpringBean
    private DepartmentService departmentService = Mock()

    def "shouldCalculateSumSalaryDepartment"() {
        given:
        calculationService.calculateSalaryByDepartment() >> [new DepartmentSalaryDto("IT", 230000 as BigDecimal),
                                                             new DepartmentSalaryDto("IBM", 125800 as BigDecimal),
                                                             new DepartmentSalaryDto("GVC", 44000 as BigDecimal)]
        when:

        def response = mockMvc.perform(get("/department/salary"))

        then:
        response
        .andExpect(status().isOk())
        .andExpect(jsonPath('$.length()').value(3))
        .andExpect(jsonPath('$[0].nameDepartment').value("IT"))
        .andExpect(jsonPath('$[0].salary').value(230000  ))

        .andExpect(jsonPath('$[1].nameDepartment').value("IBM"))
        .andExpect(jsonPath('$[1].salary').value(125800 ))

        .andExpect(jsonPath('$[2].nameDepartment').value("GVC"))
        .andExpect(jsonPath('$[2].salary').value(44000 ))

    }



//    def "should return 200 with correct body"() {
//        given:
//        departmentService.getAmountOfDepartmentSalary() >> [
//                new DepartmentSalaryAmountDto(
//                        departmentName: "department1",
//                        salaryAmount: BigDecimal.valueOf(900.59)),
//                new DepartmentSalaryAmountDto(
//                        departmentName: "department2",
//                        salaryAmount: BigDecimal.valueOf(0.34)
//                )]
//
//        when:
//        def response = mockMvc.perform(get("/department/salary-amount"))
//
//        then:
//        response
//                .andExpect(status().isOk())
//                .andExpect(jsonPath('$.length()').value(2))
//                .andExpect(jsonPath('$[0].departmentName').value("department1"))
//                .andExpect(jsonPath('$[0].salaryAmount').value("900.59"))
//                .andExpect(jsonPath('$[1].departmentName').value("department2"))
//                .andExpect(jsonPath('$[1].salaryAmount').value("0.34"))
//    }
//}

}
