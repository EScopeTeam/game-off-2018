--
-- TOC entry 2490 (class 0 OID 16646)
-- Dependencies: 205
-- Data for Name: bugs_races; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO bugs_races (bug_race_id, name, generation_chance) VALUES (1, 'Monster', 100);


--
-- TOC entry 2481 (class 0 OID 16623)
-- Dependencies: 196
-- Data for Name: bugs_color_palettes; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO bugs_color_palettes (bug_color_palette_id, bug_race_id, name, generation_chance) VALUES (1, 1, 'Test palette', 100);


--
-- TOC entry 2483 (class 0 OID 16628)
-- Dependencies: 198
-- Data for Name: bugs_colors; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO bugs_colors (bug_color_id, bug_color_palette_id, name, rgb_code, generation_chance) VALUES (6, 1, 'red', '#DF3B56', 20);
INSERT INTO bugs_colors (bug_color_id, bug_color_palette_id, name, rgb_code, generation_chance) VALUES (7, 1, 'green1', '#60D394', 20);
INSERT INTO bugs_colors (bug_color_id, bug_color_palette_id, name, rgb_code, generation_chance) VALUES (8, 1, 'green2', '#AAF683', 20);
INSERT INTO bugs_colors (bug_color_id, bug_color_palette_id, name, rgb_code, generation_chance) VALUES (9, 1, 'orange1', '#FFD97D', 20);
INSERT INTO bugs_colors (bug_color_id, bug_color_palette_id, name, rgb_code, generation_chance) VALUES (10, 1, 'orange2', '#FF9B85', 20);


--
-- TOC entry 2487 (class 0 OID 16638)
-- Dependencies: 202
-- Data for Name: bugs_parts; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO bugs_parts (bug_race_id, parent_part_id, name, required, "position", generation_chance) VALUES (1, NULL, 'Body', true, 50, NULL);
INSERT INTO bugs_parts (bug_race_id, parent_part_id, name, required, "position", generation_chance) VALUES (1, NULL, 'Head', true, 75, NULL);


--
-- TOC entry 2488 (class 0 OID 16641)
-- Dependencies: 203
-- Data for Name: bugs_patterns; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) VALUES (1, 1, 'BODY-1', 30);
INSERT INTO bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) VALUES (2, 1, 'BODY-2', 70);
INSERT INTO bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) VALUES (3, 2, 'HEAD-1', 25);
INSERT INTO bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) VALUES (4, 2, 'HEAD-2', 25);
INSERT INTO bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) VALUES (5, 2, 'HEAD-3', 25);
INSERT INTO bugs_patterns (bug_pattern_id, bug_part_id, name, generation_chance) VALUES (6, 2, 'HEAD-4', 25);


--
-- TOC entry 2485 (class 0 OID 16633)
-- Dependencies: 200
-- Data for Name: bugs_images; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (2, 1, 'img1', 'BODY-1-1.png', false, 0);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (3, 1, 'img2', 'BODY-1-2.png', true, 1);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (4, 2, 'img1', 'BODY-2-1.png', true, 0);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (5, 3, 'img1', 'HEAD-1-1.png', true, 0);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (6, 4, 'img1', 'HEAD-2-1.png', false, 1);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (7, 4, 'img2', 'HEAD-2-2.png', true, 0);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (8, 5, 'img1', 'HEAD-3-1.png', true, 0);
INSERT INTO bugs_images (bug_image_id, bug_pattern_id, name, image_url, allow_color, "position") VALUES (9, 6, 'img1', 'HEAD-4-1.png', true, 0);


--
-- TOC entry 2492 (class 0 OID 16651)
-- Dependencies: 207
-- Data for Name: players; Type: TABLE DATA; Schema: public; Owner: bichos
--

INSERT INTO players (player_id, username, password, salt, email, online) VALUES (2, 'test', '5CDD8ED1E7BDDC562BA7186AC680FB89F23CE6D7A4B90E667F259CCD87ECF7742927BDF42F945E8709E7524EE5BC544115E2A7761EEBA2E3E152E35F5405D32D', '689229A90E4AD345F492D30CD49D87853E6FBC0AA5C9652223', 'test@test.com', true);


--
-- TOC entry 2499 (class 0 OID 0)
-- Dependencies: 197
-- Name: bugs_color_palettes_bug_color_palette_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('bugs_color_palettes_bug_color_palette_id_seq', 1, true);


--
-- TOC entry 2500 (class 0 OID 0)
-- Dependencies: 199
-- Name: bugs_colors_bug_color_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('bugs_colors_bug_color_id_seq', 10, true);


--
-- TOC entry 2501 (class 0 OID 0)
-- Dependencies: 201
-- Name: bugs_images_bug_image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('bugs_images_bug_image_id_seq', 9, true);


--
-- TOC entry 2502 (class 0 OID 0)
-- Dependencies: 204
-- Name: bugs_patterns_bug_pattern_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('bugs_patterns_bug_pattern_id_seq', 6, true);

--
-- TOC entry 2502 (class 0 OID 0)
-- Dependencies: 204
-- Name: bugs_parts_bug_part_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('bugs_parts_bug_part_id_seq', 2, true);


--
-- TOC entry 2503 (class 0 OID 0)
-- Dependencies: 206
-- Name: bugs_races_bug_race_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('bugs_races_bug_race_id_seq', 1, true);


--
-- TOC entry 2504 (class 0 OID 0)
-- Dependencies: 208
-- Name: players_player_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bichos
--

SELECT pg_catalog.setval('players_player_id_seq', 2, true);


-- Completed on 2018-11-20 19:57:48 CET

--
-- PostgreSQL database dump complete
--

