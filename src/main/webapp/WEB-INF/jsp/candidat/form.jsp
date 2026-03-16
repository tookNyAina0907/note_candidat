<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.example.model.Candidat" %>
        <% Candidat candidat=(Candidat) request.getAttribute("candidat"); String title=(candidat !=null &&
            candidat.getId() !=null) ? "Modifier un Candidat" : "Ajouter un Candidat" ; %>
            <!DOCTYPE html>
            <html lang="fr">

            <head>
                <meta charset="UTF-8">
                <title>
                    <%= title %>
                </title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            </head>

            <body>
                <div class="container mt-5">
                    <h2>
                        <%= title %>
                    </h2>

                    <form action="<%= request.getContextPath() %>/candidats/save" method="post" class="mt-4">
                        <% if (candidat !=null && candidat.getId() !=null) { %>
                            <input type="hidden" name="id" value="<%= candidat.getId() %>" />
                            <% } %>

                                <div class="mb-3">
                                    <label for="nom" class="form-label">Nom</label>
                                    <input type="text" class="form-control" id="nom" name="nom"
                                        value="<%= candidat != null && candidat.getNom() != null ? candidat.getNom() : "" %>"
                                        required>
                                </div>

                                <div class="mb-3">
                                    <label for="prenom" class="form-label">Prénom</label>
                                    <input type="text" class="form-control" id="prenom" name="prenom"
                                        value="<%= candidat != null && candidat.getPrenom() != null ? candidat.getPrenom() : "" %>"
                                        required>
                                </div>

                                <div class="mb-3">
                                    <label for="matricule" class="form-label">Matricule</label>
                                    <input type="text" class="form-control" id="matricule" name="matricule"
                                        value="<%= candidat != null && candidat.getMatricule() != null ? candidat.getMatricule() : "" %>"
                                        required>
                                </div>

                                <button type="submit" class="btn btn-success">Enregistrer</button>
                                <a href="<%= request.getContextPath() %>/candidats"
                                    class="btn btn-secondary">Annuler</a>
                    </form>
                </div>
            </body>

            </html>