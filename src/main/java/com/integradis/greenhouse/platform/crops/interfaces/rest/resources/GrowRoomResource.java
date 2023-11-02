package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

public record GrowRoomResource(
        Long growRoomId,
        String author,
        String cropPhase,
        int growRoom,
        float airTemperature,
        String compostTemperature,
        int carbonDioxide,
        int airHumidity,
        float setting,
        String comment

) {
}
