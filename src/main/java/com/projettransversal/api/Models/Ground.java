package com.projettransversal.api.Models;

import java.io.Serializable;

public enum Ground implements Serializable {
    ROAD("ROAD"),
    GARDEN("ROAD"),
    BUILDING("BUILDING"),
    WATER("WATER");

    private final String text;

    Ground(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
