package com.projettransversal.api.Models;


import javax.persistence.*;

@Entity
@Table(name = "incident_truck", schema = "public")
public class IncidentTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne()
    @JoinColumn(name = "incident_id", referencedColumnName = "id")
    private Incident incident;

    @OneToOne()
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private Truck truck;

    public IncidentTruck() {
    }

    public IncidentTruck(int id, Incident incident, Truck truck) {
        this.id = id;
        this.incident = incident;
        this.truck = truck;
    }
}
