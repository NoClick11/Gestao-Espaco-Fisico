async function carregarEspacos() {
    const espacos = await fetchAPI('/espacos');
    const tbody = document.querySelector('#espacos-table tbody');
    tbody.innerHTML = '';
    espacos.forEach(e => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${e.id}</td>
            <td>${e.nome}</td>
            <td>${e.metragem}</td>
            <td>
                <button onclick="deletarEspaco(${e.id})">Deletar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function criarEspaco() {
    const nome = document.getElementById('nome').value;
    const metragem = parseFloat(document.getElementById('metragem').value);
    await fetchAPI('/espacos', 'POST', { nome, metragem });
    carregarEspacos();
}

async function deletarEspaco(id) {
    if (confirm('Deseja deletar?')) {
        await fetchAPI(`/espacos/${id}`, 'DELETE');
        carregarEspacos();
    }
}

window.onload = carregarEspacos;
