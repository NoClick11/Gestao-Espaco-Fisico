const API_URL = "http://localhost:8080/api";

const form = document.getElementById("solicitacao-form");

async function carregarOpcoes() {
    const espacos = await fetch(`${API_URL}/espacos`).then(r => r.json());
    const usuarios = await fetch(`${API_URL}/usuarios`).then(r => r.json());

    const espacoSelect = document.getElementById("espaco");
    const solicitanteSelect = document.getElementById("solicitante");

    espacos.forEach(e => {
        espacoSelect.innerHTML += `<option value="${e.id}">${e.nome}</option>`;
    });

    usuarios.forEach(u => {
        solicitanteSelect.innerHTML += `<option value="${u.id}">${u.nome}</option>`;
    });
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const payload = {
        espacoFisico: { id: document.getElementById("espaco").value },
        solicitante: { id: document.getElementById("solicitante").value },
        dataReserva: document.getElementById("dataReserva").value,
        horaInicio: document.getElementById("horaInicio").value,
        horaFim: document.getElementById("horaFim").value,
        descricao: document.getElementById("descricao").value
    };

    await fetch(`${API_URL}/solicitacoes`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    alert("Solicitação criada com sucesso!");
    form.reset();
});

carregarOpcoes();
