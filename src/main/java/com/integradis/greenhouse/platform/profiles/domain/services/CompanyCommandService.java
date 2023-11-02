package com.integradis.greenhouse.platform.profiles.domain.services;

import com.integradis.greenhouse.platform.profiles.domain.model.commands.CreateCompanyCommand;

public interface CompanyCommandService {
    Long handle(CreateCompanyCommand command);
}
