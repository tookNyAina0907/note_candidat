<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

    <main>
        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom: 2rem;">
            <h2>${client.id != null ? 'Modifier le Client' : 'Ajouter un Client'}</h2>
            <a href="${pageContext.request.contextPath}/forage/client" class="btn btn-secondary">Retour à la liste</a>
        </div>
        <form action="${pageContext.request.contextPath}/forage/client/add" method="post" class="modern-form">
            <input type="hidden" name="id" value="${client.id}" />
            
            <div class="form-group">
                <label for="nom">Nom</label>
                <input type="text" id="nom" name="nom" value="${client.nom}" placeholder="Nom du client" required>
            </div>

            <div class="form-group">
                <label for="contact">contact</label>
                <input type="text" id="contact" name="contact" value="${client.contact}" placeholder="00000000" required>
            </div>

            <button type="submit" class="btn">${client.id != null ? 'Enregistrer les modifications' : 'Créer le client'}</button>
        </form>
    </main>
    <jsp:include page="/WEB-INF/jsp/forage/layout/footer.jsp" />