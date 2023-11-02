package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.entities.PreparationArea;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetPreparationAreaByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetPreparationAreaEntriesByCropIdQuery;

import java.util.List;
import java.util.Optional;

public interface PreparationAreaQueryService {
    List<PreparationArea> handle (GetPreparationAreaEntriesByCropIdQuery query);

    Optional<PreparationArea> handle (GetPreparationAreaByIdQuery query);
}
