 document.addEventListener('DOMContentLoaded', function() {
            const API_BASE_URL = 'http://localhost:8080/api';
            
            const espacoSelect = document.getElementById('espacoSelect');
            const historicoContainer = document.getElementById('historicoContainer');
            const loadingIndicator = document.getElementById('loading');
            const errorMessage = document.getElementById('errorMessage');
            
            let solicitacoes = [];
            let espacos = [];
            
            init();
            
            async function init() {
                await carregarEspacos();
                setupEventListeners();
            }
            
            async function carregarEspacos() {
                try {
                    showLoading(true);
                    const response = await fetch(`${API_BASE_URL}/espacos`);
                    
                    if (!response.ok) {
                        throw new Error('Erro ao carregar espaços');
                    }
                    
                    espacos = await response.json();
                    preencherSelectEspacos();
                    
                } catch (error) {
                    showError('Erro ao carregar espaços: ' + error.message);
                } finally {
                    showLoading(false);
                }
            }
            
            function preencherSelectEspacos() {
                espacoSelect.innerHTML = '<option value="">Selecione um espaço</option>';
                
                espacos.forEach(espaco => {
                    const option = document.createElement('option');
                    option.value = espaco.id;
                    option.textContent = espaco.nome;
                    espacoSelect.appendChild(option);
                });
            }
            
            async function carregarHistoricoPorEspaco(espacoNome) {
                try {
                    showLoading(true);
                    showError('');
                    
                    const response = await fetch(`${API_BASE_URL}/solicitacoes/historico-por-espaco/${encodeURIComponent(espacoNome)}`);
                    
                    if (!response.ok) {
                        throw new Error('Erro ao carregar histórico');
                    }
                    
                    const historico = await response.json();
                    solicitacoes = historico.solicitacoes || [];
                    exibirHistorico(solicitacoes);
                    
                } catch (error) {
                    showError('Erro ao carregar histórico: ' + error.message);
                    solicitacoes = [];
                    exibirHistorico([]);
                } finally {
                    showLoading(false);
                }
            }
            
            function exibirHistorico(solicitacoesFiltradas) {
                if (!solicitacoesFiltradas || solicitacoesFiltradas.length === 0) {
                    historicoContainer.innerHTML = `
                        <div class="no-data">
                            <p>Nenhuma solicitação encontrada para este espaço.</p>
                        </div>
                    `;
                    return;
                }
                
                solicitacoesFiltradas.sort((a, b) => new Date(b.dataSolicitacao) - new Date(a.dataSolicitacao));
                
                const html = solicitacoesFiltradas.map(solicitacao => `
                    <div class="solicitacao-card ${solicitacao.status.toLowerCase()}">
                        <div class="solicitacao-header">
                            <h3>Solicitação #${solicitacao.id}</h3>
                            <span class="status-badge ${solicitacao.status.toLowerCase()}">
                                ${getStatusText(solicitacao.status)}
                            </span>
                        </div>
                        
                        <div class="solicitacao-info">
                            <div class="info-row">
                                <strong>Solicitante:</strong> ${solicitacao.solicitanteNome}
                            </div>
                            
                            <div class="info-row">
                                <strong>Data da Reserva:</strong> ${formatarData(solicitacao.dataReserva)}
                            </div>
                            
                            <div class="info-row">
                                <strong>Horário:</strong> ${formatarHora(solicitacao.horaInicio)} às ${formatarHora(solicitacao.horaFim)}
                            </div>
                            
                            ${solicitacao.equipamentosNomes && solicitacao.equipamentosNomes.length > 0 ? `
                                <div class="info-row">
                                    <strong>Equipamentos:</strong> ${solicitacao.equipamentosNomes.join(', ')}
                                </div>
                            ` : ''}
                        </div>
                        
                        <div class="solicitacao-actions">
                            <button class="btn-secondary" onclick="verDetalhes(${solicitacao.id})">
                                Ver Detalhes
                            </button>
                        </div>
                    </div>
                `).join('');
                
                historicoContainer.innerHTML = html;
            }
            
            function setupEventListeners() {
                espacoSelect.addEventListener('change', function() {
                    const espacoNome = this.options[this.selectedIndex].text;
                    if (this.value) {
                        carregarHistoricoPorEspaco(espacoNome);
                    } else {
                        historicoContainer.innerHTML = '';
                        solicitacoes = [];
                    }
                });
            }
            
            function formatarData(data) {
                if (!data) return '-';
                return new Date(data + 'T00:00:00').toLocaleDateString('pt-BR');
            }
            
            function formatarHora(hora) {
                if (!hora) return '-';
                return hora.substring(0, 5);
            }
            
            function getStatusText(status) {
                const statusMap = {
                    'PENDENTE': 'Pendente',
                    'APROVADA': 'Aprovada',
                    'REJEITADA': 'Rejeitada',
                    'CANCELADA': 'Cancelada'
                };
                return statusMap[status] || status;
            }
            
            function showLoading(show) {
                if (loadingIndicator) {
                    loadingIndicator.style.display = show ? 'block' : 'none';
                }
            }
            
            function showError(message) {
                if (errorMessage) {
                    errorMessage.textContent = message;
                    errorMessage.style.display = message ? 'block' : 'none';
                }
            }
            
            window.verDetalhes = function(solicitacaoId) {
                alert(`Ver detalhes da solicitação #${solicitacaoId}`);
            };
            
            window.exportarCSV = function() {
                if (!solicitacoes || solicitacoes.length === 0) {
                    alert('Nenhuma solicitação para exportar');
                    return;
                }
                
                const csvContent = gerarCSV(solicitacoes);
                const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
                const link = document.createElement('a');
                const url = URL.createObjectURL(blob);
                
                link.setAttribute('href', url);
                link.setAttribute('download', `historico_solicitacoes_${new Date().toISOString().split('T')[0]}.csv`);
                link.style.visibility = 'hidden';
                
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            };
            
            function gerarCSV(dados) {
                const headers = [
                    'ID',
                    'Solicitante',
                    'Data Reserva',
                    'Hora Início',
                    'Hora Fim',
                    'Status',
                    'Equipamentos'
                ];
                
                const rows = dados.map(s => [
                    s.id,
                    s.solicitanteNome,
                    formatarData(s.dataReserva),
                    formatarHora(s.horaInicio),
                    formatarHora(s.horaFim),
                    getStatusText(s.status),
                    s.equipamentosNomes ? s.equipamentosNomes.join('; ') : ''
                ]);
                
                const csvContent = [headers, ...rows]
                    .map(row => row.map(field => `"${field}"`).join(','))
                    .join('\n');
                
                return csvContent;
            }
        });