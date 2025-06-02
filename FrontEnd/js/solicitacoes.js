document.addEventListener('DOMContentLoaded', function() {
    carregarSolicitacoes();
});

function carregarSolicitacoes() {
    fetch('http://localhost:8080/api/solicitacoes')
        .then(resposta => {
            if (!resposta.ok) throw new Error('Erro na resposta da API');
            return resposta.json();
        })
        .then(dados => {
            const tabela = document.getElementById('solicitacao-table');
            if (!tabela) {
                console.error('Elemento tbody com id "tabela-solicitacoes" não encontrado.');
                return;
            }
            console.log(dados);
            tabela.innerHTML = '';

            if (dados.length === 0) {
                const linha = document.createElement('tr');
                linha.innerHTML = `<td colspan="4">Nenhuma solicitação encontrada.</td>`;
                tabela.appendChild(linha);
            } else {
                dados.forEach(solicitacao => {
                    const linha = document.createElement('tr');
                    linha.innerHTML = `
                        <td>${solicitacao.id}</td>
                        <td>${solicitacao.espacoFisicoId }</td>
                        <td>${solicitacao.solicitanteId }</td>
                        <td>${solicitacao.dataReserva || 'Sem data'}</td>
                        <td>${solicitacao.status || ''}</td>
                        <td>${solicitacao.horaInicio || 'Sem data'}</td>
                        <td>${solicitacao.horaFim || 'Sem data'}</td>
                    `;
                    tabela.appendChild(linha);
                });
            }
        })
        .catch(erro => {
            console.error('Erro ao carregar as solicitações:', erro);
            const tabela = document.getElementById('tabela-solicitacoes');
            if (tabela) tabela.innerHTML = `<tr><td colspan="4">Erro ao carregar dados.</td></tr>`;
        });
}
