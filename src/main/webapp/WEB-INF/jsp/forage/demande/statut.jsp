<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

<main class="container">
    <div style="margin-bottom: 3rem;">
        <h1 style="color: var(--primary-color); font-size: 2.5rem; margin-bottom: 0.5rem; letter-spacing: -1px;">Nouveau Statut</h1>
        <p style="color: #64748b; font-size: 1.1rem;">Demande #<strong>${demande.id}</strong> - ${demande.client.nom}</p>
    </div>

    <div style="max-width: 800px; margin: 0 auto;">
        <div class="premium-card">
            <div class="card-header" style="background: linear-gradient(to right, #f8fafc, white);">
                <h3 style="margin:0; font-size: 1.2rem; color: var(--primary-dark);">Mettre à jour l'état</h3>
            </div>
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/forage/demande/statut/update" method="POST">
                    <input type="hidden" name="demandeId" value="${demande.id}">
                    
                    <div class="form-group">
                        <label for="statutId">Statut</label>
                        <select name="statutId" id="statutId" required>
                            <c:forEach var="s" items="${statuts}">
                                <option value="${s.id}">
                                    ${s.nom}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="observation">Observation / Description</label>
                        <textarea name="observation" id="observation" rows="6" placeholder="Ex: Forage terminé, attente signature..."></textarea>
                    </div>

                    <div style="margin-top: 2.5rem; display: flex; gap: 1rem;">
                        <button type="submit" class="btn-premium" style="flex: 2;">Enregistrer le statut</button>
                        <a href="${pageContext.request.contextPath}/forage/demande/statut/${demande.id}" class="btn-premium secondary" style="flex: 1;">Annuler</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/WEB-INF/jsp/forage/layout/footer.jsp" />
