INSERT INTO employee(id, tenant_id, title, first_name, last_name) VALUES
(1, 1, 'Șofer', 'Alexandr', 'Ceban'),
(2, 1, 'Șofer', 'Vladimir', 'Ursu'),
(3, 1, 'Șofer', 'Petru', 'Birca'),
(4, 1, 'Șofer', 'Iurii', 'Popov'),
(5, 2, 'Șofer', 'Pavel', 'Croitor'),
(6, 2, 'Șofer', 'Ivan', 'Lupu'),
(7, 2, 'Șofer', 'David', 'Russu'),
(8, 2, 'Șofer', 'Boris', 'Sirbu');

SELECT setval('employee_id_seq', (SELECT MAX(id) FROM employee));