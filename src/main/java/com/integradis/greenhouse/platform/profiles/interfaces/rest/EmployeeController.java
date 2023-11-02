package com.integradis.greenhouse.platform.profiles.interfaces.rest;

import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetEmployeeByIdQuery;
import com.integradis.greenhouse.platform.profiles.domain.model.queries.GetEmployeesByCompanyIdQuery;
import com.integradis.greenhouse.platform.profiles.domain.services.EmployeeCommandService;
import com.integradis.greenhouse.platform.profiles.domain.services.EmployeeQueryService;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.resources.CreateEmployeeResource;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.resources.EmployeeResource;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.integradis.greenhouse.platform.profiles.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value ="/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employees", description = "Employees Management Endpoints")
public class EmployeeController {
    private final EmployeeQueryService employeeQueryService;
    private final EmployeeCommandService employeeCommandService;

    public EmployeeController(EmployeeQueryService employeeQueryService, EmployeeCommandService employeeCommandService) {
        this.employeeQueryService = employeeQueryService;
        this.employeeCommandService = employeeCommandService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResource> createEmployee(@RequestBody CreateEmployeeResource resource) {
        var createEmployeeCommand = CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(resource);
        var employeeId = employeeCommandService.handle(createEmployeeCommand);
        if (employeeId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getEmployeeByIdQuery = new GetEmployeeByIdQuery(employeeId);
        var employee = employeeQueryService.handle(getEmployeeByIdQuery);

        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get());
        return new ResponseEntity<>(employeeResource, HttpStatus.CREATED);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResource> getEmployeeById(@PathVariable Long employeeId) {
        var getEmployeeByIdQuery = new GetEmployeeByIdQuery(employeeId);
        var employee = employeeQueryService.handle(getEmployeeByIdQuery);
        if (employee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee.get());
        return ResponseEntity.ok(employeeResource);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<EmployeeResource>> getEmployeeByCompanyId(@PathVariable Long companyId) {
        var getEmployeeByCompanyIdQuery = new GetEmployeesByCompanyIdQuery(companyId);
        var employee = employeeQueryService.handle(getEmployeeByCompanyIdQuery);
        if (employee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var employeeResource = employee.stream().map(EmployeeResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(employeeResource);
    }
}
