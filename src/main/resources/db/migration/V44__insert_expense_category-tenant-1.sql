INSERT INTO expense_category(id, tenant_id, name, description) VALUES
(11, 1, 'Tehnică și echipamente', 'Cheltuieli pentru repararea mașinilor agricole'),
(12, 1, 'Combustibil', 'Cheltuieli pentru combustibil'),
(13, 1, 'Însămânțare', 'Cheltuieli pentru însămânțare'),
(14, 1, 'Lucrări agricole', 'Cheltuieli pentru lucrări agricole'),
(15, 1, 'Îngrășăminte', 'Cheltuieli pentru îngrășăminte'),
(16, 1, 'Pesticide', 'Cheltuieli pentru pesticide'),
(17, 1, 'Irigare', 'Cheltuieli pentru irigare'),
(18, 1, 'Închiriere', 'Cheltuieli pentru închiriere'),
(19, 1, 'Operaționale', 'Cheltuieli operaționale'),
(20, 1, 'Altele', 'Alte cheltuieli');

INSERT INTO expense_category(id, tenant_id, parent_id, name) VALUES
(21, 1, 12, 'Motorină'),
(22, 1, 12, 'Ulei'),
(23, 1, 12, 'Solidol'),
(24, 1, 12, 'Negrol'),

(25, 1, 11, 'Tractoare'),
(26, 1, 11, 'Combine'),
(27, 1, 11, 'Altă tehnică mecanizată'),
(28, 1, 11, 'Diverse echipamente');

SELECT setval('expense_category_id_seq', (SELECT MAX(id) FROM expense_category));