package com.integradis.greenhouse.platform.profiles.domain.model.aggregates;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.profiles.domain.model.valueobjects.CompanyName;
import com.integradis.greenhouse.platform.profiles.domain.model.valueobjects.TIN;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Company extends AbstractAggregateRoot<Company> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private CompanyName companyName;

    @Embedded
    private TIN tin;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "company")
    private List<Crop> crops;

    public Company() {

    }

    public Company(String name, String number){
        this.companyName = new CompanyName(name);
        this.tin = new TIN(number);
    }

    public Company(Long id){
        this();
    }

    public String getCompanyName(){
        return this.companyName.name();
    }

    public String getTin() {
        return this.tin.number();
    }
}
