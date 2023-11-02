package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

public record CreatePreparationAreaResource(
        Long cropId, String author, int activities, int temperature, String comment
) {
}
