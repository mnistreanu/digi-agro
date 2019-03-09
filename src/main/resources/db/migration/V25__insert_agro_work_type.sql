INSERT INTO agro_work_type (id, name_ro, name_ru) VALUES
(1,'Aratul','Вспашка'),
(2,'Lucrarea cu grapa','Работа с бороной'),
(3,'Prăgatirea pat germinativ','Прорастание проростков'),
(4,'Semănatul','Посев'),
(5,'Pulverizarea chemicalelor','Распыление химикатов'),
(6,'Irigarea','Орошение'),
(7,'Culesul roadei','Сбор урожая');

SELECT setval('agro_work_type_id_seq', (SELECT MAX(id) FROM agro_work_type));