INSERT INTO pest (id, name_ro) VALUES
(1, 'Rapăn'),
(2, 'Monilioze'),
(3, 'Bacterioze'),
(4, 'Gărgărițe'),
(5, 'Gărgărița florilor de măr'),
(6, 'Făinarea'),
(7, 'Păduchii'),
(8, 'Păduchele din San-Hose'),
(9, 'Gândacul păros'),
(10, 'Molii'),
(11, 'Viespe cu ferestrău'),
(12, 'Viermele mărului'),
(13, 'Acarieni'),
(14, 'Bolile dructelor în perioada de păstrare'),
(15, 'Molii minere');

UPDATE pest SET name_ru=name_ro WHERE name_ru IS NULL;

SELECT SETVAL('pest_id_seq', (SELECT MAX(id) FROM pest));

