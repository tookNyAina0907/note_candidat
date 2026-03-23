<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <jsp:include page="layout/header.jsp" />
    
    <main class="hero">
        <div class="hero-content">
            <h2>Bienvenue sur Forage Pro</h2>
            <p class="hero-subtitle">Le système intelligent de gestion de vos projets de forage.</p>
            <div class="stats-container">
                <div class="stat-item">
                    <span class="stat-number">24/7</span>
                    <span class="stat-label">Support Technique</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number">100%</span>
                    <span class="stat-label">Suivi en temps réel</span>
                </div>
            </div>
            <div class="action-cards">
                <a href="${pageContext.request.contextPath}/client" class="card">
                    <div class="card-icon">👥</div>
                    <h3>Gestion Clients</h3>
                    <p>Suivez et gérez votre base de données clients.</p>
                </a>
                <a href="${pageContext.request.contextPath}/demande" class="card">
                    <div class="card-icon">🏗️</div>
                    <h3>Demandes Forage</h3>
                    <p>Enregistrez et planifiez les nouvelles demandes.</p>
                </a>
            </div>
        </div>
    </main>

    <jsp:include page="layout/footer.jsp" />