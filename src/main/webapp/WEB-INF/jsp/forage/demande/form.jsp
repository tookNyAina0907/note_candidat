<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

    <main>
        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom: 2rem;">
            <h2>${demande.id !=null ? 'Modifier la Demande' : 'Nouvelle Demande de Forage'}</h2>
            <a href="${pageContext.request.contextPath}/demande" class="btn btn-secondary">Retour à la liste</a>
        </div>
        <form action="${pageContext.request.contextPath}/demande/add" method="post" class="modern-form">
            <input type="hidden" name="id" value="${demande.id}" />
            
            <div class="form-group">
                <label for="client">Sélectionner le Client</label>
                <select id="client" name="client.id" required>
                    <option value="" disabled selected>Choisir un client...</option>
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.id}" ${demande.client.id == client.id ? 'selected' : ''}>
                            ${client.nom}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="district">District</label>
                <input type="text" id="district" name="district" value="${demande.district}" placeholder="Ex: Analamanga" required>
            </div>

            <div class="form-group">
                <label for="lieu">Lieu / Commune</label>
                <input type="text" id="lieu" name="lieu" value="${demande.lieu}" placeholder="Ex: Antananarivo" required>
            </div>

            <div class="form-group">
                <label for="description">Description des travaux</label>
                <textarea id="description" name="description" rows="4" placeholder="Détails du projet de forage..." required>${demande.description}</textarea>
            </div>

            <button type="submit" class="btn">${demande.id != null ? 'Mettre à jour la demande' : 'Créer la demande'}</button>
        </form>
    </main>

<jsp:include page="/WEB-INF/jsp/forage/layout/footer.jsp" />