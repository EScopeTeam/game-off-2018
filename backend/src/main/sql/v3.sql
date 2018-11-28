CREATE SEQUENCE public.bugs_bug_id_seq;

ALTER SEQUENCE public.bugs_bug_id_seq
    OWNER TO bichos;

CREATE TABLE public.bugs
(
    bug_id integer NOT NULL DEFAULT nextval('bugs_bug_id_seq'::regclass),
    bug_race_id integer NOT NULL,
    player_id integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT bugs_pkey PRIMARY KEY (bug_id),
    CONSTRAINT bug_race_id_bugs FOREIGN KEY (bug_race_id)
        REFERENCES public.bugs_races (bug_race_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT player_id_bugs FOREIGN KEY (player_id)
        REFERENCES public.players (player_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bugs
    OWNER to bichos;
    
CREATE TABLE public.bugs_selected_parts
(
    bug_id integer NOT NULL,
    bug_part_id integer NOT NULL,
    bug_pattern_id integer NOT NULL,
    CONSTRAINT bugs_selected_parts_pkey PRIMARY KEY (bug_id, bug_part_id),
    CONSTRAINT bug_id_bugs_selected_parts FOREIGN KEY (bug_id)
        REFERENCES public.bugs (bug_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT bug_part_id_bugs_selected_parts FOREIGN KEY (bug_part_id)
        REFERENCES public.bugs_parts (bug_part_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT bug_pattern_id_bugs_selected_parts FOREIGN KEY (bug_pattern_id)
        REFERENCES public.bugs_patterns (bug_pattern_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bugs_selected_parts
    OWNER to bichos;
    
CREATE TABLE public.bugs_selected_images
(
    bug_id integer NOT NULL,
    bug_image_id integer NOT NULL,
    bug_color_id integer,
    CONSTRAINT bugs_selected_images_pkey PRIMARY KEY (bug_id, bug_image_id),
    CONSTRAINT bug_color_id_bugs_selected_images FOREIGN KEY (bug_color_id)
        REFERENCES public.bugs_colors (bug_color_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT bug_id_bugs_selected_images FOREIGN KEY (bug_id)
        REFERENCES public.bugs (bug_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT bug_image_id_bugs_selected_images FOREIGN KEY (bug_image_id)
        REFERENCES public.bugs_images (bug_image_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bugs_selected_images
    OWNER to bichos;