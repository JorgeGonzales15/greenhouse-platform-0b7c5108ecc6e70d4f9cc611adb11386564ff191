package com.integradis.greenhouse.platform.profiles.domain.model.commands;


public record CreateEmployeeCommand(String firstName, String lastName, String email, Long companyId) {
}
