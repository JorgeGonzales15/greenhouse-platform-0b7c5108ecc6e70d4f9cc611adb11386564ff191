package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

public record PreparationAreaResource(
        Long preparationAreaId,
        String author,
        int activities,
        int temperature,
        String comment
) {
}
