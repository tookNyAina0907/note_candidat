<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.example.note.model.*" %>
            <% List<Correcteur> correcteurs = (List<Correcteur>) request.getAttribute("correcteurs");
                    %>
                    <!DOCTYPE html>
                    <html lang="fr">

                    <head>
                        <meta charset="UTF-8">
                        <title>Liste des Correcteurs</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet">
                    </head>

                    <body>
                        <div class="container mt-5">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2>Liste des Correcteurs</h2>
                                <a href="<%= request.getContextPath() %>/note/correcteurs/new"
                                    class="btn btn-primary">Ajouter un Correcteur</a>
                            </div>

                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Nom</th>
                                        <th>Prénom</th>
                                        <th>Numéro de Téléphone</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (correcteurs !=null) { for (Correcteur c : correcteurs) { %>
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
                                                <%= c.getNumtel() %>
                                            </td>
                                            <td>
                                                <a href="<%= request.getContextPath() %>/note/correcteurs/edit/<%= c.getId() %>"
                                                    class="btn btn-sm btn-warning">Éditer</a>
                                                <a href="<%= request.getContextPath() %>/note/correcteurs/delete/<%= c.getId() %>"
                                                    class="btn btn-sm btn-danger"
                                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce correcteur ?');">Supprimer</a>
                                            </td>
                                        </tr>
                                        <% } } %>
                                </tbody>
                            </table>
                            <a href="<%= request.getContextPath() %>/note" class="btn btn-secondary">Retour à l'accueil</a>
                        </div>
                    </body>

                    </html>