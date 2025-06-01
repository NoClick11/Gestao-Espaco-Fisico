window.onload = async () => {
    try {
        const espacos = await fetchAPI('/espacos');
        const equipamentos = await fetchAPI('/equipamentos');
        const usuarios = await fetchAPI('/usuarios');
        const pendentes = await fetchAPI('/solicitacoes/pendentes/count');

        document.getElementById('espacos-count').textContent = espacos.length;
        document.getElementById('equipamentos-count').textContent = equipamentos.length;
        document.getElementById('usuarios-count').textContent = usuarios.length;
        document.getElementById('solicitacoes-pendentes').textContent = pendentes;
    } catch (e) {
        alert('Erro ao carregar dados');
    }
};
