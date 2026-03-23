<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.note.model.*" %>
<%
    Note note = (Note) request.getAttribute("note");
    List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");
    List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
    List<Correcteur> correcteurs = (List<Correcteur>) request.getAttribute("correcteurs");
    boolean isEdit = (note != null && note.getId() != null);
    String title = isEdit ? "Modifier une Note" : "Saisir des Notes";
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title><%= title %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2><%= title %></h2>

    <form action="<%= request.getContextPath() %>/note/notes/save" method="post" class="mt-4">
        <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= note.getId() %>"/>
        <% } %>

        <div class="mb-3">
            <label for="candidat" class="form-label">Candidat</label>
            <select class="form-select" id="candidat" name="candidatId" required>
                <option value="">Sélectionnez un candidat</option>
                <%
                    if (candidats != null) {
                        for (Candidat c : candidats) {
                            String sel = (isEdit && note.getCandidat() != null && note.getCandidat().getId().equals(c.getId())) ? "selected" : "";
                %>
                    <option value="<%= c.getId() %>" <%= sel %>><%= c.getMatricule() %> - <%= c.getNom() %> <%= c.getPrenom() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="mb-3">
            <label for="matiere" class="form-label">Matière</label>
            <select class="form-select" id="matiere" name="matiereId" required>
                <option value="">Sélectionnez une matière</option>
                <%
                    if (matieres != null) {
                        for (Matiere m : matieres) {
                            String sel = (isEdit && note.getMatiere() != null && note.getMatiere().getId().equals(m.getId())) ? "selected" : "";
                %>
                    <option value="<%= m.getId() %>" <%= sel %>><%= m.getNom() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div id="correcteurs-container">
            <% if (isEdit) { %>
                <div class="row align-items-end mb-3 correcteur-row">
                    <div class="col-md-5">
                        <label class="form-label">Correcteur</label>
                        <select class="form-select" name="correcteurIds" required>
                            <option value="">Sélectionnez un correcteur</option>
                            <%
                                if (correcteurs != null) {
                                    for (Correcteur cor : correcteurs) {
                                        String sel = (note.getCorrecteur() != null && note.getCorrecteur().getId().equals(cor.getId())) ? "selected" : "";
                            %>
                                <option value="<%= cor.getId() %>" <%= sel %>><%= cor.getNom() %> <%= cor.getPrenom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <label class="form-label">Note (sur 20)</label>
                        <input type="number" step="0.001" min="0" max="20" class="form-control"
                               name="noteValues"
                               value="<%= note.getNote() != null ? note.getNote() : "" %>" required>
                    </div>
                </div>
            <% } else { %>
                <div class="row align-items-end mb-3 correcteur-row">
                    <div class="col-md-5">
                        <label class="form-label">Correcteur</label>
                        <select class="form-select" name="correcteurIds" required>
                            <option value="">Sélectionnez un correcteur</option>
                            <%
                                if (correcteurs != null) {
                                    for (Correcteur cor : correcteurs) {
                            %>
                                <option value="<%= cor.getId() %>"><%= cor.getNom() %> <%= cor.getPrenom() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <label class="form-label">Note (sur 20)</label>
                        <input type="number" step="0.001" min="0" max="20" class="form-control" name="noteValues" required>
                    </div>
                    <div class="col-md-2" style="display:none;">
                        <button type="button" class="btn btn-outline-danger remove-btn w-100">X</button>
                    </div>
                </div>
            <% } %>
        </div>

        <% if (!isEdit) { %>
            <button type="button" id="add-btn" class="btn btn-outline-info mb-4">+ Ajouter une autre note</button>
        <% } %>

        <div>
            <button type="submit" class="btn btn-success">Enregistrer</button>
            <a href="<%= request.getContextPath() %>/note/notes" class="btn btn-secondary">Annuler</a>
        </div>
    </form>
</div>

<script>
document.addEventListener('DOMContentLoaded', function () {
    var addBtn = document.getElementById('add-btn');
    if (addBtn) {
        addBtn.addEventListener('click', function () {
            var container = document.getElementById('correcteurs-container');
            var rows = container.querySelectorAll('.correcteur-row');
            var clone = rows[0].cloneNode(true);
            clone.querySelector('select').selectedIndex = 0;
            clone.querySelector('input').value = '';
            var removeCol = clone.querySelector('.col-md-2');
            if (removeCol) {
                removeCol.style.display = 'block';
                clone.querySelector('.remove-btn').addEventListener('click', function () {
                    clone.remove();
                });
            }
            container.appendChild(clone);
        });
    }
});
</script>
</body>
</html>