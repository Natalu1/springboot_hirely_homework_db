package pl.hirely.springboot.company.model.repository;

import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.company.model.domain.Employee;
import pl.hirely.springboot.company.model.domain.Position;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


//    @Query("select Employee e where e.position := position and e.departmentId := departmentId")

    List<Employee> findAllByPositionAndDepartmentId(Position position, Long departmentId);
    List<Employee> findAllByPosition(Position position);



}
