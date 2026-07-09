function toggleForm() { const d = document.getElementById("formDrawer"); d.style.display = d.style.display === "none" ? "block" : "none"; }

async function createSess(e) { 
    e.preventDefault(); 
    try { 
        const titleVal = document.getElementById("sTitle").value.trim();
        const catVal = document.getElementById("sCat").value; // Reads the strict uppercase Enum value selection
        const durVal = parseInt(document.getElementById("sDur").value);

        const payload = { 
            title: titleVal, 
            category: catVal, 
            durationSeconds: durVal * 60 
        };

        const r = await fetch(API.MEDITATION.ALL, { 
            method: "POST", 
            headers: authHeaders(), 
            body: JSON.stringify(payload) 
        }); 

        if (!r.ok) {
            const errData = await r.json().catch(() => ({}));
            throw new Error(errData.message || `Status ${r.status}`);
        }

        alert("Meditation session published successfully!"); 
        location.reload(); 
    } catch(err) { 
        alert("Failed to create session: " + err.message); 
    } 
}

if (window.guard.protect()) { 
    document.addEventListener("DOMContentLoaded", async () => { 
        window.store.renderUser(); 
        window.store.renderRole(); 
        
        const u = getUser(); 
        if(u.role !== "PRACTITIONER") document.getElementById("openSessionCreatorBtn").style.display="block"; 
        
        try { 
            const r = await fetch(API.MEDITATION.ALL, { headers: authHeaders() }); 
            const data = await r.json(); 
            
            document.getElementById("medTable").innerHTML = data.map(s => `
                <tr>
                    <td><b>${s.title}</b></td>
                    <td><span class="badge bg-light text-dark" style="border:1px solid #cbd5e1; text-transform: capitalize;">${(s.category || '').toLowerCase()}</span></td>
                    <td>${Math.round(s.durationSeconds/60)} mins</td>
                    <td class="text-end"><button class="btn-action-outline">Stream</button></td>
                </tr>
            `).join(''); 
        } catch(e){} 
    }); 
}