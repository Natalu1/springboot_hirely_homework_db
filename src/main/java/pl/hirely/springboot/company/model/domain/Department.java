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


}
