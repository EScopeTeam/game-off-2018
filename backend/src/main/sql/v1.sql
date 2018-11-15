--
-- PostgreSQL database dump
--

-- Dumped from database version 11.0
-- Dumped by pg_dump version 11.0

-- Started on 2018-11-15 17:33:12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

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
-- TOC entry 2892 (class 0 OID 16490)
-- Dependencies: 208
-- Data for Name: bugs_color_palettes; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.bugs_color_palettes (bug_color_palette_id, bug_race_id, name, generation_chance) FROM stdin;
2	2	Test palette	100
\.


--
-- TOC entry 2894 (class 0 OID 16503)
-- Dependencies: 210
-- Data for Name: bugs_colors; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.bugs_colors (bug_color_id, bug_color_palette_id, name, rgb_code, generation_chance) FROM stdin;
1	2	red	#DF3B56	20
2	2	green1	#60D394	20
3	2	green2	#AAF683	20
4	2	orange1	#FFD97D	20
5	2	orange2	#FF9B85	20
\.


--
-- TOC entry 2890 (class 0 OID 16477)
-- Dependencies: 206
-- Data for Name: bugs_images; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") FROM stdin;
1	1	img1		t	0
2	2	img1		t	0
3	3	img1		t	0
4	4	img1		t	0
5	5	img1		t	0
6	6	img1		t	0
7	7	img1		t	0
8	8	img1		t	0
9	9	img1		t	0
10	10	img1		t	0
11	11	img1		t	0
12	12	img1		t	0
13	13	img1		t	0
14	14	img1		t	0
15	15	img1		t	0
16	16	img1		t	0
17	17	img1		t	0
18	18	img1		t	0
19	19	img1		t	0
20	20	img1		t	0
21	21	img1		t	0
22	22	img1		t	0
23	23	img1		t	0
24	24	img1		t	0
25	25	img1		t	0
26	26	img1		t	0
27	27	img1		t	0
28	28	img1		t	0
29	29	img1		t	0
30	30	img1		t	0
31	31	img1		t	0
32	32	img1		t	0
33	33	img1		t	0
34	34	img1		t	0
35	35	img1		t	0
36	36	img1		t	0
37	37	img1		t	0
38	38	img1		t	0
39	39	img1		t	0
40	40	img1		t	0
41	41	img1		t	0
42	42	img1		t	0
43	43	img1		t	0
44	44	img1		t	0
45	45	img1		t	0
46	46	img1		t	0
47	47	img1		t	0
48	48	img1		t	0
49	110	img1		t	0
50	6	img1		t	0
51	6	img1		f	0
52	6	img1		t	0
53	6	img2		t	1
54	6	img1		f	0
55	6	img1		t	0
56	6	img1		t	0
57	6	img2		f	1
58	6	img1		f	0
59	6	img1		t	0
60	6	img2		f	1
61	6	img1		f	0
62	6	img1		f	0
63	6	img1		f	0
64	7	img1		f	0
65	7	img2		t	1
66	7	img3		f	2
67	7	img1		t	0
68	7	img2		f	1
69	7	img1		t	0
70	7	img1		f	0
71	7	img1		f	0
72	7	img1		f	0
73	7	img1		f	0
74	7	img1		f	0
75	7	img1		f	0
76	7	img1		t	0
77	7	img1		f	0
78	7	img1		t	0
79	7	img1		f	0
80	7	img1		t	0
81	9	img1		f	0
82	9	img2		f	1
83	9	img1		f	0
84	9	img2		f	1
85	9	img1		f	0
86	9	img2		f	1
87	9	img1		f	0
88	9	img2		f	1
89	9	img1		f	0
90	9	img1		f	0
91	9	img1		f	0
92	9	img1		f	0
93	9	img2		f	1
94	9	img1		f	0
95	9	img2		f	1
96	9	img1		f	0
97	9	img2		f	1
98	9	img3		f	2
99	9	img1		f	0
100	9	img2		f	1
101	9	img3		f	2
102	9	img1		f	0
103	9	img2		f	1
104	9	img3		f	2
105	9	img1		f	0
106	9	img2		f	1
107	9	img1		f	0
108	9	img2		f	1
109	9	img1		t	0
110	9	img2		f	1
111	9	img3		f	2
112	9	img1		f	0
113	9	img2		f	1
114	9	img1		f	0
115	9	img1		f	0
116	9	img2		f	1
117	9	img1		f	0
118	9	img1		t	0
119	9	img2		t	1
120	9	img3		f	2
121	9	img4		f	3
122	9	img1		f	0
123	9	img2		f	1
124	9	img1		f	0
125	9	img2		f	1
126	9	img1		f	0
127	9	img2		f	1
128	9	img1		f	0
129	9	img2		f	1
130	9	img3		f	2
131	9	img1		f	0
132	9	img2		f	1
133	9	img3		f	2
134	9	img1		t	0
135	9	img2		f	1
136	9	img1		f	0
137	9	img1		f	0
138	9	img2		f	1
139	9	img1		f	0
140	9	img2		f	1
141	9	img1		f	0
142	9	img2		f	1
143	9	img3		f	2
144	9	img1		f	0
145	9	img2		t	1
146	9	img1		f	0
147	9	img1		f	0
148	9	img1		f	0
149	9	img1		f	0
150	9	img2		f	1
151	9	img1		f	0
152	9	img1		f	0
153	9	img2		t	1
154	9	img3		f	2
\.


--
-- TOC entry 2886 (class 0 OID 16446)
-- Dependencies: 202
-- Data for Name: bugs_parts; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.bugs_parts (bug_part_id, bug_race_id, parent_part_id, name, required, "position", generation_chance) FROM stdin;
3	2	\N	legs	t	30	\N
4	2	\N	arms	t	40	\N
5	2	\N	wings	f	5	10
6	2	\N	horns	f	25	20
1	2	\N	body	t	10	\N
2	2	\N	head	t	20	\N
8	2	\N	tail	f	4	10
7	2	\N	nose	f	30	\N
9	2	\N	eyes	t	28	\N
\.


--
-- TOC entry 2888 (class 0 OID 16464)
-- Dependencies: 204
-- Data for Name: bugs_patterns; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) FROM stdin;
1	1	body1	80
2	1	body2	20
3	2	head1	10
4	2	head2	35
5	2	head3	35
6	2	head4	20
7	3	leg1	10
8	3	leg2	10
9	3	leg3	10
10	3	leg4	10
11	3	leg5	10
12	3	leg6	10
13	3	leg7	10
14	3	leg8	10
15	3	leg9	10
16	4	arms1	4
17	4	arms2	4
18	4	arms3	4
19	4	arms4	4
20	4	arms5	4
21	4	arms6	4
22	4	arms7	4
23	4	arms8	4
24	4	arms9	4
25	4	arms10	4
26	4	arms11	4
27	4	arms12	4
28	4	arms13	4
29	4	arms14	4
30	4	arms15	4
31	4	arms16	4
32	4	arms17	4
33	4	arms18	4
34	4	arms19	4
35	4	arms20	4
36	4	arms21	4
37	4	arms22	4
38	4	arms23	4
39	4	arms24	4
40	5	wings1	14
41	5	wings2	14
42	5	wings3	14
43	5	wings4	14
44	5	wings5	14
45	5	wings6	14
46	5	wings7	14
47	8	tail1	70
48	8	tail2	30
49	6	horn2	10
50	6	horn3	10
51	6	horn4	10
52	6	horn5	10
53	6	horn6	10
54	6	horn7	10
55	6	horn8	10
56	6	horn9	10
57	6	horn10	10
58	6	horn11	10
59	7	nose1	7
60	7	nose2	7
61	7	nose3	7
62	7	nose4	7
63	7	nose5	7
64	7	nose6	7
65	7	nose7	7
66	7	nose8	7
67	7	nose9	7
68	7	nose10	7
69	7	nose11	7
70	7	nose12	7
71	7	nose13	7
72	7	nose14	7
73	9	eyes1	3
74	9	eyes2	3
75	9	eyes3	3
76	9	eyes4	3
77	9	eyes5	3
78	9	eyes6	3
79	9	eyes7	3
80	9	eyes8	3
81	9	eyes9	3
82	9	eyes10	3
83	9	eyes11	3
84	9	eyes12	3
85	9	eyes13	3
86	9	eyes14	3
87	9	eyes15	3
88	9	eyes16	3
89	9	eyes17	3
90	9	eyes18	3
91	9	eyes19	3
92	9	eyes20	3
93	9	eyes21	3
94	9	eyes22	3
95	9	eyes23	3
96	9	eyes24	3
97	9	eyes25	3
98	9	eyes26	3
99	9	eyes27	3
100	9	eyes28	3
101	9	eyes29	3
102	9	eyes30	3
103	9	eyes31	3
104	9	eyes32	3
105	9	eyes33	3
106	9	eyes34	3
107	9	eyes35	3
108	9	eyes36	3
109	9	eyes37	3
110	6	horn1	10
\.


--
-- TOC entry 2884 (class 0 OID 16438)
-- Dependencies: 200
-- Data for Name: bugs_races; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.bugs_races (bug_race_id, name, generation_chance) FROM stdin;
2	Monster	100
\.


--
-- TOC entry 2881 (class 0 OID 16416)
-- Dependencies: 197
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.players (player_id, username, password, salt, email, online) FROM stdin;
2	test	5CDD8ED1E7BDDC562BA7186AC680FB89F23CE6D7A4B90E667F259CCD87ECF7742927BDF42F945E8709E7524EE5BC544115E2A7761EEBA2E3E152E35F5405D32D	689229A90E4AD345F492D30CD49D87853E6FBC0AA5C9652223	test@test.com	t
\.


--
-- TOC entry 2882 (class 0 OID 16426)
-- Dependencies: 198
-- Data for Name: players_sessions; Type: TABLE DATA; Schema: public; Owner: bichos
--

COPY public.players_sessions (player_id, session_id, start_date) FROM stdin;
2	644091cc-632d-4985-b75f-77e996d91e1e	2018-11-12 13:24:28.482+01
2	64f5a728-8af4-482c-947e-37d9b2487500	2018-11-12 14:23:00.387+01
2	254ad541-8977-4de6-9112-787b0753734e	2018-11-12 14:24:05.4+01
2	68f9edce-e3e2-45ef-9817-07faad4d6c97	2018-11-12 14:25:28.451+01
2	d683f62d-d492-484c-abb6-e3058f0018e3	2018-11-12 15:18:43.249+01
2	6fa8775a-4e15-47f2-92bb-ebc905fb4725	2018-11-12 15:19:08.443+01
2	4ba9a9fc-942e-4770-a420-4d18c6cd10a5	2018-11-12 15:19:15.745+01
2	acf96fb7-e24b-4abd-ae21-dac91da4e1ea	2018-11-12 15:32:38.14+01
2	1b521c3d-5401-46a6-8620-89d3740b160f	2018-11-12 15:33:09.863+01
2	b7388f43-8919-4675-a614-2edf6bc60b12	2018-11-12 15:35:22.174+01
2	0c987ff2-013d-45b1-9b4b-1bd82bb6aa4e	2018-11-12 15:35:53.669+01
2	288238e8-f6b1-49e4-b4b2-cf5788bcab7a	2018-11-12 15:38:34.882+01
2	9782a355-4013-4c07-91e5-62c4b597c20f	2018-11-12 15:39:06.532+01
2	1667237f-9943-4832-ba20-aa10c3ffdde9	2018-11-12 15:40:30.001+01
2	1e9c9070-1997-4889-9b29-f160bba5eda9	2018-11-12 15:44:26.545+01
2	71179ec8-b778-4404-a21d-ceba57cf2c15	2018-11-12 16:14:54.842+01
2	2d84ad0a-8144-41fc-8e5a-d187d5cdcd54	2018-11-12 16:18:55.75+01
\.


--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 207
-- Name: bugs_color_palettes_bug_color_palette_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.bugs_color_palettes_bug_color_palette_id_seq', 2, true);


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 209
-- Name: bugs_colors_bug_color_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.bugs_colors_bug_color_id_seq', 5, true);


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 205
-- Name: bugs_images_bug_image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.bugs_images_bug_image_id_seq', 154, true);


--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 201
-- Name: bugs_parts_bug_part_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.bugs_parts_bug_part_id_seq', 9, true);


--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 203
-- Name: bugs_patterns_bug_pattern_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.bugs_patterns_bug_pattern_id_seq', 110, true);


--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 199
-- Name: bugs_races_bug_race_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.bugs_races_bug_race_id_seq', 2, true);


--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 196
-- Name: players_player_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('public.players_player_id_seq', 2, true);


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
    ADD CONSTRAINT bug_color_palette_id_bugs_colors FOREIGN KEY (bug_color_palette_id) REFERENCES public.bugs_color_palettes(bug_color_palette_id);


--
-- TOC entry 2755 (class 2606 OID 16470)
-- Name: bugs_patterns bug_part_id_bugs_patterns; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_patterns
    ADD CONSTRAINT bug_part_id_bugs_patterns FOREIGN KEY (bug_part_id) REFERENCES public.bugs_parts(bug_part_id);


--
-- TOC entry 2756 (class 2606 OID 16483)
-- Name: bugs_images bug_pattern_id_bugs_images; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_images
    ADD CONSTRAINT bug_pattern_id_bugs_images FOREIGN KEY (bug_pattern_id) REFERENCES public.bugs_patterns(bug_pattern_id);


--
-- TOC entry 2757 (class 2606 OID 16496)
-- Name: bugs_color_palettes bug_race_id_bugs_color_palettes; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_color_palettes
    ADD CONSTRAINT bug_race_id_bugs_color_palettes FOREIGN KEY (bug_race_id) REFERENCES public.bugs_races(bug_race_id);


--
-- TOC entry 2753 (class 2606 OID 16452)
-- Name: bugs_parts bug_race_id_bugs_parts; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_parts
    ADD CONSTRAINT bug_race_id_bugs_parts FOREIGN KEY (bug_race_id) REFERENCES public.bugs_races(bug_race_id);


--
-- TOC entry 2754 (class 2606 OID 16457)
-- Name: bugs_parts parent_part_id_bugs_parts; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.bugs_parts
    ADD CONSTRAINT parent_part_id_bugs_parts FOREIGN KEY (parent_part_id) REFERENCES public.bugs_parts(bug_part_id);


--
-- TOC entry 2752 (class 2606 OID 16431)
-- Name: players_sessions players_sessions_player_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: bichos
--

ALTER TABLE ONLY public.players_sessions
    ADD CONSTRAINT players_sessions_player_id_fk FOREIGN KEY (player_id) REFERENCES public.players(player_id);


-- Completed on 2018-11-15 17:33:13

--
-- PostgreSQL database dump complete
--

