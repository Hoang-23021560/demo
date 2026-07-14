/* HoangSkyLand Admin JS */

// ── Accordion toggle ──────────────────────────────────────────────
function toggleAccordion(id) {
    const body = document.getElementById(id);
    const arrow = document.querySelector('[data-accordion="' + id + '"]');
    if (!body) return;
    const isOpen = !body.classList.contains('hidden');
    body.classList.toggle('hidden', isOpen);
    if (arrow) arrow.classList.toggle('open', !isOpen);
}

// ── Sidebar toggle (collapse / expand) ───────────────────────────
function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    const labels  = document.querySelectorAll('.sidebar-label');
    const logo    = document.getElementById('sidebar-logo');
    sidebar.classList.toggle('w-56');
    sidebar.classList.toggle('w-14');
    labels.forEach(el => el.classList.toggle('hidden'));
    if (logo) logo.classList.toggle('hidden');
}

// ── Dropdown user menu ────────────────────────────────────────────
function toggleDropdown(id) {
    const menu = document.getElementById(id);
    if (menu) menu.classList.toggle('hidden');
}

// Close dropdown when clicking outside
document.addEventListener('click', function (e) {
    const dd = document.getElementById('user-dropdown');
    const btn = document.getElementById('user-btn');
    if (dd && btn && !btn.contains(e.target) && !dd.contains(e.target)) {
        dd.classList.add('hidden');
    }
});

// ── Auto-hide flash messages after 4 s ───────────────────────────
document.addEventListener('DOMContentLoaded', function () {
    const flash = document.querySelectorAll('.flash-msg');
    flash.forEach(el => {
        setTimeout(() => {
            el.style.transition = 'opacity 0.5s';
            el.style.opacity = '0';
            setTimeout(() => el.remove(), 500);
        }, 4000);
    });
});
