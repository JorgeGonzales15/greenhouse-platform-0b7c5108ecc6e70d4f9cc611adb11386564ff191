package com.integradis.greenhouse.platform.profiles.domain.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(Long companyId) {
        super("Crop with Id " + companyId + " not found");
    }
}
