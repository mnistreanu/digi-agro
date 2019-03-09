-- password: test123
INSERT INTO user_account (id, username, password) values
(1, 'super-admin', '$2a$10$J.79O6b/Zo9rPAivWonK1ufA3yB.dsH1UJMVZRrarOrBiDzFRT9NO');

INSERT INTO user_account (id, username, password, first_name, last_name, language) values
(2, 'mihai.gorgos', '$2a$10$J.79O6b/Zo9rPAivWonK1ufA3yB.dsH1UJMVZRrarOrBiDzFRT9NO', 'Mihai', 'Gorgos', 'ro'),
(3, 'maxim.nistreanu', '$2a$10$J.79O6b/Zo9rPAivWonK1ufA3yB.dsH1UJMVZRrarOrBiDzFRT9NO', 'Maxim', 'Nistreanu', 'ru'),
(4, 'sergiu.gangan', '$2a$10$J.79O6b/Zo9rPAivWonK1ufA3yB.dsH1UJMVZRrarOrBiDzFRT9NO', 'Sergiu', 'Gangan', 'ru'),
(5, 'tenant1-admin', '$2a$10$J.79O6b/Zo9rPAivWonK1ufA3yB.dsH1UJMVZRrarOrBiDzFRT9NO', 'Tenant 1', 'Admin', 'ro');


INSERT INTO user_authority (user_id, authority_id) VALUES
(1, 1),
(2, 3),
(3, 3),
(4, 3),
(5, 2);

INSERT INTO tenant_user (user_id, tenant_id) VALUES
(2, 1),
(3, 1),
(3, 2),
(4, 2),
(5, 1);

SELECT setval('user_account_id_seq', (SELECT MAX(id) FROM user_account));
