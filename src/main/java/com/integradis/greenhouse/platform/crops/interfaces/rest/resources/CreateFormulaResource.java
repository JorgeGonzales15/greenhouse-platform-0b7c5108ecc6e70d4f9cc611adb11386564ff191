package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateFormulaResource(
                                    @NotNull Long cropId,
                                    String author,
                                    int hay,
                                    int corn,
                                    int guano,
                                    float cottonSeedCake,
                                    int soybeanMeal,
                                    float gypsum,
                                    int urea,
                                    int ammoniumSulphate) {
}
