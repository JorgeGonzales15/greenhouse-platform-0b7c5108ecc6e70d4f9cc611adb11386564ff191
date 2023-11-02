package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.commands.CreateTunnelCommand;

public interface TunnelCommandService {
    Long handle (CreateTunnelCommand command);
}
