<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

<main>
    <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom: 2rem;">
        <h2>Liste des Demandes de Forage</h2>
        <a href="${pageContext.request.contextPath}/forage/demande/form" class="btn">Nouvelle Demande</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Client</th>
                <th>District</th>
                <th>Lieu</th>
                <th>Date</th>
                <th>statut</th>
                <th style="text-align: right;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="demande" items="${demandes}">
                <tr>
                    <td><span class="drilling-badge">#${demande.id}</span></td>
                    <td><strong>${demande.client.nom}</strong></td>
                    <td>${demande.district}</td>
                    <td>${demande.lieu}</td>
                    <td>${demande.dateDemande}</td>
                    <c:if test="${not empty demande.demandeStatuts}">
                        <td>${demande.demandeStatuts[0].statut.nom}</td>
                    </c:if>
                    <td style="text-align: right;">
                        <a href="${pageContext.request.contextPath}/forage/demande/edit/${demande.id}" class="action-edit">Modifier</a>
                        <a href="${pageContext.request.contextPath}/forage/demande/delete/${demande.id}" class="action-delete" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette demande ?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>