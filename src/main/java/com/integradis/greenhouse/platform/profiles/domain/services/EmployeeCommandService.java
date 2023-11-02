package com.integradis.greenhouse.platform.profiles.domain.services;

import com.integradis.greenhouse.platform.profiles.domain.model.commands.CreateEmployeeCommand;

public interface EmployeeCommandService {
    Long handle(CreateEmployeeCommand command);
}
