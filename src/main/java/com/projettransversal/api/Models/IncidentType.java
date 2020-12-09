package com.projettransversal.api.Models;

public enum IncidentType {
    FLOOD("FLOOD"),
    FIRE("FIRE"),
    ACCIDENT("ACCIDENT"),
    TORNADO("TORNADO");

    private final String text;

    IncidentType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
