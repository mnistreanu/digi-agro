-- cropSeason 1, tenant 1
INSERT INTO expense(id, tenant_id, crop_season_id, category_id, sub_category_id, date, cost) VALUES
--  combustibil 
(1, 1, 1, 12, null, '2019-02-01', 400),
-- Lucrări agricole
(2, 1, 1, 14, null, '2019-02-01', 1000),
-- Însămânțare
(3, 1, 1, 15, null, '2019-04-01', 400),
-- Irigare
(4, 1, 1, 17, null, '2019-04-20', 550),
-- Altele
(5, 1, 1, 20, null, '2019-05-03', 558),
-- Tehnică și echipamente
(6, 1, 1, 11, null, '2019-05-15', 430),
-- Motorină
(7, 1, 1, 12, 21, '2019-05-16', 300),
-- Ulei
(8, 1, 1, 12, 22, '2019-05-16', 100),
-- Solidol
(9, 1, 1, 12, 23, '2019-05-16', 80),
-- Negrol
(10, 1, 1, 12, 24, '2019-05-16', 70);


-- cropSeason 2, tenant 1
INSERT INTO expense(id, tenant_id, crop_season_id, category_id, sub_category_id, date, cost) VALUES
--  combustibil 
(11, 1, 2, 12, null, '2019-03-01', 200),
-- Lucrări agricole
(12, 1, 2, 14, null, '2019-03-01', 800),
-- Irigare
(13, 1, 2, 17, null, '2019-05-01', 300),
-- Operaționale
(14, 1, 2, 19, null, '2019-06-01', 100),
-- Altele
(15, 1, 2, 20, null, '2019-06-03', 700),
-- Tehnică și echipamente
(16, 1, 2, 11, null, '2019-06-15', 300),
-- Motorină
(17, 1, 2, 12, 21, '2019-06-16', 100),
-- Ulei
(18, 1, 2, 12, 22, '2019-06-16', 50),
-- Solidol
(19, 1, 2, 12, 23, '2019-06-16', 50),
-- Negrol
(20, 1, 2, 12, 24, '2019-06-20', 80);

SELECT setval('expense_id_seq', (SELECT MAX(id) FROM expense));