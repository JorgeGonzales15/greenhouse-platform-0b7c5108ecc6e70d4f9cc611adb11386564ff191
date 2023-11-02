package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.entities.GrowRoomRecord;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetGrowRoomByCropIdAndCropPhase;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetGrowRoomByIdQuery;

import java.util.List;
import java.util.Optional;

public interface GrowRoomQueryService {
    List<GrowRoomRecord> handle (GetGrowRoomByCropIdAndCropPhase query);

    Optional<GrowRoomRecord> handle (GetGrowRoomByIdQuery query);
}
