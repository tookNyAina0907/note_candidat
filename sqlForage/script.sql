CREATE DATABASE forage;
\c forage;

CREATE TABLE t_client(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE t_demande(
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    description TEXT NOT NULL,
    date_demande TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    district VARCHAR(255) NOT NULL,
    lieu VARCHAR(255) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES t_client(id)
);


CREATE TABLE t_type_devis(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE t_devis(
    id SERIAL PRIMARY KEY,
    demande_id INT NOT NULL,
    type_devis_id INT NOT NULL,
    date_devis TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (demande_id) REFERENCES t_demande(id),
    FOREIGN KEY (type_devis_id) REFERENCES t_type_devis(id)
);

CREATE TABLE t_detail_devis(
    id SERIAL PRIMARY KEY,
    devis_id INT NOT NULL,
    libelle TEXT NOT NULL,
    prix DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (devis_id) REFERENCES t_devis(id)
);

CREATE TABLE t_statut(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE t_demande_statut(
    id SERIAL PRIMARY KEY,
    demande_id INT NOT NULL,
    statut_id INT NOT NULL,
    date_statut TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (demande_id) REFERENCES t_demande(id),
    FOREIGN KEY (statut_id) REFERENCES t_statut(id)
);
ALTER TABLE t_demande_statut ADD COLUMN observation VARCHAR(250);
ALTER TABLE t_demande_statut ADD COLUMN duree TIME;

CREATE TABLE t_heure_travail(
    id SERIAL PRIMARY KEY,
    debut TIME,
    fin TIME
);

CREATE TABLE t_parametre(
    id SERIAL PRIMARY KEY,
    remise DECIMAL(10,2)
);
SELECT SUM(prix*quantite) as devis_total
FROM t_detail_devis;