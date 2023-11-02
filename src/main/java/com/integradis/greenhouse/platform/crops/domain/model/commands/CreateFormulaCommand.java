package com.integradis.greenhouse.platform.crops.domain.model.commands;

public record CreateFormulaCommand(Long cropId, String author, int hay, int corn, int guano, float cottonSeedCake,
                                   int soybeanMeal, float gypsum, int urea, int ammoniumSulphate) {
}
