<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.Map" %>
        <%@ page import="java.util.List" %>
            <%@ page import="com.example.note.model.*" %>
                <!DOCTYPE html>
                <html lang="fr">

                <head>
                    <meta charset="UTF-8">
                    <title>Liste des Notes</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                </head>

                <body>
                    <div class="container mt-5">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <h2>Liste des Notes</h2>
                            <a href="<%= request.getContextPath() %>/notes/new" class="btn btn-primary">Saisir des
                                Notes</a>
                        </div>

                        <table class="table table-bordered table-striped table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>Candidat / Matière</th>
                                    <th>Correcteurs et Notes</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% Map<String, List<Note>> groupedNotes = (Map<String, List<Note>>)
                                        request.getAttribute("groupedNotes");
                                        if (groupedNotes != null) {
                                        for (Map.Entry<String, List<Note>> entry : groupedNotes.entrySet()) {
                                            %>
                                            <tr>
                                                <td class="align-middle bg-light"><strong>
                                                        <%= entry.getKey() %>
                                                    </strong></td>
                                                <td>
                                                    <table class="table table-sm table-borderless mb-0">
                                                        <% List<Note> notesList = entry.getValue();
                                                            if (notesList != null) {
                                                            for (Note n : notesList) {
                                                            %>
                                                            <tr>
                                                                <td>
                                                                    <%= n.getCorrecteur().getNom() %>
                                                                        <%= n.getCorrecteur().getPrenom() %>
                                                                </td>
                                                                <td><strong>
                                                                        <%= n.getNote() %>
                                                                    </strong></td>
                                                                <td class="text-end">
                                                                    <a href="<%= request.getContextPath() %>/notes/edit/<%= n.getId() %>"
                                                                        class="btn btn-sm btn-warning">Éditer</a>
                                                                    <a href="<%= request.getContextPath() %>/notes/delete/<%= n.getId() %>"
                                                                        class="btn btn-sm btn-danger px-2"
                                                                        onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette note ?');">X</a>
                                                                </td>
                                                            </tr>
                                                            <% } } %>
                                                    </table>
                                                </td>
                                            </tr>
                                            <% } } %>
                            </tbody>
                        </table>
                        <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Retour à l'accueil</a>
                    </div>
                </body>

                </html>