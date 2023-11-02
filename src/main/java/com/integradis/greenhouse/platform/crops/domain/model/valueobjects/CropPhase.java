package com.integradis.greenhouse.platform.crops.domain.model.valueobjects;

import java.util.Optional;

public enum CropPhase {
    FORMULA,
    PREPARATION_AREA,
    BUNKER,
    TUNNEL,
    INCUBATION,
    CASING,
    INDUCTION,
    HARVEST;

    public Optional<CropPhase> next() {
        return switch (this) {
            case FORMULA -> Optional.of(PREPARATION_AREA);
            case PREPARATION_AREA -> Optional.of(BUNKER);
            case BUNKER -> Optional.of(TUNNEL);
            case TUNNEL -> Optional.of(INCUBATION);
            case INCUBATION -> Optional.of(CASING);
            case CASING -> Optional.of(INDUCTION);
            case INDUCTION -> Optional.of(HARVEST);
            default -> Optional.empty();
        };
    }
}
