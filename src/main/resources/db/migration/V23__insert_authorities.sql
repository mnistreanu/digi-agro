INSERT INTO authority (id, name) values(1, 'ROLE_SUPER_ADMIN');
INSERT INTO authority (id, name) values(2, 'ROLE_ADMIN');
INSERT INTO authority (id, name) values(3, 'ROLE_USER');

SELECT setval('authority_id_seq', (SELECT MAX(id) FROM authority));
