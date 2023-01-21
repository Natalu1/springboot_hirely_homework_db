package pl.hirely.springboot.company.model.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
    public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public Department() {
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void addNewEmployee(Employee employee){
        employees.add(employee);
        employee.setDepartment(this);
    }
}
