INSERT INTO parcel(id, cadaster_number, city_id, country_id, county_id, created_at, description, land_worthiness_points, name, parent_id, tenant_id, area) VALUES
(1, 'AB-34567-ZB-12356', null, 'MD', 'HN', '2018-07-26 12:45:00', 'sus in deal', 89, 'Floarea soarelui', null, 1, (RANDOM() * 100)),
(2, 'AB-34567-ZB-17554', null, 'MD', 'HN', '2018-07-26 12:45:00', 'sus in stinga', 84, 'Porumb furajer', null, 1, (RANDOM() * 100)),
(3, 'AB-34567-ZB-1898', null, 'MD', 'HN', '2018-07-26 12:45:00', 'jos la vale', 82, 'Negru de purcari', null, 1, (RANDOM() * 100));



INSERT INTO parcel_geometry(parcel_id, lat_center, lon_center, coordinates) VALUES
(1, 46.806277, 28.639881, '[ [46.811898, 28.638581], [46.810215, 28.634720], [46.798834, 28.643039], [46.799954, 28.644066], [46.802772, 28.644606] ]'),
(2, 46.812229, 28.642183, '[ [46.811898, 28.638581], [46.814937, 28.645115], [46.811814, 28.644840], [46.809678, 28.640038] ]'),
(3, 46.808621, 28.644148, '[ [46.811814, 28.644840], [46.809678, 28.640038], [46.804438, 28.643071], [46.801469, 28.645740], [46.802652, 28.645927], [46.803798, 28.645621], [46.807954, 28.648691] ]');


SELECT setval('parcel_id_seq', (SELECT MAX(id) FROM parcel));
SELECT setval('parcel_id_seq', (SELECT MAX(parcel_id) FROM parcel_geometry));
