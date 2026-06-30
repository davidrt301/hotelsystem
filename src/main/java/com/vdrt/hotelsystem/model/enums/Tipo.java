package com.vdrt.hotelsystem.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Tipo {
    SIMPLE,
    DOBLE, 
    SUITE;

    @JsonCreator
    public static Tipo fromString(String value) {
        return Tipo.valueOf(value.toUpperCase());
    }
}
