async function login(e) {
    e.preventDefault();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    
    try {
        const res = await fetch(API.AUTH.LOGIN, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });
        
        const data = await res.json();
        if (!res.ok) throw new Error(data.message || "Invalid credentials.");
        
        localStorage.setItem("token", data.token);
        localStorage.setItem("user", JSON.stringify(data.user));
        window.location.href = "dashboard.html";
    } catch (err) { 
        alert("Login Connection Failed: " + err.message); 
    }
}

async function register(e) {
    e.preventDefault();
    
    // Extracted directly to match your backend RegisterDto naming criteria precisely
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const fullName = document.getElementById("name").value.trim();
    const selectedRole = document.getElementById("role").value; // e.g. "PRACTITIONER"

    try {
        const res = await fetch(API.AUTH.REGISTER, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ 
                email: email, 
                password: password, 
                fullName: fullName, 
                role: selectedRole // Sends string uppercase to bind safely to ZenUser.UserRole enum
            })
        });

        if (!res.ok) { 
            const errorResponse = await res.json().catch(() => ({}));
            throw new Error(errorResponse.message || `Server returned error status: ${res.status}`); 
        }
        
        alert("Registration complete! Your account is active.");
        window.location.href = "login.html";
    } catch (err) { 
        alert("Registration Failed: " + err.message + "\n\nTip: Ensure your backend Spring Boot application is online and @CrossOrigin is added to AuthController."); 
    }
}

function logout() { window.store.logout(); }