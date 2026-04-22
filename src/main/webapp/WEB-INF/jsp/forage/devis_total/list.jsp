<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

<main class="container">
        <div class="stats-grid">
            <a href="${pageContext.request.contextPath}/forage/chiffre-affaire" class="stat-card stat-card--blue">
                <div class="stat-accent accent--blue"></div>
                <span class="stat-label">Chiffre d'affaire</span>
                <span class="stat-value">
                <fmt:formatNumber value="${chiffre}" type="number" groupingUsed="true" /> Ar
                </span>
                <span class="stat-sub">Revenus totaux</span>
            </a>

            <a href="${pageContext.request.contextPath}/forage/client" class="stat-card stat-card--teal">
                <div class="stat-accent accent--teal"></div>
                <span class="stat-label">Nombre de clients</span>
                <span class="stat-value">${totalClients}</span>
                <span class="stat-sub">Clients enregistrés</span>
            </a>

            <a href="${pageContext.request.contextPath}/forage/demande" class="stat-card stat-card--amber">
                <div class="stat-accent accent--amber"></div>
                <span class="stat-label">Nombre de demandes</span>
                <span class="stat-value">${totalDemandes}</span>
                <span class="stat-sub">Demandes en cours</span>
            </a>

            <a href="${pageContext.request.contextPath}/forage/devis/list" class="stat-card stat-card--coral">
                <div class="stat-accent accent--coral"></div>
                <span class="stat-label">Nombre de devis</span>
                <span class="stat-value">${totalDevis}</span>
                <span class="stat-sub">Devis émis</span>
            </a>
        </div>
        <div class="stats-grid">
            <c:forEach var="statut" items="${statuts}">
                <a href="${pageContext.request.contextPath}/forage/demande/statut/${statut.id}" class="stat-card stat-card--gray">
                    <div class="stat-accent accent--gray"></div>
                    <span class="stat-label"> nombre de ${statut.nom}</span>
                    <span class="stat-value">${requestScope[statut.nom]}</span>
                    <span class="stat-sub"> ${statut.nom}</span>
                </a>
            </c:forEach>
        </div>
</main>