<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.example.note.model.*" %>
            <% List<Parametre> parametres = (List<Parametre>) request.getAttribute("parametres");
                    %>
                    <!DOCTYPE html>
                    <html lang="fr">

                    <head>
                        <meta charset="UTF-8">
                        <title>Liste des Paramètres</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet">
                    </head>

                    <body>
                        <div class="container mt-5">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2>Liste des Paramètres</h2>
                                <a href="<%= request.getContextPath() %>/parametres/new" class="btn btn-primary">Ajouter
                                    un Paramètre</a>
                            </div>

                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Matière</th>
                                        <th>Résolution</th>
                                        <th>Opérateur</th>
                                        <th>Seuil</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (parametres !=null) { for (Parametre p : parametres) { %>
                                        <tr>
                                            <td>
                                                <%= p.getId() %>
                                            </td>
                                            <td>
                                                <%= p.getMatiere().getNom() %>
                                            </td>
                                            <td>
                                                <%= p.getResolution().getNom() %>
                                            </td>
                                            <td>
                                                <%= p.getOperateur().getOperateur() %>
                                            </td>
                                            <td>
                                                <%= p.getSeuil() %>
                                            </td>
                                            <td>
                                                <a href="<%= request.getContextPath() %>/parametres/edit/<%= p.getId() %>"
                                                    class="btn btn-sm btn-warning">Éditer</a>
                                                <a href="<%= request.getContextPath() %>/parametres/delete/<%= p.getId() %>"
                                                    class="btn btn-sm btn-danger"
                                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce paramètre ?');">Supprimer</a>
                                            </td>
                                        </tr>
                                        <% } } %>
                                </tbody>
                            </table>
                            <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Retour à l'accueil</a>
                        </div>
                    </body>

                    </html>