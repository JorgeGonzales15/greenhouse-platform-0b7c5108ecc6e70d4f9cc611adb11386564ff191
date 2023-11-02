package com.integradis.greenhouse.platform.crops.domain.model.queries;

import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;

public record GetGrowRoomByCropIdAndCropPhase(Long cropId, CropPhase cropPhase) {
}
