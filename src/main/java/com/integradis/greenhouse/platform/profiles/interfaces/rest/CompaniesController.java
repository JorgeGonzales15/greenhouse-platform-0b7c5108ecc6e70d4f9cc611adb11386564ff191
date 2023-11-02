package com.integradis.greenhouse.platform.profiles.interfaces.rest;

import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetCompanyByIdQuery;
import com.integradis.greenhouse.platform.profiles.domain.services.CompanyCommandService;
import com.integradis.greenhouse.platform.profiles.domain.services.CompanyQueryService;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.resources.CompanyResource;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.resources.CreateCompanyResource;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.transform.CompanyResourceFromEntityAssembler;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.transform.CreateCompanyCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value = "/api/v1/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Companies", description = "Companies Management Endpoints")
public class CompaniesController {
    private final CompanyQueryService companyQueryService;
    private final CompanyCommandService companyCommandService;

    public CompaniesController(CompanyQueryService companyQueryService, CompanyCommandService companyCommandService) {
        this.companyQueryService = companyQueryService;
        this.companyCommandService = companyCommandService;
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResource> getCompanyById(@PathVariable Long companyId) {
        var getCompanyByIdQuery = new GetCompanyByIdQuery(companyId);
        var company = companyQueryService.handle(getCompanyByIdQuery);
        if (company.isEmpty()) return ResponseEntity.badRequest().build();
        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return ResponseEntity.ok(companyResource);
    }

    @PostMapping
    public ResponseEntity<CompanyResource> createCompany(@RequestBody CreateCompanyResource resource) {
        var createCompanyCommand = CreateCompanyCommandFromResourceAssembler.toCommandFromResource(resource);
        var companyId = companyCommandService.handle(createCompanyCommand);
        if (companyId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getCompanyByIdQuery = new GetCompanyByIdQuery(companyId);
        var company = companyQueryService.handle(getCompanyByIdQuery);

        if (company.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var companyResource = CompanyResourceFromEntityAssembler.toResourceFromEntity(company.get());
        return new ResponseEntity<>(companyResource, HttpStatus.CREATED);
    }

}
