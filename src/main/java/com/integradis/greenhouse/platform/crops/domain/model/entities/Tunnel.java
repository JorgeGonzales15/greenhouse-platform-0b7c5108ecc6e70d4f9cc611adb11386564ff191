package com.integradis.greenhouse.platform.crops.domain.model.entities;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import com.integradis.greenhouse.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
public class Tunnel extends CropEntry {
    @Getter
    private int thermocoupleOne;
    @Getter
    private int thermocoupleTwo;
    @Getter
    private int thermocoupleThree;
    @Getter
    private float averageThermocouple;
    @Getter
    private int motorFrequency;
    @Getter
    private float roomTemperature;
    @Getter
    private int freshAir;
    @Getter
    private int recirculation;
    @Getter
    private String comment;

    public Tunnel() {
        this.cropPhase = CropPhase.TUNNEL;
    }

    public Tunnel(Crop crop, String author, int thermocoupleOne, int thermocoupleTwo, int thermocoupleThree,
                  float averageThermocouple, int motorFrequency, float roomTemperature, int freshAir, int recirculation,
                  String comment) {
        super(author, null, crop);
        this.cropPhase = CropPhase.TUNNEL;
        this.thermocoupleOne = thermocoupleOne;
        this.thermocoupleTwo = thermocoupleTwo;
        this.thermocoupleThree = thermocoupleThree;
        this.averageThermocouple = averageThermocouple;
        this.motorFrequency = motorFrequency;
        this.roomTemperature = roomTemperature;
        this.freshAir = freshAir;
        this.recirculation = recirculation;
        this.comment = comment;
    }
}
