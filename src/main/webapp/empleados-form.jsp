<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.gestionempleados.gestionempleados.models.Empleados"%>
<%@ page import="com.gestionempleados.gestionempleados.models.Empresas"%>
<%@ page import="java.util.List"%>
<%
  Empleados emp = (Empleados) request.getAttribute("Empleados");
  List<Empresas> listaEmpresas = (List<Empresas>) request.getAttribute("listaEmpresas");
  boolean esEdicion = emp != null;
%>
<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <title><%= esEdicion ? "Editar Empleado" : "Nuevo Empleado" %></title>
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
  <h1><%= esEdicion ? "Editar Empleado" : "Nuevo Empleado" %></h1>
  <form id="empleadoForm" action="empleados" method="post">
    <input type="hidden" name="action" value="save">
    <% if (esEdicion) { %>
    <input type="hidden" name="idEmpleado" value="<%= emp.getIdEmpleado() %>">
    <% } %>

    <div class="form-group">
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre"
             value="<%= esEdicion ? emp.getNombre() : "" %>" required>
    </div>

    <div class="form-group">
      <label for="apellidos">Apellidos:</label>
      <input type="text" id="apellidos" name="apellidos"
             value="<%= esEdicion ? emp.getApellidos() : "" %>" required>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" id="email" name="email"
             value="<%= esEdicion ? emp.getEmail() : "" %>" required>
    </div>

    <div class="form-group">
      <label for="salario">Salario:</label>
      <input type="number" step="0.01" id="salario" name="salario"
             value="<%= esEdicion ? emp.getSalario() : "" %>" required>
    </div>

    <div class="form-group">
      <label for="idEmpresa">Empresa:</label>
      <select id="idEmpresa" name="idEmpresa" required>
        <option value="">-- Selecciona una empresa --</option>
        <% for (Empresas e : listaEmpresas) {
          boolean sel = esEdicion && e.getIdEmpresa() == emp.getIdEmpresa(); %>
        <option value="<%= e.getIdEmpresa() %>" <%= sel?"selected":"" %>>
          <%= e.getNombre() %>
        </option>
        <% } %>
      </select>
    </div>

    <div class="form-buttons">
      <input type="submit" class="button" value="<%= esEdicion ? "Actualizar" : "Crear" %> Empleado">
      <a href="empleados" class="button back-button">Cancelar</a>
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

