package com.integradis.greenhouse.platform.crops.domain.model.commands;

public record CreateTunnelCommand(
        Long cropId,
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
