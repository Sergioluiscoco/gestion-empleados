
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Empleados y Empresas</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<!-- Barra de navegación superior -->
<nav class="navbar">
    <a href="index.jsp">🏠 Inicio</a>
    <a href="empresas?action=list">Empresas</a>
    <a href="empleados?action=list">Empleados</a>
    <!-- Botón de alternar tema -->
    <button id="toggleTheme" class="button">🌓 Modo</button>
</nav>

<div class="container">

    <!--TITULO DE LA PAGINA -->

    <h1>
        <span>Gestión de Empleados</span>
                <span>y</span>
            <span>Empresas</span>
    </h1>

    <!-- Contenido principal -->

    <!-- Acciones Empresas -->

    <div class="actions">
        <a href="empresas?action=list" class="button">Ver Empresas</a>
        <a href="empresas?action=new" class="button">Crear Empresa</a>
    </div>

    <!-- Acciones Empleados -->
    <div class="actions">
        <a href="empleados?action=list" class="button">Ver Empleados</a>
        <a href="empleados?action=new" class="button">Crear Empleado</a>
    </div>
</div>

<script>
    // Script de modo claro/oscuro
    const toggleBtn = document.getElementById("toggleTheme");
    if (toggleBtn) {
        toggleBtn.addEventListener("click", () => {
            document.body.classList.toggle("dark-mode");
            localStorage.setItem("modoOscuro", document.body.classList.contains("dark-mode"));
        });
        if (localStorage.getItem("modoOscuro") === "true") {
            document.body.classList.add("dark-mode");
        }
    }
</script>
</body>
</html>
