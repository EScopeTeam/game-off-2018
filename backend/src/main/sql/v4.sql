ALTER TABLE public.players
    ADD COLUMN active boolean DEFAULT false NOT NULL;

ALTER TABLE public.players
    ADD COLUMN creation_time timestamp with time zone DEFAULT CURRENT_DATE NOT NULL;

ALTER TABLE public.players
    ADD COLUMN update_time timestamp with time zone DEFAULT CURRENT_DATE NOT NULL;

ALTER TABLE public.players
    ADD COLUMN coins double precision DEFAULT 0.0 NOT NULL;

ALTER TABLE public.players
    ADD COLUMN experience_points bigint DEFAULT 0 NOT NULL;