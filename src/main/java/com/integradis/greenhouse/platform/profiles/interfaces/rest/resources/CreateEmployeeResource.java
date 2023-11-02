package com.integradis.greenhouse.platform.profiles.interfaces.rest.resources;


public record CreateEmployeeResource(String firstName, String lastName, String email, Long companyId) {
}
