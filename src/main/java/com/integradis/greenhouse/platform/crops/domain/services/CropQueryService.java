package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetCropByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetCropsByCompanyId;

import java.util.List;
import java.util.Optional;

public interface CropQueryService {
    List<Crop> handle (GetCropsByCompanyId query);

    Optional<Crop> handle (GetCropByIdQuery query);
}
