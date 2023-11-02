package com.integradis.greenhouse.platform.crops.domain.model.commands;

public record CreatePreparationAreaCommand(
        Long cropId, String author, int activities, int temperature, String comment
) {
}
