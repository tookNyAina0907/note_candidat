<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.example.note.model.*" %>
            <% List<Operateur> operateurs = (List<Operateur>) request.getAttribute("operateurs");
                    %>
                    <!DOCTYPE html>
                    <html lang="fr">

                    <head>
                        <meta charset="UTF-8">
                        <title>Liste des Opérateurs</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                            rel="stylesheet">
                    </head>

                    <body>
                        <div class="container mt-5">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h2>Liste des Opérateurs</h2>
                                <a href="<%= request.getContextPath() %>/operateurs/new" class="btn btn-primary">Ajouter
                                    un Opérateur</a>
                            </div>

                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Opérateur</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (operateurs !=null) { for (Operateur o : operateurs) { %>
                                        <tr>
                                            <td>
                                                <%= o.getId() %>
                                            </td>
                                            <td>
                                                <%= o.getOperateur() %>
                                            </td>
                                            <td>
                                                <a href="<%= request.getContextPath() %>/operateurs/edit/<%= o.getId() %>"
                                                    class="btn btn-sm btn-warning">Éditer</a>
                                                <a href="<%= request.getContextPath() %>/operateurs/delete/<%= o.getId() %>"
                                                    class="btn btn-sm btn-danger"
                                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet opérateur ?');">Supprimer</a>
                                            </td>
                                        </tr>
                                        <% } } %>
                                </tbody>
                            </table>
                            <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Retour à l'accueil</a>
                        </div>
                    </body>

                    </html>