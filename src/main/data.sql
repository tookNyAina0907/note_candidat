SET session_replication_role = 'replica';

TRUNCATE TABLE t_client RESTART IDENTITY CASCADE;
TRUNCATE TABLE t_demande RESTART IDENTITY CASCADE;
TRUNCATE TABLE t_devis RESTART IDENTITY CASCADE;
TRUNCATE TABLE t_type_devis RESTART IDENTITY CASCADE;
TRUNCATE TABLE t_statut RESTART IDENTITY CASCADE;
TRUNCATE TABLE t_detail_devis RESTART IDENTITY CASCADE;
TRUNCATE TABLE t_demande_statut RESTART IDENTITY CASCADE;

SET session_replication_role = 'origin';
INSERT INTO t_type_devis(nom) VALUES
('etude'),
('forage');

INSERT INTO t_statut(nom) VALUES
('demande cree'),
('devis etude envoyé'),
('devis etude acceptee'),
('devis etude refuse'),
('devis forage envoyé'),
('devis forage acceptee'),
('devis forage refuse');

INSERT into t_parametre(remise) VALUES
(10);

INSERT INTO t_heure_travail(debut, fin) VALUES
('08:00:00', '17:00:00');