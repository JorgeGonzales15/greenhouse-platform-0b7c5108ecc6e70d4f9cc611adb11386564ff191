package com.integradis.greenhouse.platform.crops.domain.model.entities;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class PreparationArea extends CropEntry{
    @Getter
    private int activities;
    @Getter
    private int temperature;
    @Getter
    private String comment;

    public PreparationArea() {
        this.cropPhase = CropPhase.PREPARATION_AREA;
    }

    public PreparationArea(Crop crop, String author,
                           int activities, int temperature, String comment) {
        super(author, null, crop);
        this.cropPhase = CropPhase.PREPARATION_AREA;
        this.activities = activities;
        this.temperature = temperature;
        this.comment = comment;
    }
}
