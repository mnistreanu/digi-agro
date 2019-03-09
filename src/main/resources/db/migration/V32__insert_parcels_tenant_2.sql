INSERT INTO parcel(id, cadaster_number, city_id, country_id, county_id, created_at, description, land_worthiness_points, name, parent_id, tenant_id, area) VALUES
(4, 'AB-34567-NS-12356', null, 'MD', 'NS', '2018-07-26 12:45:00', 'sus in deal', 89, 'Floarea soarelui', null, 2, (RANDOM() * 100)),
(5, 'AB-34567-NS-17554', null, 'MD', 'NS', '2018-07-26 12:45:00', 'sus in stinga', 84, 'Porumb furajer', null, 2, (RANDOM() * 100)),
(6, 'AB-34567-NS-18698', null, 'MD', 'NS', '2018-07-26 12:45:00', 'jos la vale', 82, 'Negru de purcari', null, 2, (RANDOM() * 100)),
(7, 'AB-34567-NS-18398', null, 'MD', 'NS', '2018-07-26 12:45:00', 'jos iin stinga', 82, 'Tutun Kentuki', null, 2, (RANDOM() * 100));


INSERT INTO parcel_geometry(parcel_id, lat_center, lon_center, coordinates) VALUES
(4, 47.069773, 28.146284, '[ [47.070854, 28.145911], [47.069766, 28.144750], [47.068454, 28.146618], [47.069552, 28.147994] ]'),
(5, 47.068758, 28.147786, '[ [47.068454, 28.146618], [47.069552, 28.147994], [47.068984, 28.148861], [47.067851, 28.147590] ]'),
(6, 47.067853, 28.149274, '[ [47.068984, 28.148861], [47.067851, 28.147590], [47.066603, 28.149660], [47.067649, 28.150897] ]'),
(7, 47.066702, 28.151290, '[ [47.066603, 28.149660], [47.067649, 28.150897], [47.066009, 28.153511], [47.066329, 28.150125] ]');

SELECT setval('parcel_id_seq', (SELECT MAX(id) FROM parcel));
SELECT setval('parcel_id_seq', (SELECT MAX(parcel_id) FROM parcel_geometry));