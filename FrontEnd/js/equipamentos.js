const API_URL = "http://localhost:8080/api/equipamentos";

const form = document.getElementById("equipamento-form");
const table = document.getElementById("equipamento-table");

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const nome = document.getElementById("nome").value;

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome })
    });

    form.reset();
    carregarEquipamentos();
});

async function carregarEquipamentos() {
    const response = await fetch(API_URL);
    const data = await response.json();

    table.innerHTML = "";
    data.forEach(e => {
        const row = `
            <tr>
                <td>${e.id}</td>
                <td>${e.nome}</td>
                <td>
                    <button onclick="removerEquipamento(${e.id})">Excluir</button>
                </td>
            </tr>
        `;
        table.innerHTML += row;
    });
}

async function removerEquipamento(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    carregarEquipamentos();
}

carregarEquipamentos();
