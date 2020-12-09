DROP TABLE IF EXISTS map_item;
CREATE TABLE map_item
(
    id integer NOT NULL,
    ground character varying(255) COLLATE pg_catalog."default",
    posx integer,
    posy integer,
    CONSTRAINT map_item_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS fire;
CREATE TABLE fire
(
    id integer NOT NULL,
    intensity real,
    map_item_id integer,
    CONSTRAINT fire_pkey PRIMARY KEY (id),
    CONSTRAINT fkm902tmdwh8ul5i3onutv14gy2 FOREIGN KEY (map_item_id)
        REFERENCES map_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

DROP TABLE IF EXISTS truck;
CREATE TABLE truck
(
    id integer NOT NULL,
    map_item_id integer,
    CONSTRAINT truck_pkey PRIMARY KEY (id),
    CONSTRAINT fk7hhcvj05kgi7u4ac9scwb0dlp FOREIGN KEY (map_item_id)
        REFERENCES map_item (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
