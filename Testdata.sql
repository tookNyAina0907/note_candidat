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
(1, 1, 1, 12.0),
(1, 2, 2, 14.0),
(1, 3, 3, 9.0),
(1, 4, 4, 16.0),
(1, 5, 5, 6.0),
(2, 1, 1, 8.0),
(2, 2, 2, 10.0),
(2, 3, 3, 7.0),
(2, 4, 4, 14.0),
(2, 5, 5, 4.0),
(3, 1, 1, 15.0),
(3, 2, 2, 13.0),
(3, 3, 3, 10.0),
(3, 4, 4, 17.0),
(3, 5, 5, 7.0),
(4, 1, 1, 9.0),
(4, 2, 2, 11.0),
(4, 3, 3, 6.0),
(4, 4, 4, 13.0),
(4, 5, 5, 5.0),
(5, 1, 1, 11.0),
(5, 2, 2, 12.0),
(5, 3, 3, 8.0),
(5, 4, 4, 15.0),
(5, 5, 5, 6.0);