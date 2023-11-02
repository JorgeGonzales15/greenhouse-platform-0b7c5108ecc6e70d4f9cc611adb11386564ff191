package com.integradis.greenhouse.platform.crops.domain.model.entities;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class Bunker extends CropEntry{
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
    private String comment;

    public Bunker() {
        this.cropPhase = CropPhase.BUNKER;
    }
    public Bunker(Crop crop, String author,
                  int thermocoupleOne, int thermocoupleTwo, int thermocoupleThree,
                  float averageThermocouple, int motorFrequency, String comment) {
        super(author, null, crop);
        this.cropPhase = CropPhase.BUNKER;
        this.thermocoupleOne = thermocoupleOne;
        this.thermocoupleTwo = thermocoupleTwo;
        this.thermocoupleThree = thermocoupleThree;
        this.averageThermocouple = averageThermocouple;
        this.motorFrequency = motorFrequency;
        this.comment = comment;
    }
}
