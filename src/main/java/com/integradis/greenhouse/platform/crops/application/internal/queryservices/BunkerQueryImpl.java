package com.integradis.greenhouse.platform.crops.application.internal.queryservices;

import com.integradis.greenhouse.platform.crops.domain.model.entities.Bunker;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetBunkerByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetBunkerEntriesByCropIdQuery;
import com.integradis.greenhouse.platform.crops.domain.services.BunkerQueryService;
import com.integradis.greenhouse.platform.crops.infrastructure.persistence.jpa.repositories.BunkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BunkerQueryImpl implements BunkerQueryService {
    private final BunkerRepository bunkerRepository;

    public BunkerQueryImpl(BunkerRepository bunkerRepository) {
        this.bunkerRepository = bunkerRepository;
    }

    @Override
    public List<Bunker> handle(GetBunkerEntriesByCropIdQuery query) {
        return bunkerRepository.findAllByCropId(query.cropId());
    }

    @Override
    public Optional<Bunker> handle(GetBunkerByIdQuery query) {
        return bunkerRepository.findById(query.id());
    }
}
