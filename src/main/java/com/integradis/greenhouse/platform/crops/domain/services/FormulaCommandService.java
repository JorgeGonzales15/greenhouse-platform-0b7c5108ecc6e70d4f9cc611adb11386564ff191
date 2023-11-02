package com.integradis.greenhouse.platform.crops.domain.services;

import com.integradis.greenhouse.platform.crops.domain.model.commands.CreateFormulaCommand;

public interface FormulaCommandService {

    Long handle (CreateFormulaCommand command);
}
