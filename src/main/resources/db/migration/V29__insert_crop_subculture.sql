INSERT INTO crop_subculture(id, crop_id, name_ro, name_ru) VALUES
(1, 1, 'Grâu de vară', 'Яровая пшеница'),
(2, 1, 'Grâu de iarnă', 'Озимая пшеница'),
(3, 2, 'Subcultură de Secară', 'Субкультура Рожь'),
(4, 3, 'Subcultură de Triticale', 'Субкультура Тритикале'),
(5, 4, 'Subcultură de Orz', 'Субкультура Ячмень'),
(6, 5, 'Subcultură de Ovăz', 'Субкультура Овес'),
(7, 6, 'Subcultură de Porumb', 'Субкультура Кукуруза'),
(8, 7, 'Subcultură de Sorg', 'Субкультура Сорг'),
(9, 8, 'Subcultură de Mei', 'Субкультура Просо'),
(10, 9, 'Subcultură de Orez', 'Субкультура Рис'),
(11, 10, 'Subcultură de Hrișca', 'Субкультура Гречиха'),
(12, 11, 'Subcultură de Floarea soarelui', 'Субкультура Подсолнечник'),
(13, 12, 'Subcultură de Rapiță', 'Субкультура Рапс'),
(14, 13, 'Subcultură de Sfeclă de zahăr', 'Сахарная свекла'),
(15, 14, 'Subcultură de In', 'Субкультура Лён'),
(16, 15, 'Subcultură de Cânepă', 'Субкультура Конопля'),
(17, 16, 'Subcultură de Tutun', 'Субкультура Табак'),
(18, 17, 'Subcultură de Hamei', 'Субкультура Хмель'),
(19, 18, 'Subcultură de Soia', 'Субкультура Соя');

SELECT setval('crop_subculture_id_seq', (SELECT MAX(id) FROM crop_subculture));
