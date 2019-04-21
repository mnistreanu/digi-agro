INSERT INTO event_type(id, tenant_id, parent_id, name) VALUES
(1, 1, null, 'Weather'),
(2, 1, 1, 'Rain'),
(3, 1, 1, 'Hail'),
(4, 1, 1, 'Frost'),
(5, 1, null, 'Other'),
(6, 1, 5, 'Meeting'),
(7, 1, 5, 'Training');

SELECT setval('event_type_id_seq', (SELECT MAX(id) FROM event_type));