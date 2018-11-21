--
-- TOC entry 208 (class 1259 OID 16490)
-- Name: bugs_color_palettes; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.bugs_color_palettes (
    bug_color_palette_id bigint NOT NULL,
    bug_race_id integer NOT NULL,
    name character varying(50) NOT NULL,
    generation_chance integer NOT NULL
);


ALTER TABLE public.bugs_color_palettes OWNER TO bichos;

--
-- TOC entry 207 (class 1259 OID 16488)
-- Name: bugs_color_palettes_bug_color_palette_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--

CREATE SEQUENCE public.bugs_color_palettes_bug_color_palette_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bugs_color_palettes_bug_color_palette_id_seq OWNER TO bichos;

--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 207
-- Name: bugs_color_palettes_bug_color_palette_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--

ALTER SEQUENCE public.bugs_color_palettes_bug_color_palette_id_seq OWNED BY public.bugs_color_palettes.bug_color_palette_id;


--
-- TOC entry 210 (class 1259 OID 16503)
-- Name: bugs_colors; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.bugs_colors (
    bug_color_id integer NOT NULL,
    bug_color_palette_id integer NOT NULL,
    name character varying(50) NOT NULL,
    rgb_code character varying(25) NOT NULL,
    generation_chance integer NOT NULL
);


ALTER TABLE public.bugs_colors OWNER TO bichos;

--
-- TOC entry 209 (class 1259 OID 16501)
-- Name: bugs_colors_bug_color_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--

CREATE SEQUENCE public.bugs_colors_bug_color_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bugs_colors_bug_color_id_seq OWNER TO bichos;

--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 209
-- Name: bugs_colors_bug_color_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--

ALTER SEQUENCE public.bugs_colors_bug_color_id_seq OWNED BY public.bugs_colors.bug_color_id;


--
-- TOC entry 206 (class 1259 OID 16477)
-- Name: bugs_images; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.bugs_images (
    bug_image_id integer NOT NULL,
    bug_pattern_id integer NOT NULL,
    name character varying(50) NOT NULL,
    image_url character varying(120) NOT NULL,
    allow_color boolean NOT NULL,
    "position" integer NOT NULL
);


ALTER TABLE public.bugs_images OWNER TO bichos;

--
-- TOC entry 205 (class 1259 OID 16475)
-- Name: bugs_images_bug_image_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--

CREATE SEQUENCE public.bugs_images_bug_image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bugs_images_bug_image_id_seq OWNER TO bichos;

--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 205
-- Name: bugs_images_bug_image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--

ALTER SEQUENCE public.bugs_images_bug_image_id_seq OWNED BY public.bugs_images.bug_image_id;


--
-- TOC entry 202 (class 1259 OID 16446)
-- Name: bugs_parts; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.bugs_parts (
    bug_part_id integer NOT NULL,
    bug_race_id integer NOT NULL,
    parent_part_id integer,
    name character varying(50) NOT NULL,
    required boolean NOT NULL,
    "position" smallint NOT NULL,
    generation_chance smallint
);


ALTER TABLE public.bugs_parts OWNER TO bichos;

--
-- TOC entry 201 (class 1259 OID 16444)
-- Name: bugs_parts_bug_part_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--
 CREATE SEQUENCE public.bugs_parts_bug_part_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ALTER TABLE public.bugs_parts_bug_part_id_seq OWNER TO bichos;
--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 201
-- Name: bugs_parts_bug_part_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--
 ALTER SEQUENCE public.bugs_parts_bug_part_id_seq OWNED BY public.bugs_parts.bug_part_id;



--
-- TOC entry 204 (class 1259 OID 16464)
-- Name: bugs_patterns; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.bugs_patterns (
    bug_pattern_id integer NOT NULL,
    bug_part_id integer NOT NULL,
    name character varying(50) NOT NULL,
    generation_chance integer NOT NULL
);


ALTER TABLE public.bugs_patterns OWNER TO bichos;

--
-- TOC entry 203 (class 1259 OID 16462)
-- Name: bugs_patterns_bug_pattern_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--

CREATE SEQUENCE public.bugs_patterns_bug_pattern_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bugs_patterns_bug_pattern_id_seq OWNER TO bichos;

--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 203
-- Name: bugs_patterns_bug_pattern_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--

ALTER SEQUENCE public.bugs_patterns_bug_pattern_id_seq OWNED BY public.bugs_patterns.bug_pattern_id;


--
-- TOC entry 200 (class 1259 OID 16438)
-- Name: bugs_races; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.bugs_races (
    bug_race_id integer NOT NULL,
    name character varying(50) NOT NULL,
    generation_chance integer NOT NULL
);


ALTER TABLE public.bugs_races OWNER TO bichos;

--
-- TOC entry 199 (class 1259 OID 16436)
-- Name: bugs_races_bug_race_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--

CREATE SEQUENCE public.bugs_races_bug_race_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bugs_races_bug_race_id_seq OWNER TO bichos;

--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 199
-- Name: bugs_races_bug_race_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--

ALTER SEQUENCE public.bugs_races_bug_race_id_seq OWNED BY public.bugs_races.bug_race_id;


--
-- TOC entry 197 (class 1259 OID 16416)
-- Name: players; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.players (
    player_id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(150) NOT NULL,
    salt character varying(50) NOT NULL,
    email character varying(150) NOT NULL,
    online boolean NOT NULL
);


ALTER TABLE public.players OWNER TO bichos;

--
-- TOC entry 196 (class 1259 OID 16414)
-- Name: players_player_id_seq; Type: SEQUENCE; Schema: public; Owner: bichos
--

CREATE SEQUENCE public.players_player_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.players_player_id_seq OWNER TO bichos;

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 196
-- Name: players_player_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bichos
--

ALTER SEQUENCE public.players_player_id_seq OWNED BY public.players.player_id;


--
-- TOC entry 198 (class 1259 OID 16426)
-- Name: players_sessions; Type: TABLE; Schema: public; Owner: bichos
--

CREATE TABLE public.players_sessions (
    player_id bigint NOT NULL,
    session_id character varying(150) NOT NULL,
    start_date timestamp with time zone NOT NULL
);


ALTER TABLE public.players_sessions OWNER TO bichos;

--
-- TOC entry 2730 (class 2604 OID 16493)
-- Name: bugs_color_palettes bug_color_palette_id; Type: DEFAULT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_color_palettes ALTER COLUMN bug_color_palette_id SET DEFAULT nextval('public.bugs_color_palettes_bug_color_palette_id_seq'::regclass);


--
-- TOC entry 2731 (class 2604 OID 16506)
-- Name: bugs_colors bug_color_id; Type: DEFAULT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_colors ALTER COLUMN bug_color_id SET DEFAULT nextval('public.bugs_colors_bug_color_id_seq'::regclass);


--
-- TOC entry 2729 (class 2604 OID 16480)
-- Name: bugs_images bug_image_id; Type: DEFAULT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_images ALTER COLUMN bug_image_id SET DEFAULT nextval('public.bugs_images_bug_image_id_seq'::regclass);

--
-- TOC entry 2727 (class 2604 OID 16449)
-- Name: bugs_parts bug_part_id; Type: DEFAULT; Schema: public; Owner: bichos
--
 ALTER TABLE ONLY public.bugs_parts ALTER COLUMN bug_part_id SET DEFAULT nextval('public.bugs_parts_bug_part_id_seq'::regclass);

--
-- TOC entry 2728 (class 2604 OID 16467)
-- Name: bugs_patterns bug_pattern_id; Type: DEFAULT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_patterns ALTER COLUMN bug_pattern_id SET DEFAULT nextval('public.bugs_patterns_bug_pattern_id_seq'::regclass);


--
-- TOC entry 2726 (class 2604 OID 16441)
-- Name: bugs_races bug_race_id; Type: DEFAULT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_races ALTER COLUMN bug_race_id SET DEFAULT nextval('public.bugs_races_bug_race_id_seq'::regclass);


--
-- TOC entry 2725 (class 2604 OID 16419)
-- Name: players player_id; Type: DEFAULT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players ALTER COLUMN player_id SET DEFAULT nextval('public.players_player_id_seq'::regclass);


--
-- TOC entry 2749 (class 2606 OID 16495)
-- Name: bugs_color_palettes bugs_color_palettes_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_color_palettes
    ADD CONSTRAINT bugs_color_palettes_pkey PRIMARY KEY (bug_color_palette_id);


--
-- TOC entry 2751 (class 2606 OID 16508)
-- Name: bugs_colors bugs_colors_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_colors
    ADD CONSTRAINT bugs_colors_pkey PRIMARY KEY (bug_color_id);


--
-- TOC entry 2747 (class 2606 OID 16482)
-- Name: bugs_images bugs_images_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_images
    ADD CONSTRAINT bugs_images_pkey PRIMARY KEY (bug_image_id);


--
-- TOC entry 2743 (class 2606 OID 16451)
-- Name: bugs_parts bugs_parts_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_parts
    ADD CONSTRAINT bugs_parts_pkey PRIMARY KEY (bug_part_id);


--
-- TOC entry 2745 (class 2606 OID 16469)
-- Name: bugs_patterns bugs_patterns_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_patterns
    ADD CONSTRAINT bugs_patterns_pkey PRIMARY KEY (bug_pattern_id);


--
-- TOC entry 2741 (class 2606 OID 16443)
-- Name: bugs_races bugs_races_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_races
    ADD CONSTRAINT bugs_races_pkey PRIMARY KEY (bug_race_id);


--
-- TOC entry 2733 (class 2606 OID 16423)
-- Name: players players_email_u; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_email_u UNIQUE (email);


--
-- TOC entry 2735 (class 2606 OID 16421)
-- Name: players players_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_pkey PRIMARY KEY (player_id);


--
-- TOC entry 2739 (class 2606 OID 16430)
-- Name: players_sessions players_sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players_sessions
    ADD CONSTRAINT players_sessions_pkey PRIMARY KEY (player_id, session_id);


--
-- TOC entry 2737 (class 2606 OID 16425)
-- Name: players players_username_u; Type: CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_username_u UNIQUE (username);


--
-- TOC entry 2758 (class 2606 OID 16509)
-- Name: bugs_colors bug_color_palette_id_bugs_colors; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_colors
    ADD CONSTRAINT bug_color_palette_id_bugs_colors FOREIGN KEY (bug_color_palette_id) REFERENCES public.bugs_color_palettes(bug_color_palette_id) ON DELETE CASCADE;


--
-- TOC entry 2755 (class 2606 OID 16470)
-- Name: bugs_patterns bug_part_id_bugs_patterns; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_patterns
    ADD CONSTRAINT bug_part_id_bugs_patterns FOREIGN KEY (bug_part_id) REFERENCES public.bugs_parts(bug_part_id) ON DELETE CASCADE;


--
-- TOC entry 2756 (class 2606 OID 16483)
-- Name: bugs_images bug_pattern_id_bugs_images; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_images
    ADD CONSTRAINT bug_pattern_id_bugs_images FOREIGN KEY (bug_pattern_id) REFERENCES public.bugs_patterns(bug_pattern_id) ON DELETE CASCADE;


--
-- TOC entry 2757 (class 2606 OID 16496)
-- Name: bugs_color_palettes bug_race_id_bugs_color_palettes; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_color_palettes
    ADD CONSTRAINT bug_race_id_bugs_color_palettes FOREIGN KEY (bug_race_id) REFERENCES public.bugs_races(bug_race_id) ON DELETE CASCADE;


--
-- TOC entry 2753 (class 2606 OID 16452)
-- Name: bugs_parts bug_race_id_bugs_parts; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_parts
    ADD CONSTRAINT bug_race_id_bugs_parts FOREIGN KEY (bug_race_id) REFERENCES public.bugs_races(bug_race_id) ON DELETE CASCADE;


--
-- TOC entry 2754 (class 2606 OID 16457)
-- Name: bugs_parts parent_part_id_bugs_parts; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_parts
    ADD CONSTRAINT parent_part_id_bugs_parts FOREIGN KEY (parent_part_id) REFERENCES public.bugs_parts(bug_part_id) ON DELETE CASCADE;


--
-- TOC entry 2752 (class 2606 OID 16431)
-- Name: players_sessions players_sessions_player_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players_sessions
    ADD CONSTRAINT players_sessions_player_id_fk FOREIGN KEY (player_id) REFERENCES public.players(player_id) ON DELETE CASCADE;


-- Completed on 2018-11-15 17:33:13

--
-- PostgreSQL database dump complete
--

