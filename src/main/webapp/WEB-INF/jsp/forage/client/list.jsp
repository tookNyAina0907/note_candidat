<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

<main>
    <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom: 2rem;">
        <h2>Liste des Clients</h2>
        <a href="${pageContext.request.contextPath}/forage/client/form" class="btn">Nouveau Client</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Contact</th>
                <th style="text-align: right;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="client" items="${clients}">
                <tr>
                    <td>${client.nom}</td>
                    <td><span class="drilling-badge">${client.contact}</span></td>
                    <td style="text-align: right;">
                        <a href="${pageContext.request.contextPath}/forage/demande/dclient/${client.id}" class="action-view">Voir demande</a>
                        <a href="${pageContext.request.contextPath}/forage/client/edit/${client.id}" class="action-edit">Modifier</a>
                        <a href="${pageContext.request.contextPath}/forage/client/delete/${client.id}" class="action-delete" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce client ?');">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>