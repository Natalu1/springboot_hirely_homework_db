package pl.hirely.springboot.company.model.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.company.model.domain.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
