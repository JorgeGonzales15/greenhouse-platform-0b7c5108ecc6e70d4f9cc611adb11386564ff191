package com.integradis.greenhouse.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TIN(String number) {
    public TIN(){
        this(null);
    }
}
