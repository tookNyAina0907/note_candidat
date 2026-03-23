<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.example.note.model.*" %>
            <% List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");
                    %>
                    <!DOCTYPE html>
                    <html lang="fr">

                    <head>
                        <meta charset="UTF-8">
                        <title>Liste des Candidats</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet">
                    </head>

                    <body>
                        <div class="container mt-5">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2>Liste des Candidats</h2>
                                <a href="<%= request.getContextPath() %>/note/candidats/new" class="btn btn-primary">Ajouter
                                    un Candidat</a>
                            </div>

                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nom</th>
                                        <th>Prénom</th>
                                        <th>Matricule</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (candidats !=null) { for (Candidat c : candidats) { %>
                                        <tr>
                                            <td>
                                                <%= c.getId() %>
                                            </td>
                                            <td>
                                                <%= c.getNom() %>
                                            </td>
                                            <td>
                                                <%= c.getPrenom() %>
                                            </td>
                                            <td>
                                                <%= c.getMatricule() %>
                                            </td>
                                            <td>
                                                <a href="<%= request.getContextPath() %>/note/candidats/edit/<%= c.getId() %>"
                                                    class="btn btn-sm btn-warning">Éditer</a>
                                                <a href="<%= request.getContextPath() %>/note/candidats/delete/<%= c.getId() %>"
                                                    class="btn btn-sm btn-danger"
                                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce candidat ?');">Supprimer</a>
                                            </td>
                                        </tr>
                                        <% } } %>
                                </tbody>
                            </table>
                            <a href="<%= request.getContextPath() %>/note" class="btn btn-secondary">Retour à l'accueil</a>
                        </div>
                    </body>

                    </html>