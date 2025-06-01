const API_BASE = "http://localhost:8080/api";

async function fetchAPI(endpoint, method = 'GET', data) {
    const options = {
        method,
        headers: {
            'Content-Type': 'application/json'
        }
    };
    if (data) {
        options.body = JSON.stringify(data);
    }
    const res = await fetch(`${API_BASE}${endpoint}`, options);
    if (!res.ok) throw new Error('Erro na API');
    if (res.status !== 204) return res.json();
}
