

function selecionarPerfil(tipo) {
  const perfilSelector = document.getElementById('perfil-selector');
  const perfilInfo = document.getElementById('perfil-info');
  const conteudoPrincipal = document.getElementById('conteudo-principal');
  const perfilTexto = document.getElementById('perfil-texto');
  
  perfilSelector.classList.add('hidden');
  
  perfilInfo.classList.remove('hidden');
  conteudoPrincipal.classList.remove('hidden');
  
  if (tipo === 'gestor') {
    const itensGestor = document.querySelectorAll('.gestor-only');
    const itensUsuario = document.querySelectorAll('.user-access');
    
    itensGestor.forEach(item => item.classList.remove('hidden'));
    itensUsuario.forEach(item => item.classList.remove('hidden'));
    
    perfilTexto.textContent = 'Você está logado como Gestor. Você tem acesso completo ao sistema.';
  } else {
    const itensUsuario = document.querySelectorAll('.user-access');
    const itensGestor = document.querySelectorAll('.gestor-only');
    
    itensUsuario.forEach(item => item.classList.remove('hidden'));
    itensGestor.forEach(item => item.classList.add('hidden'));
    
    perfilTexto.textContent = 'Você está logado como Usuário. Você pode criar novas solicitações.';
  }
  
  localStorage.setItem('perfilUsuario', tipo);
}

function alterarPerfil() {
  const perfilSelector = document.getElementById('perfil-selector');
  const perfilInfo = document.getElementById('perfil-info');
  const conteudoPrincipal = document.getElementById('conteudo-principal');
  
  perfilSelector.classList.remove('hidden');
  perfilInfo.classList.add('hidden');
  conteudoPrincipal.classList.add('hidden');
  
  const todosItens = document.querySelectorAll('.gestor-only, .user-access');
  todosItens.forEach(item => item.classList.add('hidden'));
  
  localStorage.removeItem('perfilUsuario');
}

function verificarPerfilUsuario() {
  const perfil = localStorage.getItem('perfilUsuario');
  
  if (!perfil) {
    alert('Por favor, selecione seu perfil de acesso primeiro.');
    window.location.href = 'index.html';
    return null;
  }
  
  return perfil;
}

function aplicarControlePerfil() {
  const perfil = localStorage.getItem('perfilUsuario');
  
  if (!perfil) {
    window.location.href = 'index.html';
    return;
  }
  
  if (perfil === 'gestor') {
    const itensGestor = document.querySelectorAll('.gestor-only');
    const itensUsuario = document.querySelectorAll('.user-access');
    
    itensGestor.forEach(item => item.classList.remove('hidden'));
    itensUsuario.forEach(item => item.classList.remove('hidden'));
    
    const conteudoGestor = document.querySelectorAll('#conteudo-gestor, .conteudo-gestor');
    conteudoGestor.forEach(item => item.classList.remove('hidden'));
    
  } else if (perfil === 'usuario') {
    const itensUsuario = document.querySelectorAll('.user-access');
    const itensGestor = document.querySelectorAll('.gestor-only');
    
    itensUsuario.forEach(item => item.classList.remove('hidden'));
    itensGestor.forEach(item => item.classList.add('hidden'));
    
    const conteudoGestor = document.querySelectorAll('#conteudo-gestor, .conteudo-gestor');
    conteudoGestor.forEach(item => item.classList.add('hidden'));
  }
}

function verificarPermissaoPagina(paginasPermitidas) {
  const perfil = localStorage.getItem('perfilUsuario');
  
  if (!perfil) {
    window.location.href = 'index.html';
    return false;
  }
  
  const paginaAtual = window.location.pathname.split('/').pop();
  
  if (perfil === 'gestor') {
    return true;
  }
  
  if (perfil === 'usuario' && paginasPermitidas.includes(paginaAtual)) {
    return true;
  }
  
  alert('Você não tem permissão para acessar esta página.');
  window.location.href = 'nova-solicitacao.html';
  return false;
}

function voltarParaIndex() {
  localStorage.removeItem('perfilUsuario');
  window.location.href = 'index.html';
}

function mostrarIndicadorPerfil() {
  const perfil = localStorage.getItem('perfilUsuario');
  const perfilTexto = document.getElementById('perfil-texto-nav');
  
  if (perfilTexto) {
    if (perfil === 'gestor') {
      perfilTexto.textContent = '👤 Logado como: Gestor';
    } else if (perfil === 'usuario') {
      perfilTexto.textContent = '👤 Logado como: Usuário';
    }
  }
}

window.addEventListener('load', function() {
  if (window.location.pathname.includes('index.html') || window.location.pathname === '/') {
    const perfilSalvo = localStorage.getItem('perfilUsuario');
    if (perfilSalvo) {
      selecionarPerfil(perfilSalvo);
    }
  } else {
    aplicarControlePerfil();
    mostrarIndicadorPerfil();
  }
});

function paginaApenasGestor() {
  const perfil = localStorage.getItem('perfilUsuario');
  
  if (perfil !== 'gestor') {
    alert('Esta página é restrita a gestores.');
    if (perfil === 'usuario') {
      window.location.href = 'nova-solicitacao.html';
    } else {
      window.location.href = 'index.html';
    }
  }
}

function paginaApenasUsuario() {
  const perfil = localStorage.getItem('perfilUsuario');
  
  if (perfil !== 'usuario') {
    alert('Esta página é para usuários do sistema.');
    if (perfil === 'gestor') {
      window.location.href = 'dashboard.html';
    } else {
      window.location.href = 'index.html';
    }
  }
}

function obterPerfilAtual() {
  return localStorage.getItem('perfilUsuario');
}

function isGestor() {
  return localStorage.getItem('perfilUsuario') === 'gestor';
}

function isUsuario() {
  return localStorage.getItem('perfilUsuario') === 'usuario';
}