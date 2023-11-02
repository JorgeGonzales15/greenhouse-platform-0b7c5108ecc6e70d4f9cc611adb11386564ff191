package com.integradis.greenhouse.platform.crops.domain.model.valueobjects;

import com.integradis.greenhouse.platform.crops.domain.model.entities.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class CropEntryRecord {

    @OneToMany(mappedBy = "crop")
    public List<CropEntry> cropEntryList;

    public CropEntryRecord() {
        this.cropEntryList = new ArrayList<>();
    }

}
