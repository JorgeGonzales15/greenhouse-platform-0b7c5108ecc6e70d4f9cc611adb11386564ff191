package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;

public record CreateGrowRoomResource(
        Long cropId,
        String author,
        CropPhase cropPhase,
        int grow_room,
        int air_temperature,
        String compost_temperature,
        int carbon_dioxide,
        int air_humidity,
        float setting,
        String comment
) {
}
