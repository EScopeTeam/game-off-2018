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
INSERT INTO bugs_parts (bug_race_id, parent_part_id, name, required, "position", generation_chance) VALUES (1, NULL, 'Head', false, 75, 40);


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
INSERT INTO bugs_parts (bug_race_id, name, required, position) VALUES (1, 'Arms', true, 60);
INSERT INTO bugs_parts (bug_race_id, name, required, position) VALUES (1, 'Eyes', true, 100);

INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-1', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-2', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-3', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-4', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-5', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-6', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-7', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-8', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-9', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-10', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-11', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-12', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-13', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-14', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-15', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-16', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-17', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-18', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-19', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-20', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-21', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-22', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-23', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-24', 4);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (4, 'ARMS-25', 4);

INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (7, 'img1', 'ARMS-1-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (8, 'img1', 'ARMS-2-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (9, 'img1', 'ARMS-3-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (10, 'img1', 'ARMS-4-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (11, 'img1', 'ARMS-5-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (12, 'img1', 'ARMS-6-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (13, 'img1', 'ARMS-7-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (14, 'img1', 'ARMS-8-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (15, 'img1', 'ARMS-9-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (15, 'img2', 'ARMS-9-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (16, 'img1', 'ARMS-10-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (17, 'img1', 'ARMS-11-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (18, 'img1', 'ARMS-12-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (18, 'img2', 'ARMS-12-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (19, 'img1', 'ARMS-13-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (20, 'img1', 'ARMS-14-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (20, 'img2', 'ARMS-14-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (21, 'img1', 'ARMS-15-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (22, 'img1', 'ARMS-16-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (22, 'img2', 'ARMS-16-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (22, 'img3', 'ARMS-16-3.png', true, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (23, 'img1', 'ARMS-17-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (24, 'img1', 'ARMS-18-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (25, 'img1', 'ARMS-19-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (26, 'img1', 'ARMS-20-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (26, 'img2', 'ARMS-20-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (27, 'img1', 'ARMS-21-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (28, 'img1', 'ARMS-22-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (29, 'img1', 'ARMS-23-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (30, 'img1', 'ARMS-24-1.png', true, 0);

INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-1', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-2', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-3', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-4', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-5', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-6', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-7', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-8', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-9', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-10', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-11', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-12', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-13', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-14', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-15', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-16', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-17', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-18', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-19', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-20', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-21', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-22', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-23', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-24', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-25', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-26', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-27', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-28', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-29', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-30', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-31', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-32', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-33', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-34', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-35', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-36', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-37', 3);
INSERT INTO bugs_patterns (bug_part_id, name, generation_chance) VALUES (5, 'EYES-38', 3);

INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (32, 'img1', 'EYES-1-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (32, 'img2', 'EYES-1-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (33, 'img1', 'EYES-2-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (33, 'img2', 'EYES-2-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (34, 'img1', 'EYES-3-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (34, 'img2', 'EYES-3-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (35, 'img1', 'EYES-4-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (35, 'img2', 'EYES-4-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (36, 'img1', 'EYES-5-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (37, 'img1', 'EYES-6-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (38, 'img1', 'EYES-7-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (39, 'img1', 'EYES-8-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (39, 'img2', 'EYES-8-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (40, 'img1', 'EYES-9-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (40, 'img2', 'EYES-9-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (41, 'img1', 'EYES-10-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (41, 'img2', 'EYES-10-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (41, 'img3', 'EYES-10-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (42, 'img1', 'EYES-11-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (42, 'img2', 'EYES-11-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (42, 'img3', 'EYES-11-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (43, 'img1', 'EYES-12-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (43, 'img2', 'EYES-12-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (43, 'img3', 'EYES-12-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (44, 'img1', 'EYES-13-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (44, 'img2', 'EYES-13-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (45, 'img1', 'EYES-14-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (45, 'img2', 'EYES-14-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (46, 'img1', 'EYES-15-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (46, 'img2', 'EYES-15-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (46, 'img3', 'EYES-15-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (47, 'img1', 'EYES-16-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (47, 'img2', 'EYES-16-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (48, 'img1', 'EYES-17-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (49, 'img1', 'EYES-18-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (49, 'img2', 'EYES-18-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (50, 'img1', 'EYES-19-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (51, 'img1', 'EYES-20-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (51, 'img2', 'EYES-20-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (51, 'img3', 'EYES-20-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (51, 'img4', 'EYES-20-4.png', false, 3);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (52, 'img1', 'EYES-21-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (52, 'img2', 'EYES-21-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (53, 'img1', 'EYES-22-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (53, 'img2', 'EYES-22-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (54, 'img1', 'EYES-23-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (54, 'img2', 'EYES-23-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (55, 'img1', 'EYES-24-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (55, 'img2', 'EYES-24-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (55, 'img3', 'EYES-24-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (56, 'img1', 'EYES-25-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (56, 'img2', 'EYES-25-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (56, 'img3', 'EYES-25-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (57, 'img1', 'EYES-26-1.png', true, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (57, 'img2', 'EYES-26-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (58, 'img1', 'EYES-27-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (59, 'img1', 'EYES-28-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (59, 'img2', 'EYES-28-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (60, 'img1', 'EYES-29-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (60, 'img2', 'EYES-29-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (61, 'img1', 'EYES-30-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (61, 'img2', 'EYES-30-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (61, 'img3', 'EYES-30-3.png', false, 2);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (62, 'img1', 'EYES-31-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (62, 'img2', 'EYES-31-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (63, 'img1', 'EYES-32-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (64, 'img1', 'EYES-33-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (65, 'img1', 'EYES-34-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (66, 'img1', 'EYES-35-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (66, 'img2', 'EYES-35-2.png', false, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (67, 'img1', 'EYES-36-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (68, 'img1', 'EYES-37-1.png', false, 0);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (68, 'img2', 'EYES-37-2.png', true, 1);
INSERT INTO bugs_images (bug_pattern_id, name, image_url, allow_color, position) VALUES (69, 'img1', 'EYES-38-1.png', false, 0);