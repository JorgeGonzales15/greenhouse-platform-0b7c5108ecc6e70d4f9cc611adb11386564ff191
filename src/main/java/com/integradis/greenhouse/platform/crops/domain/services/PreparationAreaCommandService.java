package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.commands.CreatePreparationAreaCommand;

public interface PreparationAreaCommandService {
    Long handle (CreatePreparationAreaCommand command);
}
