INSERT INTO county (id, country_id, name_ro, name_ru) VALUES ('GE', 'MD', 'UTA Găgăuzia', 'АТО Гагаузия')

DELETE FROM county WHERE country_id='MD' and id='CG';
DELETE FROM county WHERE country_id='MD' and id='CO';
DELETE FROM county WHERE country_id='MD' and id='RB';
DELETE FROM county WHERE country_id='MD' and id='GR';
DELETE FROM county WHERE country_id='MD' and id='SL';

UPDATE county SET name_ro='Transnistria', name_ru='Приднестровье' WHERE country_id='MD' and id='TS';