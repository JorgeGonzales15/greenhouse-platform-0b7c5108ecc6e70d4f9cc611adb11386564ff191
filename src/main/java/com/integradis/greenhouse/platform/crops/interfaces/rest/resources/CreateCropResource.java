package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateCropResource(@NotNull Long companyId) {
}
