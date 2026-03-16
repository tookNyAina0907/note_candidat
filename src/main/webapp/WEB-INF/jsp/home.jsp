<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="fr">

    <head>
        <meta charset="UTF-8">
        <title>Accueil - Note Candidat</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/style.css" rel="stylesheet">
    </head>

    <body>
        <div class="container mt-5 text-center">
            <h1 class="mb-4">Bienvenue dans le Système de Gestion des Notes</h1>
            <p class="lead mb-5">Choisissez une option ci-dessous pour gérer les différentes entités du système.</p>

            <div class="row g-4 justify-content-center">
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Candidats</h5>
                            <p class="card-text">Gérer la liste des candidats inscrits aux examens.</p>
                            <a href="<%= request.getContextPath() %>/candidats" class="btn btn-primary w-100">Voir les
                                Candidats</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Matières</h5>
                            <p class="card-text">Gérer les différentes matières et leurs coefficients.</p>
                            <a href="<%= request.getContextPath() %>/matieres" class="btn btn-primary w-100">Voir les
                                Matières</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Correcteurs</h5>
                            <p class="card-text">Gérer la liste des correcteurs d'examen.</p>
                            <a href="<%= request.getContextPath() %>/correcteurs" class="btn btn-primary w-100">Voir les
                                Correcteurs</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Opérateurs</h5>
                            <p class="card-text">Gérer les opérateurs mathématiques pour les calculs.</p>
                            <a href="<%= request.getContextPath() %>/operateurs" class="btn btn-primary w-100">Voir les
                                Opérateurs</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Résolutions</h5>
                            <p class="card-text">Gérer les différentes résolutions possibles.</p>
                            <a href="<%= request.getContextPath() %>/resolutions" class="btn btn-primary w-100">Voir les
                                Résolutions</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">Paramètres</h5>
                            <p class="card-text">Configurer les paramètres globaux du système.</p>
                            <a href="<%= request.getContextPath() %>/parametres" class="btn btn-primary w-100">Voir les
                                Paramètres</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 mt-4">
                    <div class="card h-100 shadow border-primary">
                        <div class="card-body">
                            <h4 class="card-title text-primary">Saisie des Notes</h4>
                            <p class="card-text">Saisir de nouvelles notes ou associer des notes aux correcteurs.</p>
                            <a href="<%= request.getContextPath() %>/notes"
                                class="btn btn-primary text-white w-100">Gérer les Notes</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 mt-4">
                    <div class="card h-100 shadow border-success">
                        <div class="card-body">
                            <h4 class="card-title text-success">Note Finale par Candidat/Matière</h4>
                            <p class="card-text">Consulter le récapitulatif de la note finale pour un candidat à une
                                matière spécifique.</p>
                            <a href="<%= request.getContextPath() %>/notes/final"
                                class="btn btn-success text-white w-100">Voir la Note Finale</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    </html>