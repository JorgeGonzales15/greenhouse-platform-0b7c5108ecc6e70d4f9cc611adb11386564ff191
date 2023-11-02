package com.integradis.greenhouse.platform.profiles.domain.model.aggregates;

import com.integradis.greenhouse.platform.profiles.domain.model.valueobjects.Email;
import com.integradis.greenhouse.platform.profiles.domain.model.valueobjects.EmployeeName;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Employee extends AbstractAggregateRoot<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Embedded
    private EmployeeName name;
    @Embedded
    private Email email;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate Date updatedAt;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, Company company){
        this.name = new EmployeeName(firstName, lastName);
        this.email = new Email(email);
        this.company = company;
    }

    public void updateName(String firstName, String lastName) {
        this.name = new EmployeeName(firstName, lastName);
    }
    public String getFullName() {
        return this.name.getFullName();
    }
    public String getEmail() {
        return this.email.email();
    }
    //public Long getCompanyId() { return this.companyId.companyId();}
}
