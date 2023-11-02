package com.integradis.greenhouse.platform.crops.interfaces.rest;

import com.integradis.greenhouse.platform.crops.domain.model.queries.GetGrowRoomByCropIdAndCropPhase;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetGrowRoomByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.valueobjects.CropPhase;
import com.integradis.greenhouse.platform.crops.domain.services.GrowRoomCommandService;
import com.integradis.greenhouse.platform.crops.domain.services.GrowRoomQueryService;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.CreateGrowRoomResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.GrowRoomResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.CreateGrowRoomCommandFromResourceAssembler;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.GrowRoomResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/grow-room")
@Tag(name = "Grow Room", description = "Grow Room Management Endpoints")
public class GrowRoomController {
    private final GrowRoomCommandService growRoomCommandService;
    private final GrowRoomQueryService growRoomQueryService;

    public GrowRoomController(GrowRoomCommandService growRoomCommandService, GrowRoomQueryService growRoomQueryService) {
        this.growRoomCommandService = growRoomCommandService;
        this.growRoomQueryService = growRoomQueryService;
    }


    @GetMapping("/{cropId}/{cropPhase}")
    public ResponseEntity<List<GrowRoomResource>> getGrowRoomEntriesByCropIdAndCropPhase(@PathVariable Long cropId, @PathVariable CropPhase cropPhase) {
        var getGrowRoomEntriesByCropIdAndCropPhase = new GetGrowRoomByCropIdAndCropPhase(cropId, cropPhase);
        var entries = growRoomQueryService.handle(getGrowRoomEntriesByCropIdAndCropPhase);
        if (entries.isEmpty()) return ResponseEntity.badRequest().build();
        var growRoomResource = entries.stream().map((GrowRoomResourceFromEntityAssembler::toResourceFromEntity)).toList();
        return ResponseEntity.ok(growRoomResource);
    }

    @PostMapping
    public ResponseEntity<GrowRoomResource> createGrowRoom(@RequestBody CreateGrowRoomResource resource){
        var createGrowRoomCommand = CreateGrowRoomCommandFromResourceAssembler.toCommandFromResource(resource);
        var growRoomId = growRoomCommandService.handle(createGrowRoomCommand);
        if (growRoomId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getGrowRoomById = new GetGrowRoomByIdQuery(growRoomId);
        var growRoom = growRoomQueryService.handle(getGrowRoomById);
        if (growRoom.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var growRoomResource = GrowRoomResourceFromEntityAssembler.toResourceFromEntity(growRoom.get());
        return ResponseEntity.ok(growRoomResource);
    }
}