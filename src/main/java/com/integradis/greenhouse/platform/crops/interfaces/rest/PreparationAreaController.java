package com.integradis.greenhouse.platform.crops.interfaces.rest;

import com.integradis.greenhouse.platform.crops.domain.model.queries.GetPreparationAreaByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetPreparationAreaEntriesByCropIdQuery;
import com.integradis.greenhouse.platform.crops.domain.services.PreparationAreaCommandService;
import com.integradis.greenhouse.platform.crops.domain.services.PreparationAreaQueryService;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.CreatePreparationAreaResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.PreparationAreaResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.CreatePreparationAreaCommandFromResourceAssembler;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.PreparationAreaResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/preparationArea")
@Tag(name = "Preparation Area", description = "Preparation Area Management Endpoints")
public class PreparationAreaController {

    private final PreparationAreaQueryService preparationAreaQueryService;
    private final PreparationAreaCommandService preparationAreaCommandService;

    public PreparationAreaController(PreparationAreaQueryService preparationAreaQueryService, PreparationAreaCommandService preparationAreaCommandService) {
        this.preparationAreaQueryService = preparationAreaQueryService;
        this.preparationAreaCommandService = preparationAreaCommandService;
    }


    @GetMapping("/{cropId}")
    public ResponseEntity<List<PreparationAreaResource>> getPreparationAreaEntriesByCropId(@PathVariable Long cropId){
        var getPreparationAreaEntriesByCropId = new GetPreparationAreaEntriesByCropIdQuery(cropId);
        var entries = preparationAreaQueryService.handle(getPreparationAreaEntriesByCropId);
        if (entries.isEmpty()) return ResponseEntity.badRequest().build();
        var preparationAreaResource = entries.stream().map((PreparationAreaResourceFromEntityAssembler::toResourceFromEntity)).toList();
        return ResponseEntity.ok(preparationAreaResource);
    }

    @PostMapping
    public ResponseEntity<PreparationAreaResource> createPreparationArea(@RequestBody CreatePreparationAreaResource resource){
        var createPreparationAreaCommand = CreatePreparationAreaCommandFromResourceAssembler.toCommandFromResource(resource);
        var preparationAreaId = preparationAreaCommandService.handle(createPreparationAreaCommand);
        if (preparationAreaId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getPreparationAreaById = new GetPreparationAreaByIdQuery(preparationAreaId);
        var preparationArea = preparationAreaQueryService.handle(getPreparationAreaById);
        if (preparationArea.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var preparationAreaResource = PreparationAreaResourceFromEntityAssembler.toResourceFromEntity(preparationArea.get());
        return ResponseEntity.ok(preparationAreaResource);
    }

}
