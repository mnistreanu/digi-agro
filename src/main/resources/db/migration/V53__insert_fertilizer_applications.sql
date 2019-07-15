INSERT INTO fertilizer_application (id, parcel_id, application_date, comments, placement_type,
            fertilizer_id, tone_price, fertilized_area, rate, rate_unit_of_measure, hectare_cost)
VALUES(1, 1, '2019-04-12 12:00', 'Totul a fost bine, afara era soare', 'soil', 1, 235, 50.60, 12, 'unit-of-measure.tone', 219);

INSERT INTO fertilizer_application (id, parcel_id, application_date, comments, placement_type,
            fertilizer_id, tone_price, fertilized_area, rate, rate_unit_of_measure, hectare_cost)
VALUES(2, 1, '2019-07-15 11:00', 'S-a aplicat 3 zile dupa grindina', 'air', 3, 193, 50.60, 6, 'unit-of-measure.tone', 345);

INSERT INTO fertilizer_application (id, parcel_id, application_date, comments, placement_type,
            fertilizer_id, tone_price, fertilized_area, rate, rate_unit_of_measure, hectare_cost)
VALUES(3, 2, '2019-04-12 12:00', 'Totul a fost bine, afara era soare', 'soil', 1, 235, 50.60, 12, 'unit-of-measure.tone', 219);

INSERT INTO fertilizer_application (id, parcel_id, application_date, comments, placement_type,
            fertilizer_id, tone_price, fertilized_area, rate, rate_unit_of_measure, hectare_cost)
VALUES(4, 2, '2019-07-15 11:00', 'S-a aplicat 3 zile dupa grindina', 'air', 3, 193, 50.60, 6, 'unit-of-measure.tone', 345);

SELECT setval('fertilizer_application_id_seq', (SELECT MAX(id) FROM fertilizer_application));
