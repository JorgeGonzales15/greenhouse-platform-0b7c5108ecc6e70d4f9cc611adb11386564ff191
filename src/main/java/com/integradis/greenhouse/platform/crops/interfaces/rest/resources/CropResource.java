package com.integradis.greenhouse.platform.crops.interfaces.rest.resources;

import java.util.Date;

public record CropResource(Long cropId, String cropPhase, Date startDate, Date endDate, boolean state, Long companyId) {
}
