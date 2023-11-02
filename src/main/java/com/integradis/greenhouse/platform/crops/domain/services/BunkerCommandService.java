package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.commands.CreateBunkerCommand;

public interface BunkerCommandService {
    Long handle (CreateBunkerCommand command);
}
