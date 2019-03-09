INSERT INTO tenant (id, name, country_id, fiscal_code, description, county_id, city_id, address, phones) VALUES
(1, 'Gospodăria Ţărănească Orhei', 'md', '9842367810452', 'Gospodăria Ţărănească Orhei este super-puper', 'OR', null, 'str.Solidaritatii 14', '0-232-12345 0-232-12346'),
(2, 'InterAgro Nisporeni', 'md', '8239045184523', 'InterAgro Nisporeni este super-puper', 'NS', null, 'str.Vladimir Ilici Lenin 1', '0-111-12345 0-111-12346');

SELECT setval('tenant_id_seq', (SELECT MAX(id) FROM tenant));