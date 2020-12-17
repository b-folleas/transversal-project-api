CREATE SCHEMA IF NOT EXISTS public;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS map_item;
CREATE TABLE public.map_item
(
    id integer NOT NULL,
    ground character varying(255),
    posx integer,
    posy integer,
    CONSTRAINT pk_map_item PRIMARY KEY (id)
);

DROP TABLE IF EXISTS incident;
CREATE TABLE public.incident
(
    id integer NOT NULL,
    intensity real,
    map_item_id integer,
    incident_type character varying(255),
    CONSTRAINT pk_incident PRIMARY KEY (id),
    CONSTRAINT fk_map_item_id FOREIGN KEY (map_item_id)
        REFERENCES public.map_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS barrack;
CREATE TABLE public.barrack
(
    id integer NOT NULL,
    name character varying(255),
    map_item_id integer,
    CONSTRAINT pk_barrack PRIMARY KEY (id),
    CONSTRAINT fk_map_item_id FOREIGN KEY (map_item_id)
        REFERENCES public.map_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS truck;
CREATE TABLE public.truck
(
    id integer NOT NULL,
    matricule integer NOT NULL,
    availability boolean DEFAULT true,
    map_item_id integer,
    barrack_id integer,
    CONSTRAINT pk_truck PRIMARY KEY (id),
    CONSTRAINT fk_map_item_id FOREIGN KEY (map_item_id)
        REFERENCES public.map_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_barrack_id FOREIGN KEY (barrack_id)
        REFERENCES public.barrack (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS incident_truck;
CREATE TABLE public.incident_truck
(
    id integer NOT NULL,
    incident_id integer NOT NULL,
    truck_id integer NOT NULL,
    CONSTRAINT pk_incident_truck PRIMARY KEY (id),
    CONSTRAINT fk_incident_id FOREIGN KEY (incident_id)
        REFERENCES public.incident (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_truck_id FOREIGN KEY (truck_id)
        REFERENCES public.truck (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ON "incident" ("map_item_id");
CREATE INDEX ON "truck" ("barrack_id");
CREATE INDEX ON "truck" ("map_item_id");
CREATE INDEX ON "barrack" ("map_item_id");
CREATE UNIQUE INDEX ON "incident_truck" ("truck_id", "incident_id");
CREATE INDEX ON "incident_truck" ("incident_id");
CREATE INDEX ON "incident_truck" ("truck_id");
