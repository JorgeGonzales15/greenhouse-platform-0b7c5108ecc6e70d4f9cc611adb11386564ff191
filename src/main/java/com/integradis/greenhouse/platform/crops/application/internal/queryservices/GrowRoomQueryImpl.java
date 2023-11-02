package com.integradis.greenhouse.platform.crops.application.internal.queryservices;

import com.integradis.greenhouse.platform.crops.domain.model.entities.GrowRoomRecord;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetGrowRoomByCropIdAndCropPhase;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetGrowRoomByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.services.GrowRoomQueryService;
import com.integradis.greenhouse.platform.crops.infrastructure.persistence.jpa.repositories.GrowRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrowRoomQueryImpl implements GrowRoomQueryService {
    private final GrowRoomRepository growRoomRepository;

    public GrowRoomQueryImpl(GrowRoomRepository growRoomRepository) {
        this.growRoomRepository = growRoomRepository;
    }

    @Override
    public List<GrowRoomRecord> handle(GetGrowRoomByCropIdAndCropPhase query) {
        return growRoomRepository.findAllByCropIdAndCropPhase(query.cropId(), query.cropPhase());
    }

    @Override
    public Optional<GrowRoomRecord> handle(GetGrowRoomByIdQuery query) {
        return growRoomRepository.findById(query.id());
    }
}
