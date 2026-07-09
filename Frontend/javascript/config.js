const API_BASE_URL = "http://localhost:8080/api";
const API = {
    AUTH: {
        LOGIN: `${API_BASE_URL}/auth/login`,
        REGISTER: `${API_BASE_URL}/auth/register`
    },
    USER: {
        PROFILE: `${API_BASE_URL}/users/profile`,
        UPDATE: `${API_BASE_URL}/users/update`
    },
    MEDITATION: {
        ALL: `${API_BASE_URL}/sessions`
    },
    WELLNESS: {
        ALL: `${API_BASE_URL}/plans`
    },
    ALERTS: {
        ALL: `${API_BASE_URL}/alerts`
    }
};

function getToken() { return localStorage.getItem("token"); }
function getUser() { const u = localStorage.getItem("user"); return u ? JSON.parse(u) : null; }
function setUser(u) { localStorage.setItem("user", JSON.stringify(u)); }
function authHeaders() { return { "Content-Type": "application/json", "Authorization": `Bearer ${getToken()}` }; }