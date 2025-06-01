const API_URL = "http://localhost:8080/api/usuarios";

const form = document.getElementById("usuario-form");
const table = document.getElementById("usuario-table");

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const nome = document.getElementById("nome").value;
    const email = document.getElementById("email").value;
    const tipoUsuario = document.getElementById("tipoUsuario").value;

    await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome, email, tipoUsuario })
    });

    form.reset();
    carregarUsuarios();
});

async function carregarUsuarios() {
    const response = await fetch(API_URL);
    const data = await response.json();

    table.innerHTML = "";
    data.forEach(u => {
        const row = `
            <tr>
                <td>${u.id}</td>
                <td>${u.nome}</td>
                <td>${u.email}</td>
                <td>${u.tipoUsuario}</td>
                <td>
                    <button onclick="removerUsuario(${u.id})">Excluir</button>
                </td>
            </tr>
        `;
        table.innerHTML += row;
    });
}

async function removerUsuario(id) {
    await fetch(`${API_URL}/${id}`, { method: "DELETE" });
    carregarUsuarios();
}

carregarUsuarios();
