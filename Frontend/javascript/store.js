window.store = {
    getUser: () => getUser(),
    getToken: () => getToken(),
    setUser: (u) => setUser(u),
    logout: () => {
        localStorage.clear();
        window.location.href = "login.html";
    },
    renderUser: () => {
        const u = getUser();
        const el = document.getElementById("usernameAndRole");
        if (el) {
            // 👇 Safe fallback if the user object isn't fully loaded yet
            el.innerText = u ? `${u.fullName || u.name || 'User'} (${u.role})` : "Loading User...";
        }
    },
    renderRole: () => {
        const u = getUser();
        const el = document.getElementById("userInitial");
        if (el) {
            // 👇 Safe fallback character
            el.innerText = u && u.fullName ? u.fullName.charAt(0) : 'W';
        }
    }
};