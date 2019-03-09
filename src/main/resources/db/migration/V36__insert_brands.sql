 INSERT INTO brand(id, name) VALUES
(1, 'John Deere'),
(2, 'Belarus');

SELECT setval('brand_id_seq', (SELECT MAX(id) FROM brand));