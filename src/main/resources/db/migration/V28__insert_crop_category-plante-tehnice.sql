INSERT INTO crop_category (id, name_ro, name_ru) VALUES
(2,'Plance tehnice','Технические растения');

INSERT INTO crop (id, crop_category_id, name_ro, name_ru, icon) VALUES
(11,2, 'Floarea soarelui','Подсолнечник', 'sunflower.png'),
(12,2, 'Rapiță','Рапс', 'colza.png'),
(13,2, 'Sfeclă de zahăr','Сахарная свекла', 'sugarbeet.png'),
(14,2, 'In','Лён', 'linen.png'),
(15,2, 'Cânepă','Конопля', 'cannabis.png'),
(16,2, 'Tutun','Табак', 'tobacco.png'),
(17,2, 'Hamei','Хмель', 'hop.png'),
(18,2, 'Soia','Соя', 'soy.png');


SELECT setval('crop_category_id_seq', (SELECT MAX(id) FROM crop_category));
SELECT setval('crop_id_seq', (SELECT MAX(id) FROM crop));
