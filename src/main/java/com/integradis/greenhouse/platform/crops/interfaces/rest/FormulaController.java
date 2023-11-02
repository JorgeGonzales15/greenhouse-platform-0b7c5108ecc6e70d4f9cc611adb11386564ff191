package com.integradis.greenhouse.platform.crops.interfaces.rest;

import com.integradis.greenhouse.platform.crops.domain.model.queries.GetFormulaByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetFormulaEntriesByCropId;
import com.integradis.greenhouse.platform.crops.domain.services.FormulaCommandService;
import com.integradis.greenhouse.platform.crops.domain.services.FormulaQueryService;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.CreateFormulaResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.FormulaResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.CreateFormulaCommandFromResourceAssembler;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.FormulaResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/formula")
@Tag(name = "Formula", description = "Formula Management Endpoints")
public class FormulaController {

    private final FormulaQueryService formulaQueryService;
    private final FormulaCommandService formulaCommandService;

    public FormulaController(FormulaQueryService formulaQueryService, FormulaCommandService formulaCommandService) {
        this.formulaQueryService = formulaQueryService;
        this.formulaCommandService = formulaCommandService;
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<List<FormulaResource>> getFormulaEntriesByCropId(@PathVariable Long cropId){
        var getFormulaEntriesByCropId = new GetFormulaEntriesByCropId(cropId);
        var entries = formulaQueryService.handle(getFormulaEntriesByCropId);
        if (entries.isEmpty()) return ResponseEntity.badRequest().build();
        var formulaResource = entries.stream().map((FormulaResourceFromEntityAssembler::toResourceFromEntity)).toList();
        return ResponseEntity.ok(formulaResource);
    }

    @PostMapping
    public ResponseEntity<FormulaResource> createFormula(@RequestBody CreateFormulaResource resource){
        var createFormulaCommand = CreateFormulaCommandFromResourceAssembler.toCommandFromResource(resource);
        var formulaId = formulaCommandService.handle(createFormulaCommand);
        if (formulaId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getFormulaById = new GetFormulaByIdQuery(formulaId);
        var formula = formulaQueryService.handle(getFormulaById);
        if (formula.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var formulaResource = FormulaResourceFromEntityAssembler.toResourceFromEntity(formula.get());
        return new ResponseEntity<>(formulaResource, HttpStatus.CREATED);
    }
}
