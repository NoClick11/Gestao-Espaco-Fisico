const API_URL = "http://localhost:8080/api";


const form = document.getElementById("solicitacao-form");
const espacoSelect = document.getElementById("espaco");
const solicitanteSelect = document.getElementById("solicitante");
const equipamentosContainer = document.getElementById("equipamentos-lista");
const filtroEquipamentos = document.getElementById("filtro-equipamentos");
const contadorEquipamentos = document.getElementById("contador-equipamentos");

async function carregarOpcoes() {
    try {
        console.log("Carregando dados...");

        const [espacos, usuarios, equipamentos] = await Promise.all([
            fetch(`${API_URL}/espacos`).then(r => r.json()),
            fetch(`${API_URL}/usuarios`).then(r => r.json()),
            fetch(`${API_URL}/equipamentos`).then(r => r.json())
        ]);

        popularSelect(espacoSelect, espacos);
        popularSelect(solicitanteSelect, usuarios);
        popularEquipamentos(equipamentos);

    } catch (error) {
        console.error("Erro ao carregar opções:", error);
        alert("Erro ao carregar dados. Por favor, recarregue a página.");
    }
}

function popularSelect(selectElement, itens) {
    selectElement.innerHTML = '<option value="">Selecione...</option>';

    itens.forEach(item => {
        const option = document.createElement('option');
        option.value = item.id;
        option.textContent = item.nome;
        selectElement.appendChild(option);
    });
}

console.log("Populando equipamentos...");
function popularEquipamentos(equipamentos) {
    equipamentosContainer.innerHTML = '';

    equipamentos.sort((a, b) => a.nome.localeCompare(b.nome));

    equipamentos.forEach(eq => {
        const div = document.createElement('div');
        div.className = 'checkbox-item';

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.id = `equip-${eq.id}`;
        checkbox.value = eq.id;

        const label = document.createElement('label');
        label.htmlFor = `equip-${eq.id}`;
        label.textContent = eq.nome;

        div.appendChild(checkbox);
        div.appendChild(label);
        equipamentosContainer.appendChild(div);
    });

    atualizarContador();
}

filtroEquipamentos.addEventListener('input', function() {
    const termo = this.value.toLowerCase();
    document.querySelectorAll('.checkbox-item').forEach(item => {
        const texto = item.textContent.toLowerCase();
        item.style.display = texto.includes(termo) ? 'flex' : 'none';
    });
});

function atualizarContador() {
    const selecionados = document.querySelectorAll('#equipamentos-lista input:checked').length;
    contadorEquipamentos.textContent = `${selecionados} selecionado(s)`;
}

equipamentosContainer.addEventListener('change', function(event) {
    if (event.target.type === 'checkbox') {
        atualizarContador();
    }
});

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    try {
        const checkboxes = document.querySelectorAll('#equipamentos-lista input[type="checkbox"]:checked');
        const equipamentosIds = Array.from(checkboxes).map(cb => parseInt(cb.value));

        if (!espacoSelect.value || !solicitanteSelect.value || !document.getElementById("dataReserva").value) {
            throw new Error("Preencha todos os campos obrigatórios");
        }

        const payload = {
            espacoFisicoId: parseInt(espacoSelect.value),
            solicitanteId: parseInt(solicitanteSelect.value),
            dataReserva: document.getElementById("dataReserva").value,
            horaInicio: document.getElementById("horaInicio").value,
            horaFim: document.getElementById("horaFim").value,
            equipamentosIds: equipamentosIds
        };

        const resposta = await fetch(`${API_URL}/solicitacoes`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (!resposta.ok) {
            const erro = await resposta.json();
            throw new Error(erro.message || "Erro ao criar solicitação");
        }

        alert("Solicitação criada com sucesso!");
        form.reset();
        atualizarContador();

    } catch (erro) {
        console.error('Erro:', erro);
        alert(`Erro ao criar solicitação: ${erro.message}`);
    }
});

document.addEventListener('DOMContentLoaded', () => {
    carregarOpcoes();
});
