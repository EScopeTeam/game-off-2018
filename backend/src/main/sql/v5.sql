CREATE TYPE bug_status AS ENUM ('ALIVE', 'DEAD', 'HUNGRY', 'SAD', 'HUNGRY_AND_SAD');

ALTER TABLE public.bugs ADD COLUMN update_time timestamp with time zone DEFAULT CURRENT_DATE NOT NULL;
ALTER TABLE public.bugs ADD COLUMN current_food_level bigint DEFAULT 0 NOT NULL;
ALTER TABLE public.bugs ADD COLUMN max_food_level bigint DEFAULT 1 NOT NULL;
ALTER TABLE public.bugs ADD COLUMN current_happiness_level bigint DEFAULT 0 NOT NULL;
ALTER TABLE public.bugs ADD COLUMN max_happiness_level bigint DEFAULT 1 NOT NULL;
ALTER TABLE public.bugs ADD COLUMN status bug_status DEFAULT 'ALIVE' NOT NULL;