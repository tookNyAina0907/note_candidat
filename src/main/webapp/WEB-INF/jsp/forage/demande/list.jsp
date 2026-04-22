<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

<main class="container">
    <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom: 2.5rem;">
        <div>
            <h1 style="color: var(--primary-color); font-size: 2rem; margin-bottom: 0.5rem;">Demandes de Forage</h1>
            <p style="color: #64748b;">Gérez vos demandes et projets de forage</p>
        </div>
        <a href="${pageContext.request.contextPath}/forage/demande/form" class="btn-premium">+ Nouvelle Demande</a>
    </div>

    <div class="premium-card">
        <table class="data-table">
            <thead>
                <tr>
                    <th style="width: 80px;">ID</th>
                    <th>Client / Lieu</th>
                    <th>Date</th>
                    <th>Statut</th>
                    <th style="text-align: right;">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="demande" items="${demandes}">
                    <tr>
                        <td><span class="drilling-badge">#${demande.id}</span></td>
                        <td>
                            <div style="font-weight: 700; color: var(--text-main);">${demande.client.nom}</div>
                            <div style="font-size: 0.85rem; color: var(--text-muted);">${demande.lieu}, ${demande.district}</div>
                        </td>
                        <td style="color: var(--text-muted); font-size: 0.95rem;">${demande.dateDemande}</td>
                        <td>
                            <c:if test="${not empty demande.demandeStatuts}">
                                <span class="status-pill primary">${demande.demandeStatuts[0].statut.nom}</span>
                            </c:if>
                        </td>
                        <td style="text-align: right; white-space: nowrap;">
                            <a href="${pageContext.request.contextPath}/forage/demande/statut/${demande.id}" class="action-link" style="color: var(--warning-color);">Statut</a>
                            <a href="${pageContext.request.contextPath}/forage/devis/list/${demande.id}" class="action-link">Voir Devis</a>
                            <a href="${pageContext.request.contextPath}/forage/demande/edit/${demande.id}" class="action-link" style="margin-left: 1rem;">Modifier</a>
                            <a href="${pageContext.request.contextPath}/forage/demande/delete/${demande.id}" class="action-link delete" style="margin-left: 1rem;" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette demande ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</main>
