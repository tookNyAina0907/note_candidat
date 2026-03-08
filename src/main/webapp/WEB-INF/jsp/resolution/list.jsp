<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Resolution" %>
<%
    List<Resolution> resolutions = (List<Resolution>) request.getAttribute("resolutions");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Résolutions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Liste des Résolutions</h2>
            <a href="<%= request.getContextPath() %>/resolutions/new" class="btn btn-primary">Ajouter une Résolution</a>
        </div>
        
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (resolutions != null) {
                        for (Resolution r : resolutions) {
                %>
                    <tr>
                        <td><%= r.getId() %></td>
                        <td><%= r.getNom() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/resolutions/edit/<%= r.getId() %>" class="btn btn-sm btn-warning">Éditer</a>
                            <a href="<%= request.getContextPath() %>/resolutions/delete/<%= r.getId() %>" class="btn btn-sm btn-danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette résolution ?');">Supprimer</a>
                        </td>
                    </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Retour à l'accueil</a>
    </div>
</body>
</html>
