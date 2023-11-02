package com.integradis.greenhouse.platform.crops.domain.model.commands;

import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;

public record CreateGrowRoomCommand(
        Long cropId,
        String author,
        CropPhase cropPhase,
        int grow_room,
        float air_temperature,
        String compost_temperature,
        int carbon_dioxide,
        int air_humidity,
        float setting,
        String comment
) {
}
