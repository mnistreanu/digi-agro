INSERT INTO crop_season(id, tenant_id, harvest_year, crop_id, crop_subculture_id, crop_variety_id, start_date, end_date, yield_goal) VALUES 

-- Griu de soi XERCES pentru anul 2019, pentru tenant=1
(1, 1, 2019, 1, 1, 3, '2019-01-01', '2019-12-31', 1200),

-- Porumb pentru anul 2019, pentru tenant=1
(2, 1, 2019, 6, null, null, '2019-01-01', '2019-12-31', null),

-- Griu de soi XERCES pentru anul 2019, pentru tenant=2
(3, 2, 2019, 1, 1, 3, '2019-01-01', '2019-12-31', 1200),

-- Porumb pentru anul 2019, pentru tenant=2
(4, 2, 2019, 6, null, null, '2019-01-01', '2019-12-31', null);


INSERT INTO parcel_crop_season(id, parcel_id, crop_season_id, crop_variety_id, planted_at, rows_on_parcel, plants_on_row, space_between_rows, space_between_plants) VALUES
(1, 1, 1, 1, null, 30, 115, 1.2, 0.8),
(2, 2, 2, null, null, 44, 99, 1.4, 0.5);

SELECT setval('crop_season_id_seq', (SELECT MAX(id) FROM crop_season));
SELECT setval('parcel_crop_season_id_seq', (SELECT MAX(id) FROM parcel_crop_season));