package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

public record TunnelResource(
        Long tunnelId,
        String author,
        int thermocoupleOne,
        int thermocoupleTwo,
        int thermocoupleThree,
        float averageThermocouple,
        int motorFrequency,
        float roomTemperature,
        int freshAir,
        int recirculation,
        String comment
) {
}
