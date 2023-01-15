package pl.hirely.springboot.company.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Potrzebne, aby Postgres sam inkrementował klucz główny typu SERIAL (zobacz: V1_0__schema.sql)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    public Position getPosition() {
        return position;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public Department getDepartment() {
        return department;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;

    @Column(name = "employment_date")
    private LocalDate employmentDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id")
    private Department department;



}
