<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="layout/header.jsp" />
    
    <main class="hero">
        <div class="hero-content">
            <h2>Bienvenue sur Forage Pro</h2>
            <p class="hero-subtitle">Le système intelligent de gestion de vos projets de forage.</p>
            <div class="action-cards">
                <a href="${pageContext.request.contextPath}/forage/client" class="card">
                    <div class="card-icon">👥</div>
                    <h3>Gestion Clients</h3>
                    <p>Suivez et gérez votre base de données clients.</p>
                </a>
                <a href="${pageContext.request.contextPath}/forage/demande" class="card">
                    <div class="card-icon">🏗️</div>
                    <h3>Demandes Forage</h3>
                    <p>Enregistrez et planifiez les nouvelles demandes.</p>
                </a>
            </div>
        </div>
    </main>

    <jsp:include page="layout/footer.jsp" />