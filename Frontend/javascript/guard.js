window.guard = {
    protect: () => {
        if (!getToken()) {
            window.location.href = "login.html";
            return false;
        }
        return true;
    }
};