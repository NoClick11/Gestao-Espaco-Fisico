document.addEventListener('DOMContentLoaded', async () => {
    const usuariosSelect = document.getElementById('usuarios-select');
    const buscarBtn = document.getElementById('buscar-btn');
    const solicitacoesContainer = document.getElementById('solicitacoes-container');
    
    async function carregarUsuarios() {
        try {
            const response = await fetch('http://localhost:8080/api/usuarios');
            
            if (!response.ok) {
                throw new Error('Erro ao carregar usuários');
            }
            
            const usuarios = await response.json();
            
            usuariosSelect.innerHTML = '';
            usuariosSelect.innerHTML = '<option value="">Selecione um usuário</option>';
            
            usuarios.forEach(usuario => {
                const option = document.createElement('option');
                option.value = usuario.id;
                option.textContent = `${usuario.nome} (${usuario.email})`;
                usuariosSelect.appendChild(option);
            });
            
        } catch (error) {
            console.error('Erro:', error);
            usuariosSelect.innerHTML = '<option value="">Erro ao carregar usuários</option>';
        }
    }
    
   async function buscarSolicitacoes(usuarioId) {
    try {
        solicitacoesContainer.innerHTML = '<p>Carregando solicitações...</p>';
        
        const response = await fetch(`http://localhost:8080/api/solicitacoes/usuario/${usuarioId}`);
        
        if (!response.ok) {
            throw new Error('Erro ao buscar solicitações');
        }
        
        const solicitacoes = await response.json();
        exibirSolicitacoes(solicitacoes);
        
    } catch (error) {
        console.error('Erro:', error);
        solicitacoesContainer.innerHTML = '<p class="error">Ocorreu um erro ao buscar as solicitações</p>';
    }
}
    
    function exibirSolicitacoes(solicitacoes) {
        if (!solicitacoes || solicitacoes.length === 0) {
            solicitacoesContainer.innerHTML = '<p>Nenhuma solicitação encontrada para este usuário.</p>';
            return;
        }
        
        let html = `
            <h3>Solicitações encontradas: ${solicitacoes.length}</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Espaço Físico</th>
                        <th>Data Reserva</th>
                        <th>Hora Início</th>
                        <th>Hora Fim</th>
                        <th>Status</th>
                        <th>Equipamentos</th>
                    </tr>
                </thead>
                <tbody>
        `;
        
        solicitacoes.forEach(solicitacao => {
            html += `
                <tr>
                    <td>${solicitacao.id}</td>
                    <td>${solicitacao.espacoFisicoNome || 'N/A'}</td>
                    <td>${formatarData(solicitacao.dataReserva)}</td>
                    <td>${solicitacao.horaInicio || 'N/A'}</td>
                    <td>${solicitacao.horaFim || 'N/A'}</td>
                    <td class="status-${solicitacao.status.toLowerCase()}">${solicitacao.status}</td>
                    <td>${solicitacao.equipamentosNomes ? solicitacao.equipamentosNomes.join(', ') : 'Nenhum'}</td>
                </tr>
            `;
        });
        
        html += `
                </tbody>
            </table>
        `;
        
        solicitacoesContainer.innerHTML = html;
    }
    
    function formatarData(dataString) {
        if (!dataString) return 'N/A';
        
        try {
            const data = new Date(dataString);
            return data.toLocaleDateString('pt-BR');
        } catch {
            return dataString;
        }
    }
    
    buscarBtn.addEventListener('click', () => {
        const usuarioId = usuariosSelect.value;
        
        if (!usuarioId) {
            alert('Por favor, selecione um usuário');
            return;
        }
        
        buscarSolicitacoes(usuarioId);
    });
    
    carregarUsuarios();
});