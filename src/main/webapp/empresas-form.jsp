
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestionempleados.gestionempleados.models.Empresas" %>
<%
  Empresas emp = (Empresas) request.getAttribute("Empresas");
  boolean esEdicion = emp != null;
%>
<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <title><%= esEdicion ? "Editar Empresa" : "Nueva Empresa" %></title>
</head>
<body>

<!-- Barra de navegación superior -->
<nav class="navbar">
  <a href="index.jsp">🏠 Inicio</a>
  <a href="empresas?action=list">Empresas</a>
  <a href="empleados?action=list">Empleados</a>
  <button id="toggleTheme" class="button">🌓 Cambiar Modo</button>
</nav>

<div class="form-wrapper">
  <h1><%= esEdicion ? "Editar Empresa" : "Nueva Empresa" %></h1>
  <form id="empresaForm" action="empresas" method="post">
    <input type="hidden" name="action" value="save">
    <% if (esEdicion) { %>
    <input type="hidden" name="idEmpresa" value="<%= emp.getIdEmpresa() %>">
    <% } %>

    <div class="form-group">
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre"
             value="<%= esEdicion ? emp.getNombre() : "" %>" required>
    </div>

    <div class="form-group">
      <label for="direccion">Dirección:</label>
      <input type="text" id="direccion" name="direccion"
             value="<%= esEdicion ? emp.getDireccion() : "" %>" required>
    </div>

    <div class="form-buttons">
      <input type="submit" class="button" value="<%= esEdicion ? "Actualizar" : "Crear" %> Empresa">
      <a href="empresas" class="button back-button">Cancelar</a>
    </div>
  </form>
</div>
<jsp:include page="footer/footer.jsp" />
<script>
  /* Botones inferiores */
  const toggleBtn = document.getElementById("toggleTheme");

  if (toggleBtn) {
    toggleBtn.addEventListener("click", () => {
      document.body.classList.toggle("dark-mode");
      // Guardar preferencia
      localStorage.setItem("modoOscuro", document.body.classList.contains("dark-mode"));
    });

    // Cargar preferencia al iniciar
    if (localStorage.getItem("modoOscuro") === "true") {
      document.body.classList.add("dark-mode");
    }
  }
</script>
</body>
</html>
