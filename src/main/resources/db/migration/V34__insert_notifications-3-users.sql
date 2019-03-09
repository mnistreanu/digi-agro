INSERT INTO notification_subscription (user_id, notification_type_id) VALUES
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),

(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),

(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(4, 6),
(4, 7);

INSERT INTO notification (notification_type_id, created_at, date_to, message, user_id) VALUES 
(1, NOW(), NOW() + interval '24 hours', 'Atenție, peste 3 zile va fi caniculă', 2),
(2, NOW(), NOW() + interval '36 hours', 'Atenție, peste 3 zile vor fi înghețuri și temperatura ar putea coborî până la -20 grade', 2),
(3, NOW(), NOW() + interval '48 hours', 'Atenție, peste 3 zile va fi vânt puternic', 2),
(4, NOW(), NOW() + interval '60 hours', 'Atenție, posibile ninsori', 2),
(5, NOW(), NOW() + interval '72 hours', 'Atenție, va ploua', 2),

(1, NOW(), NOW() + interval '24 hours', 'Atenție, peste 3 zile va fi caniculă', 3),
(2, NOW(), NOW() + interval '36 hours', 'Atenție, peste 3 zile vor fi înghețuri și temperatura ar putea coborî până la -20 grade', 3),
(3, NOW(), NOW() + interval '48 hours', 'Atenție, peste 3 zile va fi vânt puternic', 3),
(4, NOW(), NOW() + interval '60 hours', 'Atenție, e posibil sa ningă', 3),
(5, NOW(), NOW() + interval '72 hours', 'Atenție, va ploua', 3),

(1, NOW(), NOW() + interval '24 hours', 'Atenție, peste 3 zile va fi caniculă', 4),
(2, NOW(), NOW() + interval '36 hours', 'Atenție, peste 3 zile vor fi înghețuri și temperatura ar putea coborî până la -20 grade', 4),
(3, NOW(), NOW() + interval '48 hours', 'Atenție, peste 3 zile va fi vânt puternic', 4),
(4, NOW(), NOW() + interval '60 hours', 'Atenție, e posibil sa ningă', 4),
(5, NOW(), NOW() + interval '72 hours', 'Atenție, posibile ploi', 4);

INSERT INTO notification (notification_type_id, seen_at, date_to, message, user_id) VALUES
(6, NOW() - interval '50 hours', NOW() - interval '48 hours', 'Atenție, posibil sa fie grindină', 2),
(7, NOW() - interval '60 hours', NOW() - interval '24 hours', 'Atenție, se așteaptă ceață', 2),

(6, NOW() - interval '50 hours', NOW() - interval '48 hours', 'Atenție, posibil sa fie grindină', 3),
(7, NOW() - interval '60 hours', NOW() - interval '24 hours', 'Atenție, se așteaptă ceață', 3),

(6, NOW() - interval '50 hours', NOW() - interval '48 hours', 'Atenție, posibil sa fie grindină', 4),
(7, NOW() - interval '60 hours', NOW() - interval '24 hours', 'Atenție, se așteaptă ceață', 4);