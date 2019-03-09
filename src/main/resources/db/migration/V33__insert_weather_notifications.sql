INSERT INTO notification_type(id, translation_key) VALUES
(1, 'weather-heat-alert'),
(2, 'weather-freezing-alert'),
(3, 'weather-wind-alert'),
(4, 'weather-snow-alert'),
(5, 'weather-rain-alert'),
(6, 'weather-hail-alert'),
(7, 'weather-fog-alert'),
(8, 'weather-hoar-frost-alert');

SELECT setval('notification_type_id_seq', (SELECT MAX(id) FROM notification_type));
