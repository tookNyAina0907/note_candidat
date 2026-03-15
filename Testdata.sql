

INSERT INTO t_candidat ( nom, prenom, matricule) VALUES
('Rakoto','Jean','MAT001'),
('Rabe','Marie','MAT002'),
('Andrianina','Paul','MAT003');


INSERT INTO t_correcteur ( nom, prenom, numtel) VALUES
('Rasoa','Andry','341234567'),
('Rasoanaivo','Hery','342345678'),
('Rajaonarison','Lala','343456789'),
('Rakotondrazaka','Mamy','344567890'),
('Ratsimbazafy','Fidy','345678901'),
('Rakotomalala','Sofia','345664901');


INSERT INTO t_matiere (nom, coefficient) VALUES
('Mathematiques',4),
('Physique',3),
('Chimie',2);


INSERT INTO t_resolution (nom) VALUES
('plus petit'),
('plus grand'),
('moyenne');


INSERT INTO t_operateur (operateur) VALUES
('<'),
('>'),
('=');



INSERT INTO t_parametre (id_matiere,id_resolution,id_operateur,seuil) VALUES
(1,1,1,8),
(1,2,2,7),
(2,1,1,6),
(2,2,2,3),
(3,1,1,12),
(3,2,2,10);


INSERT INTO t_note (id_candidat,id_matiere,id_correcteur,note) VALUES
(1,1,1,10.5),
(1,1,2,9),
(1,1,3,13),
(1,2,4,12),
(1,2,5,12),
(1,2,6,12),
(1,3,1,17),
(1,3,2,9),
(1,3,3,14),

(2,1,4,8),
(2,1,5,11),
(2,1,6,9),
(2,2,1,10),
(2,2,2,12),
(2,2,3,11),
(2,3,4,13),
(2,3,5,12),
(2,3,6,14),

(3,1,1,15),
(3,1,2,14),
(3,1,3,16),
(3,2,4,9),
(3,2,5,10),
(3,2,6,11),
(3,3,1,18),
(3,3,2,17),
(3,3,3,16);