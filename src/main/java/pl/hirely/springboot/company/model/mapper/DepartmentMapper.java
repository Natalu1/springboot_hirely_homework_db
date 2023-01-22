package pl.hirely.springboot.company.model.mapper;

import org.springframework.stereotype.Component;
import pl.hirely.springboot.company.model.domain.Department;
import pl.hirely.springboot.company.model.dto.NewDepartmentDto;

@Component
public class DepartmentMapper {
    public Department toEntity(NewDepartmentDto newDepartmentDto){
        Department department = new Department();
        department.setName(newDepartmentDto.getName());
        return department;
    }
}
