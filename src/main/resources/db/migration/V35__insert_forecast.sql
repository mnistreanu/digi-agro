INSERT INTO forecast(id, crop_id, tenant_id, harvesting_year, name, description, crop_category_id) VALUES
(1, 1, 1, 2018, 'Prognoza de grîu in 2018', 'Prognoza de grîu in 2018 pe terenurile din Calarasi si Strășeni', 1);

INSERT INTO forecast_snapshot(id, forecast_id, unit_of_measure, unit_price, created_at) VALUES 
(1, 1, 't', 2900, '2018-01-01'),
(2, 1, 't', 2900, '2018-02-01'),
(3, 1, 't', 2900, '2018-03-01'),
(4, 1, 't', 2900, '2018-04-01'),
(5, 1, 't', 2950, '2018-05-01'),
(6, 1, 't', 2950, '2018-06-01'),
(7, 1, 't', 2950, '2018-07-01'),
(8, 1, 't', 3000, '2018-08-01');

INSERT INTO forecast_parcel(forecast_snapshot_id, parcel_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 1),
(4, 2),
(5, 1),
(5, 2),
(6, 1),
(6, 2),
(7, 1),
(7, 2),
(8, 1),
(8, 2);

INSERT INTO forecast_harvest(forecast_snapshot_id, factor_name, quantity, created_at, created_by) VALUES
(1, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(2, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(3, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(4, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(5, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(5, 'A plouat cu grindină', -1.5, '2018-05-22', 2),
(6, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(6, 'A plouat cu grindină', -1.3, '2018-05-22', 2),
(7, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(7, 'A plouat cu grindină', -1.3, '2018-05-22', 2),
(7, 'A fost soare si ploi de scurte durata, timp favorabil', 0.3, '2018-07-22', 2),
(8, 'Prognoza initială de grîu la hectar, tone/hectar', 4.5, '2018-01-05', 2),
(8, 'A plouat cu grindină', -1.3, '2018-05-22', 2),
(8, 'A fost soare si ploi de scurte durată, timp favorabil', 0.3, '2018-07-22', 2),
(8, 'Se prevede secetă', -0.4, '2018-08-02', 2);


SELECT setval('forecast_id_seq', (SELECT MAX(id) FROM forecast));
SELECT setval('forecast_snapshot_id_seq', (SELECT MAX(id) FROM forecast_snapshot));
