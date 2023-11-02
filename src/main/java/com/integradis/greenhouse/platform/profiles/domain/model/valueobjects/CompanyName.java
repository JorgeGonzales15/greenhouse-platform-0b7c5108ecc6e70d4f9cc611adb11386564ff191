package com.integradis.greenhouse.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CompanyName(String name) {
    public CompanyName(){
        this(null);
    }
}
