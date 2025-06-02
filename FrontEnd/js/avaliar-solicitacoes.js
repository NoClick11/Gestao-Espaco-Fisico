const API_URL = 'http://localhost:8080/api/solicitacoes/pendentes';

async function carregarSolicitacoes() {
    try {
        const resposta = await fetch(API_URL);
            if(!resposta.ok) {
                console.log("deu erro");
            }
            

        const solicitacoes = await resposta.json();
        const tabela = document.getElementById('solicitacao-table');

        solicitacoes.forEach(solicitacao => {
            const linha = document.createElement('tr');

            linha.innerHTML = `
                <td>${solicitacao.id}</td>
                <td>${solicitacao.solicitanteNome}</td>
                <td>${solicitacao.espacoFisicoNome}</td>
                <td>${solicitacao.dataReserva}</td>
                <td>${solicitacao.horaInicio}</td>
                <td>${solicitacao.horaFim}</td>
                <td>${solicitacao.status}</td>
                <td>
                    <button onclick="atualizarStatus(${solicitacao.id}, 'APROVADA')">Aprovar</button>
                    <button onclick="atualizarStatus(${solicitacao.id}, 'REPROVADA')">Reprovar</button>
                </td>
            `;

            tabela.appendChild(linha);
        });
    } catch (erro) {
        console.error('Erro ao carregar as solicitações:', erro);
    }
}

async function atualizarStatus(id, novoStatus) {
    try {
        const resposta = await fetch(`http://localhost:8080/api/solicitacoes/${id}/status`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ status: novoStatus })
        });

        if (!resposta.ok) {
            throw new Error('Erro ao atualizar status');
        }

        alert(`Solicitação ${novoStatus.toLowerCase()} com sucesso!`);

        carregarSolicitacoes();

    } catch (erro) {
        console.error('Erro ao atualizar status:', erro);
        alert('Erro ao atualizar status.');
    }
}

carregarSolicitacoes();