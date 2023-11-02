package com.integradis.greenhouse.platform.crops.interfaces.rest;

import com.integradis.greenhouse.platform.crops.domain.model.queries.GetTunnelByIdQuery;
import com.integradis.greenhouse.platform.crops.domain.model.queries.GetTunnelEntriesByCropIdQuery;
import com.integradis.greenhouse.platform.crops.domain.services.TunnelCommandService;
import com.integradis.greenhouse.platform.crops.domain.services.TunnelQueryService;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.CreateTunnelResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.resources.TunnelResource;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.CreateTunnelCommandFromResourceAssembler;
import com.integradis.greenhouse.platform.crops.interfaces.rest.transform.TunnelResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/tunnel")
@Tag(name = "Tunnel", description = "Tunnel Management Endpoints")
public class TunnelController {
    private final TunnelQueryService tunnelQueryService;
    private final TunnelCommandService tunnelCommandService;

    public TunnelController(TunnelQueryService tunnelQueryService, TunnelCommandService tunnelCommandService) {
        this.tunnelQueryService = tunnelQueryService;
        this.tunnelCommandService = tunnelCommandService;
    }

    @GetMapping("{cropId}")
    public ResponseEntity<List<TunnelResource>> getTunnelEntriesByCropId(@PathVariable Long cropId){
        var getTunnelEntriesByCropId = new GetTunnelEntriesByCropIdQuery(cropId);
        var entries = tunnelQueryService.handle(getTunnelEntriesByCropId);
        if (entries.isEmpty()) return ResponseEntity.badRequest().build();
        var tunnelResource = entries.stream().map((TunnelResourceFromEntityAssembler::toResourceFromEntity)).toList();
        return ResponseEntity.ok(tunnelResource);
    }

    @PostMapping
    public ResponseEntity<TunnelResource> createTunnel(@RequestBody CreateTunnelResource resource){
        var createTunnelCommand = CreateTunnelCommandFromResourceAssembler.toCommandFromResource(resource);
        var tunnelId = tunnelCommandService.handle(createTunnelCommand);
        if (tunnelId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getTunnelById = new GetTunnelByIdQuery(tunnelId);
        var tunnel = tunnelQueryService.handle(getTunnelById);
        if (tunnel.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var tunnelResource = TunnelResourceFromEntityAssembler.toResourceFromEntity(tunnel.get());
        return ResponseEntity.ok(tunnelResource);
    }
}
