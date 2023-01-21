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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
