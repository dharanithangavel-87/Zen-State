if (window.guard.protect()) { 
    document.addEventListener("DOMContentLoaded", () => { 
        window.store.renderUser(); 
        window.store.renderRole(); 
        const u = window.store.getUser(); 
        const w = document.getElementById("dashboardViewWrapper"); 
        
        if (u.role === "ZEN_MASTER" || u.role === "WELLNESS_COACH") { 
            w.innerHTML = `
                <div class="mb-4"><h1>System Overview</h1><p class="text-muted">Manage platform growth parameters.</p></div>
                <div class="metrics-row">
                    <div class="metric-card"><span class="metric-label">Total Practitioners</span><span class="metric-value">1,240</span></div>
                    <div class="metric-card"><span class="metric-label">Active Sessions</span><span class="metric-value">45</span></div>
                    <div class="metric-card"><span class="metric-label">Revenue (MTD)</span><span class="metric-value">$12,450</span></div>
                </div>
                <div class="panel-white">
                    <h2>Welcome back, administrator.</h2>
                    <div class="quick-actions-bar">
                        <button class="btn-action-outline" onclick="location.href='meditation.html'">New Session</button>
                        <button class="btn-action-outline" onclick="location.href='alerts.html'">Broadcast Alert</button>
                    </div>
                </div>`; 
        } else { 
            w.innerHTML = `
                <div class="mb-4"><h1>My Practice Space</h1><p class="text-muted">Track your metrics overview metrics dynamically.</p></div>
                <div class="metrics-row">
                    <div class="metric-card"><span class="metric-label">Minutes Meditated</span><span class="metric-value">120</span></div>
                    <div class="metric-card"><span class="metric-label">Current Streak</span><span class="metric-value">4 Days</span></div>
                </div>
                <div class="panel-white"><h2>Welcome back to your inner peace space.</h2></div>`; 
        } 
    }); 
}