package com.integradis.greenhouse.platform.crops.domain.model.aggregates;


import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropEntryRecord;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import com.integradis.greenhouse.platform.profiles.domain.model.aggregates.Company;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Crop extends AbstractAggregateRoot<Crop> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Getter
    private Date startDate;

    @LastModifiedDate
    @Getter
    private Date endDate;

    @Getter
    private boolean state;

    @Getter
    protected CropPhase cropPhase;

    @Embedded
    @Getter
    public CropEntryRecord cropEntryRecord;

    @ManyToOne
    @Getter
    @JoinColumn(name="company_id", nullable = false)
    private Company company;

    public Crop(){
    }

    public Crop(Company company) {

        this.company = company;
        this.start();
    }

    public void start(){
        this.startDate = new Date();
        this.state = true;
        this.cropPhase = CropPhase.FORMULA;
        this.cropEntryRecord = new CropEntryRecord();
    }

    public void complete(){
        this.state = false;
    }

    public boolean isActive(){
        return this.state;
    }

    public void endPhase() {
        if (this.cropPhase.next().isEmpty()) this.complete();
    }
}
