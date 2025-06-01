const API_URL = "http://localhost:8080/api/solicitacoes";

const table = document.getElementById("solicitacao-table");

async function carregarSolicitacoes() {
    const response = await fetch(API_URL);
    const data = await response.json();

    table.innerHTML = "";
    data.forEach(s => {
        const row = `
            <tr>
                <td>${s.id}</td>
                <td>${s.espacoFisico.nome}</td>
                <td>${s.solicitante.nome}</td>
                <td>${s.dataReserva}</td>
                <td>${s.status}</td>
                <td>
                    <button onclick="removerSolicitacao(${s.id})">Excluir</button>
                </td>
            </tr>
        `;
        table.innerHTML += row;
    });
}

async function removerSolicitacao(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    carregarSolicitacoes();
}

carregarSolicitacoes();
