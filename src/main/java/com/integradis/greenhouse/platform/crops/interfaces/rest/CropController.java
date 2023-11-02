package com.integradis.greenhouse.platform.crops.interfaces.rest;

import com.integradis.greenhouse.platform.crops.domain.model.queries.GetCropByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetCropsByCompanyId;
import com.integradis.greenhouse.platform.crops.domain.services.CropCommandService;
import com.integradis.greenhouse.platform.crops.domain.services.CropQueryService;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.CreateCropResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.CropResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.CreateCropCommandFromResourceAssembler;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.CropResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/crops")
@Tag(name = "Crops", description = "Crop Management Endpoints")
public class CropController {
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<CropResource> getCropById(@PathVariable Long cropId) {
        var getCropByIdQuery = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropByIdQuery);
        if (crop.isEmpty()) return ResponseEntity.badRequest().build();
        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<CropResource>> getCropsByCompanyId(@PathVariable Long companyId){
        var getCropsByCompanyId = new GetCropsByCompanyId(companyId);
        var crops = cropQueryService.handle(getCropsByCompanyId);
        if (crops.isEmpty()) return ResponseEntity.badRequest().build();
        var cropResource = crops.stream().map((CropResourceFromEntityAssembler::toResourceFromEntity)).toList();
        return ResponseEntity.ok(cropResource);
    }


    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource){
        var createCropCommand = CreateCropCommandFromResourceAssembler.toCommandFromResource(resource);
        var cropId = cropCommandService.handle(createCropCommand);
        if (cropId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getCropById = new GetCropByIdQuery(cropId);
        var crop = cropQueryService.handle(getCropById);
        if (crop.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(crop.get());
        return new ResponseEntity<>(cropResource, HttpStatus.CREATED);
    }
}
