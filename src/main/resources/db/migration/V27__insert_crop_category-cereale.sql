INSERT INTO crop_category (id, name_ro, name_ru) VALUES
(1,'Cereale','Зерновые');

INSERT INTO crop (id, crop_category_id, name_ro, name_ru, icon) VALUES
(1, 1, 'Grâu','Пшеница', 'wheat.png'),
(2, 1, 'Secară','Рожь', 'rye.png'),
(3, 1, 'Triticale','Тритикале', 'triticale.png'),
(4, 1, 'Orz','Ячмень', 'barley.png'),
(5, 1, 'Ovăz','Овес', 'oat.png'),
(6, 1, 'Porumb','Кукуруза', 'corn.png'),
(7, 1, 'Sorg','Сорг', 'sorghum.png'),
(8, 1, 'Mei','Просо', 'millet.png'),
(9, 1, 'Orez','Рис', 'rice.png'),
(10, 1, 'Hrișca','Гречиха', 'buckwheat.png');


SELECT setval('crop_category_id_seq', (SELECT MAX(id) FROM crop_category));
SELECT setval('crop_id_seq', (SELECT MAX(id) FROM crop));
