function togglePForm() { 
    const d = document.getElementById("pDrawer"); 
    if (d) d.style.display = d.style.display === "none" ? "block" : "none"; 
}

async function savePlan(e) { 
    e.preventDefault(); 
    try { 
        const planNameInput = document.getElementById("pName").value.trim();
        const goalInput = document.getElementById("pGoal").value.trim();
        const capInput = parseInt(document.getElementById("pCap").value);

        const activeUser = getUser();
        // 👇 Safe validation check
        if (!activeUser || !activeUser.id) {
            alert("Session expired. Redirecting to login...");
            window.location.href = "login.html";
            return;
        }

        const payload = { 
            planName: planNameInput, 
            targetGoal: goalInput, 
            capacity: capInput 
        };

        const targetUrl = `${API.WELLNESS.ALL}?creatorId=${activeUser.id}`;

        const r = await fetch(targetUrl, { 
            method: "POST", 
            headers: authHeaders(), 
            body: JSON.stringify(payload) 
        }); 

        if (!r.ok) {
            const errData = await r.json().catch(() => ({}));
            throw new Error(errData.message || `HTTP Error Status: ${r.status}`);
        }

        alert("Wellness Plan deployed successfully!"); 
        location.reload(); 
    } catch(err) { 
        alert("Plan Deployment Interrupted:\n" + err.message); 
    } 
}

// 👇 COMPLETELY SAFE DRAWER MATRIX ENGINE
if (window.guard.protect()) { 
    document.addEventListener("DOMContentLoaded", async () => { 
        // 1. Try to render the navbar safely
        try {
            window.store.renderUser(); 
            window.store.renderRole(); 
        } catch(err) {
            console.error("Navbar rendering delayed:", err);
        }
        
        // 2. Safe Role Check to show/hide the "+ Create Plan" button
        const u = getUser();
        const btn = document.getElementById("addPlanBtn");
        if (btn) {
            if (u && u.role !== "PRACTITIONER") {
                btn.style.display = "block"; 
            } else {
                btn.style.display = "none";
            }
        }

        // 3. Load the table entries safely
        try { 
            const r = await fetch(API.WELLNESS.ALL, { headers: authHeaders() }); 
            if (!r.ok) throw new Error(`Server returned status ${r.status}`);
            
            const data = await r.json(); 
            const tableBody = document.getElementById("wellTable");
            
            if (tableBody) {
                if (!data || data.length === 0) {
                    tableBody.innerHTML = `<tr><td colspan="4" class="text-center text-muted py-3">No active plans found.</td></tr>`;
                    return;
                }
                
                tableBody.innerHTML = data.map(p => `
                    <tr>
                        <td><b>${p.planName || 'Unnamed Plan'}</b></td>
                        <td>${p.targetGoal || 'General'}</td>
                        <td>${p.currentEnrollments || 0} / ${p.capacity || 50} Enrolled</td>
                        <td class="text-end"><button class="btn-action-outline">Enroll</button></td>
                    </tr>
                `).join(''); 
            }
        } catch(e) {
            const tableBody = document.getElementById("wellTable");
            if (tableBody) {
                tableBody.innerHTML = `<tr><td colspan="4" class="text-center text-danger py-3">Error loading plans from server: ${e.message}</td></tr>`;
            }
        } 
    }); 
}