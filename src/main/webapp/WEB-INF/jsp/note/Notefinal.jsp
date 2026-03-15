<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.List" %>
        <%@ page import="com.example.model.Candidat" %>
            <%@ page import="com.example.model.Matiere" %>
                <% List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");
                        List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
                                Double finalNote = (Double) request.getAttribute("finalNote");
                                String error = (String) request.getAttribute("error");
                                %>
                                <!DOCTYPE html>
                                <html lang="fr">

                                <head>
                                    <meta charset="UTF-8">
                                    <title>Saisie Note Finale</title>
                                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
                                        rel="stylesheet">
                                    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
                                </head>

                                <body>
                                    <div class="container mt-5">
                                        <h2 class="mb-4">Calcul de la Note Finale et ETU003606</h2>
                                        <p class="text-muted">Sélectionnez un candidat et une matière pour calculer la
                                            note finale en fonction des notes des correcteurs et des paramètres définis.
                                        </p>

                                        <% if (error !=null) { %>
                                            <div class="alert alert-danger" role="alert">
                                                <%= error %>
                                            </div>
                                            <% } %>

                                                <div class="card shadow-sm mb-4">
                                                    <div class="card-body">
                                                        <form
                                                            action="<%= request.getContextPath() %>/notes/final/compute"
                                                            method="post">
                                                            <div class="row g-3">
                                                                <div class="col-md-5">
                                                                    <label for="candidat"
                                                                        class="form-label">Candidat</label>
                                                                    <select class="form-select" id="candidat"
                                                                        name="candidatId" required>
                                                                        <option value="">Sélectionnez un candidat
                                                                        </option>
                                                                        <% if (candidats !=null) { for (Candidat c :
                                                                            candidats) { %>
                                                                            <option value="<%= c.getId() %>">
                                                                                <%= c.getNom() %>
                                                                                    <%= c.getPrenom() %> (<%=
                                                                                            c.getMatricule() %>)
                                                                            </option>
                                                                            <% } } %>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-5">
                                                                    <label for="matiere"
                                                                        class="form-label">Matière</label>
                                                                    <select class="form-select" id="matiere"
                                                                        name="matiereId" required>
                                                                        <option value="">Sélectionnez une matière
                                                                        </option>
                                                                        <% if (matieres !=null) { for (Matiere m :
                                                                            matieres) { %>
                                                                            <option value="<%= m.getId() %>">
                                                                                <%= m.getNom() %> (Coeff: <%=
                                                                                        m.getCoefficient() %>)
                                                                            </option>
                                                                            <% } } %>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-2 d-flex align-items-end">
                                                                    <button type="submit"
                                                                        class="btn btn-primary w-100">Calculer</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>

                                                <% if (finalNote !=null) { %>
                                                    <div class="card border-success shadow">
                                                        <div class="card-body text-center">
                                                            <h3 class="card-title text-success mb-3">Résultat du Calcul
                                                            </h3>
                                                            <div class="display-4 fw-bold text-success">
                                                                <%= String.format("%.2f", finalNote) %>
                                                            </div>
                                                            <p class="card-text mt-3 text-muted">Note finale après
                                                                application des règles et résolutions.</p>
                                                        </div>
                                                    </div>
                                                    <% } %>

                                                        <div class="mt-4">
                                                            <a href="<%= request.getContextPath() %>/"
                                                                class="btn btn-secondary">Retour à l'accueil</a>
                                                        </div>
                                    </div>
                                </body>

                                </html>