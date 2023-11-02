package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.commands.CreateGrowRoomCommand;

public interface GrowRoomCommandService {
    Long handle (CreateGrowRoomCommand command);
}
