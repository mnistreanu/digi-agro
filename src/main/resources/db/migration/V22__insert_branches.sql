INSERT INTO branch(id, tenant_id, name, description, phones, county_id, city, address) VALUES
(11, 1, 'Filiala Nord', 'Filiala de la Briceni', '99-88-77-66, 11-22-33-44', 'BR', 'Jlobeni', 'la vale de riu'),
(12, 1, 'Filiala Sud', 'Filiala de la Cahul', '12-34-56-78, 90-78-56-34', 'CH', 'Furduleni', 'Linga Dunare'),
(13, 2, 'Filiala Vest', 'Filiala de la Ungheni', '99-88-77-66, 11-22-33-44', 'UN', 'Bubueci', 'Linga Romania'),
(14, 2, 'Filiala Est', 'Filiala de la Stefan Voda', '12-34-56-78, 90-78-56-34', 'SV', 'Cipusesti', 'Linga Ucraina');

SELECT setval('branch_id_seq', (SELECT MAX(id) FROM branch));