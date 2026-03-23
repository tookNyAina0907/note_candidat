<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.example.note.model.*" %>
            <% List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
                    %>
                    <!DOCTYPE html>
                    <html lang="fr">

                    <head>
                        <meta charset="UTF-8">
                        <title>Liste des Matières</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet">
                    </head>

                    <body>
                        <div class="container mt-5">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2>Liste des Matières</h2>
                                <a href="<%= request.getContextPath() %>/matieres/new" class="btn btn-primary">Ajouter
                                    une Matière</a>
                            </div>

                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nom</th>
                                        <th>Coefficient</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (matieres !=null) { for (Matiere m : matieres) { %>
                                        <tr>
                                            <td>
                                                <%= m.getId() %>
                                            </td>
                                            <td>
                                                <%= m.getNom() %>
                                            </td>
                                            <td>
                                                <%= m.getCoefficient() %>
                                            </td>
                                            <td>
                                                <a href="<%= request.getContextPath() %>/matieres/edit/<%= m.getId() %>"
                                                    class="btn btn-sm btn-warning">Éditer</a>
                                                <a href="<%= request.getContextPath() %>/matieres/delete/<%= m.getId() %>"
                                                    class="btn btn-sm btn-danger"
                                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette matière ?');">Supprimer</a>
                                            </td>
                                        </tr>
                                        <% } } %>
                                </tbody>
                            </table>
                            <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Retour à l'accueil</a>
                        </div>
                    </body>

                    </html>