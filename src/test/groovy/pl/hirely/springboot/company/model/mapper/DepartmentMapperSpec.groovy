package pl.hirely.springboot.company.model.mapper

import pl.hirely.springboot.company.model.domain.Department
import pl.hirely.springboot.company.model.dto.NewDepartmentDto
import spock.lang.Specification
import spock.lang.Subject

class DepartmentMapperSpec extends Specification {

    @Subject
    private DepartmentMapper departmentMapper = new DepartmentMapper()

    def"should mapNewDepartmentDto to Entity"(){
        given:
        NewDepartmentDto newDepartmentDto = new NewDepartmentDto (name: "IT")

        when:
        def actual = departmentMapper.toEntity(newDepartmentDto)

        then:
        actual.name == "IT"
    }
}
