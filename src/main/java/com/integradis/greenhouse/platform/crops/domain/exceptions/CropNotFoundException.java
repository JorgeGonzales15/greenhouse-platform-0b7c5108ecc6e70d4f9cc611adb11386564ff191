package com.integradis.greenhouse.platform.crops.domain.exceptions;

public class CropNotFoundException extends RuntimeException{
    public CropNotFoundException(Long cropId) {
        super("Crop with Id " + cropId + " not found");
    }
}
