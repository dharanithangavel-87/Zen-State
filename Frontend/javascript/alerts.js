function toggleAlertForm() { 
    const d = document.getElementById("alertDrawer"); 
    if (d) d.style.display = d.style.display === "none" ? "block" : "none"; 
}

async function broadcastAlert(e) {
    e.preventDefault();
    try {
        const msgText = document.getElementById("alertText").value.trim();

        // Standard object model variable for system notification alert
        const payload = {
            message: msgText
        };

        const r = await fetch(API.ALERTS.ALL, {
            method: "POST",
            headers: authHeaders(),
            body: JSON.stringify(payload)
        });

        if (!r.ok) {
            const errData = await r.json().catch(() => ({}));
            throw new Error(errData.message || `Server status response error: ${r.status}`);
        }

        alert("Global system alert broadcasted successfully!");
        location.reload();
    } catch(err) {
        alert("Broadcast Failed:\n" + err.message);
    }
}

if (window.guard.protect()) { 
    document.addEventListener("DOMContentLoaded", async () => { 
        try {
            window.store.renderUser(); 
            window.store.renderRole(); 
        } catch(err) {}
        
        // 👇 Unhide creation button ONLY if logged in user is the ZEN_MASTER
        const u = getUser();
        const btn = document.getElementById("openAlertCreatorBtn");
        if (btn && u && u.role === "ZEN_MASTER") {
            btn.style.display = "block";
        }
        
        const feedElement = document.getElementById("feed");

        try { 
            const r = await fetch(API.ALERTS.ALL, { headers: authHeaders() }); 
            if (!r.ok) throw new Error(`Status ${r.status}`);
            
            const data = await r.json(); 
            
            if (feedElement) {
                if (!data || data.length === 0) {
                    feedElement.innerHTML = `
                        <div class="panel-white text-center py-4 text-muted">
                            <p class="m-0">No active system broadcast bulletins online at this time.</p>
                        </div>`;
                    return;
                }
                
                feedElement.innerHTML = data.map(a => `
                    <div class="panel-white" style="border-left: 5px solid #dc3545; padding: 1.5rem; margin-bottom: 1rem;">
                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <span class="badge bg-danger text-uppercase" style="font-size: 0.75rem; letter-spacing: 0.5px;">Global Notice</span>
                        </div>
                        <h5 class="m-0" style="color: #212529; font-weight: 600;">${a.message || 'System Message'}</h5>
                    </div>
                `).join(''); 
            }
        } catch(e) { 
            if (feedElement) {
                feedElement.innerHTML = `
                    <div class="panel-white text-center py-4">
                        <p class="text-muted m-0">No corporate broadcast bulletins found on server feed.</p>
                    </div>`;
            }
        } 
    }); 
}