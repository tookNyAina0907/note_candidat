<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />


        <main class="container">
            <div style="margin-bottom: 2rem;">
                <h1 style="color: var(--primary-color); font-size: 2rem; margin-bottom: 0.5rem;">Historique des Statuts</h1>
            </div>
            <div class="premium-card">
                <div class="card-header" style="background: #f8fafc;">
                    <h3 style="margin:0; font-size: 1.1rem; color: var(--primary-dark);">Chronologie des projet</h3>
                    <span class="drilling-badge">${demandesStatut.size()} événements</span>
                </div>
                <div class="card-body" style="background: #fafafa; padding: 2.5rem;">
                    <c:choose>
                        <c:when test="${not empty demandesStatut}">
                            <div class="status-timeline">
                                <c:forEach var="ds" items="${demandesStatut}">
                                    <div class="timeline-item">
                                        <div class="timeline-content" style="width: 100%;">
                                            <form action="${pageContext.request.contextPath}/forage/demande/statut/edit"
                                                method="POST"
                                                style="background: white; padding: 1.2rem; border-radius: 12px; border: 1px solid #e2e8f0; box-shadow: 0 2px 4px rgba(0,0,0,0.02);">
                                                <input type="hidden" name="id" value="${ds.id}">
                                                <input type="hidden" name="demandeId" value="${demande.id}">

                                                <div
                                                    style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 1rem;">
                                                    <div>
                                                        <h4 style="margin: 0; color: var(--primary-dark); font-size: 1rem;">Projet #<strong>${ds.demande.id}</strong></h4>
                                                        <p style="margin: 0.2rem 0 0.5rem 0; color: #64748b; font-size: 0.85rem;">Client: <strong>${ds.demande.client.nom}</strong></p>
                                                        <span class="timeline-status"
                                                            style="display: inline-block; background: var(--primary-light); color: var(--primary-color); padding: 0.25rem 0.75rem; border-radius: 20px; font-size: 0.85rem; font-weight: 600;">
                                                            ${ds.statut.nom}
                                                        </span>
                                                    </div>
                                                    <a href="${pageContext.request.contextPath}/forage/demande/statut/form/${ds.demande.id}"
                                                        style="text-decoration: none; color: var(--primary-color); font-size: 0.85rem; font-weight: 600; display: flex; align-items: center; gap: 0.3rem;">
                                                        <span style="font-size: 1.1rem;">+</span> Statut
                                                    </a>
                                                </div>

                                                <div
                                                    style="display: grid; grid-template-columns: 1fr 2fr; gap: 1.5rem;">
                                                    <div>
                                                        <label
                                                            style="display: block; font-size: 0.75rem; color: #64748b; margin-bottom: 0.4rem; text-transform: uppercase; font-weight: 700; letter-spacing: 0.025em;">Date
                                                            et Heure</label>
                                                        <input type="datetime-local" name="date"
                                                            value="${ds.dateStatut.toString().substring(0, 16)}"
                                                            style="width: 100%; padding: 0.6rem; border: 1px solid #cbd5e1; border-radius: 8px; font-size: 0.9rem;">
                                                    </div>
                                                    <div>
                                                        <label
                                                            style="display: block; font-size: 0.75rem; color: #64748b; margin-bottom: 0.4rem; text-transform: uppercase; font-weight: 700; letter-spacing: 0.025em;">Observation</label>
                                                        <input type="text" name="observation" value="${ds.observation}"
                                                            placeholder="Aucune observation"
                                                            style="width: 100%; padding: 0.6rem; border: 1px solid #cbd5e1; border-radius: 8px; font-size: 0.9rem;">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div style="text-align: center; padding: 5rem 2rem;">
                                <div
                                    style="font-size: 4rem; margin-bottom: 1.5rem; filter: grayscale(1); opacity: 0.2;">
                                    🔍</div>
                                <h3 style="color: var(--text-muted);">Aucun statut trouvé</h3>
                                <p style="color: var(--text-muted);">Essayez de modifier votre plage de dates ou ajoutez
                                    un nouveau statut.</p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div style="margin-top: 2rem;">
                <a href="${pageContext.request.contextPath}/forage/demande"
                    style="color: var(--text-muted); text-decoration: none; font-weight: 600; display: flex; align-items: center; gap: 0.5rem;">
                    ← Retour à la liste des demandes
                </a>
            </div>
        </main>

        <jsp:include page="/WEB-INF/jsp/forage/layout/footer.jsp" />