INSERT INTO expense_category(id, name, description) VALUES
(1, 'expense-category.machinery-title', 'expense-category.machinery'),
(2, 'expense-category.fuel-title', 'expense-category.fuel'),
(3, 'expense-category.sowing-title', 'expense-category.sowing'),
(4, 'expense-category.works-title', 'expense-category.works'),
(5, 'expense-category.fertilizers-title', 'expense-category.fertilizers'),
(6, 'expense-category.pesticides-title', 'expense-category.pesticides'),
(7, 'expense-category.irrigation-title', 'expense-category.irrigation'),
(8, 'expense-category.renting-title', 'expense-category.renting'),
(9, 'expense-category.operational-title', 'expense-category.operational'),
(10, 'expense-category.others-title', 'expense-category.others');

SELECT setval('expense_category_id_seq', (SELECT MAX(id) FROM expense_category));