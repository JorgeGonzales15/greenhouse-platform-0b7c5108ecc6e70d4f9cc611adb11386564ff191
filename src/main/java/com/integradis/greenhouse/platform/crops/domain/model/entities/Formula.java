package com.integradis.greenhouse.platform.crops.domain.model.entities;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
public class Formula extends CropEntry {

    @Getter
    private int hay;

    @Getter
    private int corn;

    @Getter
    private int guano;

    @Getter
    private float cottonSeedCake;

    @Getter
    private int soybeanMeal;

    @Getter
    private float gypsum;

    @Getter
    private int urea;

    @Getter
    private int ammoniumSulphate;

    public Formula(){
        this.cropPhase = CropPhase.FORMULA;
    }

    public Formula (Crop crop, String author, int hay, int corn, int guano, float cottonSeedCake, int soybeanMeal,
                    float gypsum, int urea, int ammoniumSulphate){
        super(author, null, crop);
        this.cropPhase = CropPhase.FORMULA;
        this.hay = hay;
        this.corn = corn;
        this.guano = guano;
        this.cottonSeedCake = cottonSeedCake;
        this.soybeanMeal = soybeanMeal;
        this.gypsum = gypsum;
        this.urea = urea;
        this.ammoniumSulphate = ammoniumSulphate;
    }

    public Formula(String author, Long nextItemId, Crop crop){
        super(author, nextItemId, crop);
        this.cropPhase = CropPhase.FORMULA;
    }
}
