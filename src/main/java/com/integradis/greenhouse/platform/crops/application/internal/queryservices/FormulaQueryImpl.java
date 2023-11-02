package com.integradis.greenhouse.platform.crops.application.internal.queryservices;

import com.integradis.greenhouse.platform.crops.domain.model.entities.Formula;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetFormulaByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetFormulaEntriesByCropId;
import com.integradis.greenhouse.platform.crops.domain.services.FormulaQueryService;
import com.integradis.greenhouse.platform.crops.infrastructure.persistence.jpa.repositories.FormulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormulaQueryImpl implements FormulaQueryService {

    private final FormulaRepository formulaRepository;

    public FormulaQueryImpl(FormulaRepository formulaRepository) {
        this.formulaRepository = formulaRepository;
    }

    @Override
    public List<Formula> handle(GetFormulaEntriesByCropId query) {
        return formulaRepository.findAllByCropId(query.cropId());
    }

    @Override
    public Optional<Formula> handle(GetFormulaByIdQuery query) {
        return formulaRepository.findById(query.id());
    }


}
