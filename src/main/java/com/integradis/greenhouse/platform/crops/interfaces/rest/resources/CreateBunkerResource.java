package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

public record CreateBunkerResource(
        Long cropId,
        String author,
        int thermocoupleOne,
        int thermocoupleTwo,
        int thermocoupleThree,
        float averageThermocouple,
        int motorFrequency,
        String comment
) {
}
