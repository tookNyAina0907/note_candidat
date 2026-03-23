<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.note.model.*" %>
<%
    List<NoteFinalCandidat> noteFinalCandidats =
        (List<NoteFinalCandidat>) request.getAttribute("noteFinalCandidats");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notes Finales – Tous les candidats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
    <style>
        .note-cell { text-align: center; }
        .note-pass  { color: #198754; font-weight: 600; }
        .note-fail  { color: #dc3545; font-weight: 600; }
        .moyenne-cell { background-color: #f0f4ff; font-weight: 700; text-align: center; }
        thead th { vertical-align: middle; text-align: center; }
    </style>
</head>
<body>
<div class="container mt-5 mb-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h1 class="mb-0">ETU003606</h1>
            <p class="text-muted mb-0">Notes finales de tous les candidats par matière</p>
        </div>
        <div>
            <a href="<%= request.getContextPath() %>/notes/final" class="btn btn-outline-primary me-2">
                Calculer une note
            </a>
            <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">
                Retour à l'accueil
            </a>
        </div>
    </div>

    <%
    if (noteFinalCandidats == null || noteFinalCandidats.isEmpty()) {
    %>
        <div class="alert alert-warning">
            Aucune note finale disponible. Vérifiez que des notes et des paramètres ont été saisis.
        </div>
    <%
    } else {
        // Retrieve matiere list from first candidate (all have the same matieres)
        List<NoteFinal> matiereRef = noteFinalCandidats.get(0).getNotesFinales();
    %>
    <div class="card shadow-sm">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-bordered table-hover mb-0">
                    <thead class="table-dark">
                        <tr>
                            <th style="min-width:160px;">Candidat</th>
                            <th style="min-width:80px;">Matricule</th>
                            <% for (NoteFinal nf : matiereRef) { %>
                                <th><%= nf.getMatiere().getNom() %>
                                    <br><small class="fw-normal text-warning">coeff&nbsp;<%= nf.getMatiere().getCoefficient() %></small>
                                </th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    for (NoteFinalCandidat nfc : noteFinalCandidats) {
                        Candidat c = nfc.getCandidat();
                        List<NoteFinal> notes = nfc.getNotesFinales();                        
                    %>
                        <tr>
                            <td class="align-middle fw-semibold">
                                <%= c.getNom() %> <%= c.getPrenom() %>
                            </td>
                            <td class="align-middle text-center text-muted">
                                <code><%= c.getMatricule() %></code>
                            </td>
                            <%
                            for (NoteFinal nf : notes) {
                                double note = nf.getNote();
                                String colorClass = (note >= 10.0) ? "note-pass" : "note-fail";
                            %>
                                <td class="note-cell align-middle">
                                    <span class="<%= colorClass %>">
                                        <%= String.format("%.2f", note) %>
                                    </span>
                                </td>
                            <% } %>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <% } %>
</div>
</body>
</html>