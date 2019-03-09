INSERT INTO pesticide(id, pesticide_type, name_ro, description_ro, pests_ro, active_substance, toxicity_group) VALUES
(1, 'HERBICIDE', 'Amino 600 SL', 'Producator: Machteshim Agan Agro Poland SA', 'Buruieni dicotiledonate sensibile anuale (stir, ridiche salbatica, spanac salbatic) si perene (palamida)', 'acid 2,4 D', 3),
(2, 'FUNGICIDE', 'Antracol 70 WP', 'Producator: BAYER CROPSCIENCE AG.', 'mana cartofului, alternarioza, mana cucurbitaceelor, mana cepei, mana hameiului, rapanul marului, rapanul parului, patarea cafenie a tomatelor', 'propineb', 4),
(3, 'FUNGICIDE', 'Armetil cobre', 'Producator: IQV, SPANIA', 'Mana cartofului, Mana tomatelor, Mana vitei de vie', 'Metalaxil, cupru (din oxiclorura de cupru)', 4),
(4, 'HERBICIDE', 'Dacnisul', 'Producator: STRAND GROUP HOLDINGS LTD', 'costrei din rizomi, Buruieni monocotiledonate anuale', 'nicosulfuron', 4),
(5, 'INSECTICIDE', 'Dalila', 'Producator: BIESTERFELD SIEMSGLUSS INTERNATIONAL GmbH, GERMANIA, SC AGROINTERNATIONAL SRL BUCURESTI, ROMANIA', 'gandacul din Colorado, afide si viermii sarma, gargarita frunzelor, afidele cerealelor( in vederea prevenirii aparitiei fenomenului de ingalbenire, piticire si aspermie a graului', 'imidacloprid', 3),
(6, 'FUNGICIDE', 'Maccani', 'Producator: BASF SE, GERMANIA', 'Rapanul marului, Fainarea marului', 'ditianon, piraclostrobin', 3),
(7, 'INSECTICIDE', 'Nuprid Oil SC', 'Producator: ALCHIMEX S.A., Romania+NUFARM GmbH &amp, Co. KG, Austria', 'Paduchele de San Jose - larve hibernante, acarianul rosu al pomilor - oua de iarna, afide, paduchele testos', 'imidacloprid', 3);

UPDATE pesticide SET name_ru=name_ro, description_ru=description_ro, pests_ru=pests_ro WHERE id > 0;

SELECT setval('pesticide_id_seq', (SELECT MAX(id) FROM pesticide));

