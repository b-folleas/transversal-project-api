CREATE SCHEMA IF NOT EXISTS public;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

DROP TABLE IF EXISTS map_item;
CREATE TABLE public.map_item
(
    id     bigint NOT NULL,
    ground character varying(255),
    posx   integer,
    posy   integer,
    CONSTRAINT pk_map_item PRIMARY KEY (id)
);

DROP TABLE IF EXISTS incident;
CREATE TABLE public.incident
(
    id            bigint NOT NULL,
    intensity     real,
    map_item_id   bigint,
    incident_type character varying(255),
    CONSTRAINT pk_incident PRIMARY KEY (id),
    CONSTRAINT fk_map_item_id FOREIGN KEY (map_item_id)
        REFERENCES public.map_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX ON "incident" ("map_item_id");
