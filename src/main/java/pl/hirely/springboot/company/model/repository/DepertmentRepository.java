package pl.hirely.springboot.company.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.company.model.domain.Department;
import pl.hirely.springboot.company.model.domain.Employee;

import java.util.List;

public interface DepertmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employees")
    List<Department> findWithEmployees();
}
