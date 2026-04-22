<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<main class="container">
    <div style="margin-bottom: 2.5rem; display: flex; justify-content: space-between; align-items: center;">
            <h1 style="color: var(--primary-color); font-size: 2rem; margin-bottom: 0.5rem;">Liste des Devis</h1>
            <p style="color: #64748b;">Demande #${demande.id} &bull; ${demande.client.nom}</p>
            <c:choose>
                <c:when test="${not empty demande.demandeStatuts}">
                    <p style="color: #64748b;">${demande.demandeStatuts[0].statut.nom} &bull; statut</p>
                </c:when>
            </c:choose>
        </div>
        <a href="${pageContext.request.contextPath}/forage/devis/form?demandeId=${demande.id}" class="btn-premium">+ Nouveau Devis</a>
    </div>

    <c:choose>
        <c:when test="${empty devisList}">
            <div class="premium-card" style="padding: 4rem; text-align: center;">
                <p style="color: var(--text-muted); font-size: 1.125rem; margin-bottom: 1.5rem;">Aucun devis n'a été créé pour cette demande.</p>
                <a href="${pageContext.request.contextPath}/forage/devis/form?demandeId=${demande.id}" class="btn-premium">Créer le premier devis</a>
            </div>
        </c:when>
    </c:choose>
    <c:forEach var="devis" items="${devisList}">
                <div class="premium-card" style="margin-bottom: 2.5rem;">
                    <div class="card-header">
                        <div>
                            <span class="drilling-badge">#${devis.id}</span>
                            <span style="margin-left: 0.75rem; font-weight: 700; color: var(--primary-color); font-size: 1.1rem;">${devis.typeDevis.nom}</span>
                        </div>
                        <div style="color: var(--text-muted); font-size: 0.875rem; font-weight: 500;">
                            Date: <fmt:parseDate value="${devis.dateDevis}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                            <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy HH:mm" />
                        </div>
                    </div>
                    <div class="card-body">
                        
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>Libellé</th>
                                    <th style="text-align: right;">Prix Unitaire</th>
                                    <th style="text-align: right;">Quantité</th>
                                    <th style="text-align: right;">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="totalDevis" value="0" />
                                <c:forEach var="detail" items="${devis.detailDevis}">
                                    <tr>
                                        <td>${detail.libelle}</td>
                                        <td style="text-align: right;">
                                            <fmt:formatNumber value="${detail.prix}" type="number" minFractionDigits="2" maxFractionDigits="2" /> Ar
                                        </td>
                                        <td style="text-align: right;">${detail.quantite}</td>
                                        <td style="text-align: right; font-weight: 700; color: var(--text-main);">
                                            <fmt:formatNumber value="${detail.prix * detail.quantite}" type="number" minFractionDigits="2" maxFractionDigits="2" /> Ar
                                        </td>
                                    </tr>
                                    <c:set var="totalDevis" value="${totalDevis + (detail.prix * detail.quantite)}" />
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr style="background: var(--primary-light);">
                                    <td colspan="3" style="text-align: right; padding: 1.5rem; font-weight: 700; color: var(--primary-dark); text-transform: uppercase; letter-spacing: 0.05em;">Montant Total du Devis :</td>
                                    <td style="text-align: right; padding: 1.5rem; color: var(--primary-color); font-size: 1.5rem; font-weight: 900;">
                                        <fmt:formatNumber value="${totalDevis}" type="number" minFractionDigits="2" maxFractionDigits="2" /> Ar
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </c:forEach>

    <div style="margin-top: 2rem;">
        <a href="${pageContext.request.contextPath}/forage/demande" class="btn-premium secondary">&larr; Retour aux demandes</a>
    </div>
</main>

<jsp:include page="/WEB-INF/jsp/forage/layout/footer.jsp" />
