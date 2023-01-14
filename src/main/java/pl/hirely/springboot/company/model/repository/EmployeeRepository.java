package pl.hirely.springboot.company.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hirely.springboot.company.model.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
