INSERT INTO t_candidat (nom, prenom, matricule) VALUES
('Rakoto', 'Jean', 'MAT001'),
('Rabe', 'Marie', 'MAT002'),
('Andrianina', 'Paul', 'MAT003'),
('Razanaka', 'Luc', 'MAT004'),
('Rakotomalala', 'Sofia', 'MAT005');

INSERT INTO t_correcteur (nom, prenom, numtel) VALUES
('Rasoa', 'Andry', '0341234567'),
('Rasoanaivo', 'Hery', '0342345678'),
('Rajaonarison', 'Lala', '0343456789'),
('Rakotondrazaka', 'Mamy', '0344567890'),
('Ratsimbazafy', 'Fidy', '0345678901');

INSERT INTO t_matiere (nom, coefficient) VALUES
('Mathematiques', 4),
('Physique', 3),
('Chimie', 2),
('Informatique', 5),
('Biologie', 1);

INSERT INTO t_resolution (nom) VALUES
('plus petit'),
('plus grand'),
('moyenne');

INSERT INTO t_operateur (operateur) VALUES
('<'),
('>'),
('='),
('<='),
('>=');

INSERT INTO t_parametre (id_matiere, id_resolution, id_operateur, seuil) VALUES
(1, 1, 1, 10.0),
(2, 2, 2, 12.0),
(3, 3, 1, 8.0),
(4, 1, 2, 15.0),
(5, 2, 1, 5.0);

INSERT INTO t_note (id_candidat, id_matiere, id_correcteur, note) VALUES
(1,1,1,12.5)
