package com.projettransversal.api.Models;

public enum IncidentType {
    FLOOD("FLOOD"),
    FIRE("FIRE"),
    ACCIDENT("ACCIDENT");

    private final String text;

    IncidentType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
