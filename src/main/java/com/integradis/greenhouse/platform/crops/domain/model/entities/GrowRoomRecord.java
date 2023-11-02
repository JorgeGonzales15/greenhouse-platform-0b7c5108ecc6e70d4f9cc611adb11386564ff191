package com.integradis.greenhouse.platform.crops.domain.model.entities;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class GrowRoomRecord extends CropEntry {
    @Getter
    private int grow_room;
    @Getter
    private float air_temperature;
    @Getter
    private String compost_temperature;
    @Getter
    private int carbon_dioxide;
    @Getter
    private int air_humidity;
    @Getter
    private float setting;
    @Getter
    private String comment;


    public GrowRoomRecord(Crop crop, String author, CropPhase phase,
                          int grow_room, float air_temperature, String compost_temperature, int carbon_dioxide,
                          int air_humidity, float setting, String comment){
        super(author, null, crop);
        this.cropPhase = phase;
        this.grow_room = grow_room;
        this.air_temperature = air_temperature;
        this.compost_temperature = compost_temperature;
        this.carbon_dioxide = carbon_dioxide;
        this.air_humidity = air_humidity;
        this.setting = setting;
        this.comment = comment;
    }

    public GrowRoomRecord() {
    }
}
