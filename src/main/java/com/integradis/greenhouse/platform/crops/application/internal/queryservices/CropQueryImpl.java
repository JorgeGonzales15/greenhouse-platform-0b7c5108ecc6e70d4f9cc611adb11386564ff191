package com.integradis.greenhouse.platform.crops.application.internal.queryservices;

import com.integradis.greenhouse.platform.crops.domain.model.aggregates.Crop;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetCropByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetCropsByCompanyId;
import com.integradis.greenhouse.platform.crops.domain.services.CropQueryService;
import com.integradis.greenhouse.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryImpl implements CropQueryService {
    private final CropRepository cropRepository;

    public CropQueryImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public List<Crop> handle(GetCropsByCompanyId query) {
        return cropRepository.findAllByCompanyId(query.companyId());
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query) {
        return cropRepository.findById(query.id());
    }
}
