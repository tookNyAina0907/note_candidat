<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <jsp:include page="/WEB-INF/jsp/forage/layout/header.jsp" />

        <main class="container">
    <div style="margin-bottom: 2rem;">
        <h1 style="color: var(--primary-color); font-size: 2rem; margin-bottom: 0.5rem;">Nouveau Devis</h1>
        <p style="color: var(--text-muted);">Créez un devis détaillé pour une demande de forage</p>
    </div>

    <form action="${pageContext.request.contextPath}/forage/devis/add" method="POST">
        <div class="premium-card" style="padding: 2rem; margin-bottom: 2rem;">
            <div class="form-title">Informations Générales</div>
            <div class="form-group">
                <label for="demandeId">ID de la Demande</label>
                <input type="number" id="demandeId" name="demandeId" value="${prefilledDemandeId}" required placeholder="Saisir l'ID de la demande (ex: 1)" oninput="fetchDemandeInfo(this.value)">
            </div>

            <script>
                document.addEventListener('DOMContentLoaded', function() {
                    const id = document.getElementById('demandeId').value;
                    if (id) fetchDemandeInfo(id);
                });
            </script>

            <div id="infoTableContainer" style="display: none; margin-top: 1.5rem; background: var(--bg-color); padding: 1.5rem; border-radius: 8px; border: 1px solid var(--border-color);">
                <div style="font-weight: 700; color: var(--primary-color); margin-bottom: 1rem; font-size: 0.95rem; text-transform: uppercase; letter-spacing: 0.05em;">Détails de la Demande</div>
                <div class="info-grid">
                    <div class="info-item">
                        <span class="info-label">Client</span>
                        <span class="info-value" id="cellClientNom"></span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Statut Actuel</span>
                        <span class="info-value" id="cellStatut"></span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Date</span>
                        <span class="info-value" id="cellDateDemande"></span>
                    </div>
                    <div class="info-item">
                        <span class="info-label">Localisation</span>
                        <span class="info-value" id="cellLieuDistrict"></span>
                    </div>
                </div>
                <div class="info-item">
                    <span class="info-label">Description</span>
                    <span class="info-value" id="cellDescription" style="font-weight: 400; color: var(--text-muted);"></span>
                </div>
            </div>

            <div class="form-group" style="margin-top: 1.5rem;">
                <label for="typeDevisId">Type de Devis</label>
                <select id="typeDevisId" name="typeDevisId" required>
                    <option value="">-- Sélectionner le type de devis --</option>
                    <c:forEach var="type" items="${typeDevisList}">
                        <option value="${type.id}">${type.nom}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="premium-card">
            <div class="card-header">
                <div class="form-title" style="margin-bottom: 0;">Détails du Devis</div>
                <button type="button" class="btn-premium secondary" onclick="addRow()" style="font-size: 0.875rem;">+ Ajouter une ligne</button>
            </div>
            <div class="card-body" style="padding: 0;">
                <table class="data-table" id="detailsTable">
                    <thead>
                        <tr>
                            <th>Libellé</th>
                            <th style="width: 20%; text-align: right;">Prix Unitaire (Ar)</th>
                            <th style="width: 15%; text-align: right;">Quantité</th>
                            <th style="width: 20%; text-align: right;">Total</th>
                            <th style="width: 80px; text-align: center;"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" name="libelle[]" required placeholder="Ex: Tuyau PVC, Main d'œuvre..."></td>
                            <td><input type="number" step="0.01" name="prix[]" required placeholder="0.00" oninput="updateCalculations(this)" style="text-align: right;"></td>
                            <td><input type="number" step="0.01" name="qte[]" required placeholder="0" oninput="updateCalculations(this)" style="text-align: right;"></td>
                            <td class="line-total" style="text-align: right; font-weight: 700; color: var(--text-main);">0.00 Ar</td>
                            <td style="text-align: center;"></td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr style="background: var(--primary-light);">
                            <td colspan="3" style="text-align: right; padding: 1.5rem; font-weight: 700; color: var(--primary-dark); text-transform: uppercase; letter-spacing: 0.05em;">Montant Total Estimé :</td>
                            <td id="totalPriceDisplay" style="text-align: right; padding: 1.5rem; color: var(--primary-color); font-size: 1.5rem; font-weight: 900;">0.00 Ar</td>
                            <td></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>

        <div style="margin-top: 2.5rem; display: flex; gap: 1.5rem; justify-content: flex-end;">
            <a href="${pageContext.request.contextPath}/forage/demande" class="btn-premium secondary" style="background: transparent; color: var(--text-muted); border: 1px solid var(--border-color);">Annuler</a>
            <button type="submit" class="btn-premium">Enregistrer le Devis</button>
        </div>
    </form>
</main>

        <script>
            window.contextPath = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/forage/devis_form.js"></script>

        <jsp:include page="/WEB-INF/jsp/forage/layout/footer.jsp" />