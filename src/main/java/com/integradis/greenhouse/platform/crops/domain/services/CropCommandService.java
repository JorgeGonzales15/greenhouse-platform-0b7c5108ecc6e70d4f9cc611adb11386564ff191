package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.commands.CreateCropCommand;

public interface CropCommandService {
    Long handle(CreateCropCommand command);
}
