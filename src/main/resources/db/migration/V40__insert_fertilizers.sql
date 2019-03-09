INSERT INTO fertilizer (id, fertilizer_type, name_ro, description_ro) VALUES
(1, 'MINERAL', 'AZOTAT (NITRAT) DE AMONIU granulat', 'Nitratul (azotatul) de amoniu este un îngrăşământ granulat, tratat cu agenţi de condiţionare pentru a preveni aglomerarea.'),
(2, 'MINERAL', 'AZOTAT (NITRAT) DE AMONIU prill', 'Nitratul (azotatul) de amoniu este un îngrăşământ granulat, tratat cu agenţi de condiţionare pentru a preveni aglomerarea.'),
(3, 'MINERAL', 'Ingrasamant complex NPK 16:16:16 MOP', 'Efectul fertilizant al complexului npk 16:16:16 mop se bazează pe conținutul celor 3 macroelemente esențiale, azot (N), Fosfor (P) și Potasiu (K), ce asigură un raport echilibrat de fertilizare.'),
(4, 'MINERAL', 'Ingrasamant complex NPK 16:16:16 SOP', 'Efectul fertilizant al complexului npk 16:16:16  se bazează pe conținutul celor 3 macroelemente esențiale, azot (N), Fosfor (P) și Potasiu (K), ce asigură un raport echilibrat de fertilizare. Îngrășământul este îmbogățit cu sulf (s) ușor absorbabil. '),
(5, 'MINERAL', 'Ingrasamant complex NPK 20:20:0', 'Îngrăşământul complex npk 20:20:0 conţine două elemente esențiale, azot (N) si Fosfor (P), care asigură un raport echilibrat de fertilizare.'),
(6, 'MINERAL', 'Ingrasamant complex NPK 27:13,5:0', 'Conținutul mai ridicat de azot (N) al complexului npk 27:13,5:0 asigură utilizarea ca îngrășământ suplimentar pe parcursul perioadelor de vegetație.'),
(7, 'MINERAL', 'NITROCALCAR (CAN) granulat', 'Nitrocalcarul (can) granulat este un îngrășământ pe bază de nitrat de amoniu, completat cu dolomită sau carbonat de calciu ce asigură culturilor o aprovizionare suplimentară cu Calciu (Ca) și Magneziu (Mg). Este un îngrășământ de tip granulat, tratat cu agenți de condiționare pentru a preveni aglomerarea.'),
(8, 'MINERAL', 'UREE granulata', 'Ureea granulată este un îngrășământ cu azot, acesta regăsindu-se sub formă amidica. Este un produs NOU, cu o granulaţie substanţial îmbunătăţită, granulele fiind mai compacte şi suprafaţa mai netedă. Se solubilizează mai încet, împiedicând pierderea în atmosferă a azotului.');

UPDATE fertilizer SET name_ru = name_ro, description_ru = description_ro WHERE name_ru IS NULL;

SELECT SETVAL('fertilizer_id_seq', (SELECT MAX(id) FROM fertilizer));

