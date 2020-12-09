package com.projettransversal.api.Models;

public enum Ground {
    ROAD("ROAD"),
    GARDEN("ROAD"),
    BUILDING("BUILDING");

    private final String text;

    Ground(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
