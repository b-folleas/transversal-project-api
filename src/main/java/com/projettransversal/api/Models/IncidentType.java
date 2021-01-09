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

    public static IncidentType fromString(String type) {
        IncidentType incidentType;
        switch (type) {
            case "F":
                incidentType = IncidentType.FIRE;
                break;
            case "I":
                incidentType = IncidentType.FLOOD;
                break;
            case "A":
                incidentType = IncidentType.ACCIDENT;
                break;
            case "T":
                incidentType = IncidentType.TORNADO;
                break;
            default:
                incidentType = IncidentType.FIRE;
        }
        return incidentType;
    }

    @Override
    public String toString() {
        return text;
    }
}
