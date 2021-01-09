package com.projettransversal.api.Models;


import javax.persistence.*;

@Entity
@Table(name = "incident", schema = "public")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "map_item_id", referencedColumnName = "id")
    private MapItem mapItem;

    @Column(name = "intensity")
    private float intensity;

    @Column(name = "incident_type")
    @Enumerated(EnumType.STRING)
    private IncidentType incidentType;

    public Incident(MapItem mapItem, float intensity, IncidentType incidentType) {
        this.mapItem = mapItem;
        this.intensity = intensity;
        this.incidentType = incidentType;
    }

    public Incident() {
    }

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public MapItem getMapItem() {
        return mapItem;
    }

    public void setMapItem(MapItem mapItem) {
        this.mapItem = mapItem;
    }
}
