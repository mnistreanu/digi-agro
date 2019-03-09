 INSERT INTO machine(id, tenant_id, brand_id, type, identifier, model, fabrication_country, motor_type) VALUES
(1, 1, 1, 'TRACTOR', 'ANT 863', '5060E', 'USA', 'DIESEL'),
(2, 1, 1, 'TRACTOR', 'CLT 751', '5E', 'USA', 'DIESEL'),
(3, 2, 2, 'TRACTOR', 'UNT 784', 'MTZ-82', 'Belarus', 'DIESEL'),
(4, 2, 2, 'TRACTOR', 'BLT 367', 'MTZ-96', 'Belarus', 'DIESEL');

INSERT INTO machine_work_type(machine_id, work_type_id) VALUES
(1, 1), (1, 2),
(2, 1), (2, 2),
(3, 1), (3, 2),
(4, 1), (4, 2);

INSERT INTO machine_employee(machine_id, employee_id) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 6),
(4, 7), (4, 8);

SELECT setval('machine_id_seq', (SELECT MAX(id) FROM machine));