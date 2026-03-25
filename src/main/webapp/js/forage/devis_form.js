function fetchDemandeInfo(id) {
    const infoTableContainer = document.getElementById('infoTableContainer');
    if (!id) {
        infoTableContainer.style.display = 'none';
        return;
    }
    
    fetch(`${window.contextPath}/forage/demande/api/${id}`)
        .then(response => {
            if (!response.ok) throw new Error('Demande introuvable');
            return response.json();
        })
        .then(data => {
            if (data && data.clientNom) {
                document.getElementById('cellClientNom').textContent = data.clientNom;
                document.getElementById('cellStatut').textContent = data.statut;
                document.getElementById('cellDateDemande').textContent = data.dateDemande;
                document.getElementById('cellLieuDistrict').textContent = `${data.lieu} / ${data.district}`;
                document.getElementById('cellDescription').textContent = data.description;
                infoTableContainer.style.display = 'block';
            } else {
                infoTableContainer.style.display = 'none';
            }
        })
        .catch(error => {
            console.error('Error fetching demande info:', error);
            infoTableContainer.style.display = 'none';
        });
}

function updateCalculations(input) {
    const row = input.closest('tr');
    const prix = parseFloat(row.querySelector('input[name="prix[]"]').value) || 0;
    const qte = parseFloat(row.querySelector('input[name="qte[]"]').value) || 0;
    const totalLine = prix * qte;
    
    row.querySelector('.line-total').textContent = totalLine.toLocaleString('fr-FR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) + ' Ar';
    
    calculateTotal();
}

function calculateTotal() {
    const rows = document.querySelectorAll('#detailsTable tbody tr');
    let grandTotal = 0;
    
    rows.forEach(row => {
        const prix = parseFloat(row.querySelector('input[name="prix[]"]').value) || 0;
        const qte = parseFloat(row.querySelector('input[name="qte[]"]').value) || 0;
        grandTotal += prix * qte;
    });
    
    const totalDisplay = document.getElementById('totalPriceDisplay');
    if (totalDisplay) {
        totalDisplay.textContent = grandTotal.toLocaleString('fr-FR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) + ' Ar';
    }
}

function addRow() {
    const table = document.getElementById('detailsTable').getElementsByTagName('tbody')[0];
    const newRow = table.insertRow();
    
    const cell1 = newRow.insertCell(0);
    const cell2 = newRow.insertCell(1);
    const cell3 = newRow.insertCell(2);
    const cell4 = newRow.insertCell(3);
    const cell5 = newRow.insertCell(4);
    
    cell1.innerHTML = '<input type="text" name="libelle[]" required placeholder="Ex: Main d\'œuvre">';
    cell2.innerHTML = '<input type="number" step="0.01" name="prix[]" required placeholder="0.00" oninput="updateCalculations(this)" style="text-align: right;">';
    cell3.innerHTML = '<input type="number" step="0.01" name="qte[]" required placeholder="0.00" oninput="updateCalculations(this)" style="text-align: right;">';
    cell4.className = 'line-total';
    cell4.style.textAlign = 'right';
    cell4.style.fontWeight = '700';
    cell4.style.color = 'var(--text-main)';
    cell4.textContent = '0.00 Ar';
    cell5.style.textAlign = 'center';
    cell5.innerHTML = '<button type="button" class="action-link delete" onclick="removeRow(this)" style="border: none; background: none; cursor: pointer; font-size: 1.2rem;">&times;</button>';
    
    calculateTotal();
}

function removeRow(btn) {
    const row = btn.parentNode.parentNode;
    row.parentNode.removeChild(row);
    calculateTotal();
}
