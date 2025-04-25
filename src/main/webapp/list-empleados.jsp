<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionempleados.gestionempleados.models.Empleados" %>

<html>
<head>
  <title>Listado de Empleados</title>
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
  <!-- Barra de navegación superior -->
  <nav class="navbar">
    <a href="index.jsp">🏠 Inicio</a>
    <a href="empresas?action=list">Empresas</a>
    <a href="empleados?action=list">Empleados</a>
    <button id="toggleTheme" class="button">🌓 Cambiar Modo</button>
  </nav>

  <div class="container">
  <h1>Listado de Empleados</h1>

  <div class="actions">
    <button type="button" onclick="window.history.back()" class="back-button">
      ⟵ Volver
    </button>
    <a href="empleados?action=new" class="button">
      Crear nuevo empleado
    </a>
  </div>

  <label for="searchInput"></label>
  <input type="text" id="searchInput" placeholder="Buscar empleado por nombre...">

  <table>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Apellidos</th>
      <th>Email</th>
      <th>Salario</th>
      <th>Empresa</th>
      <th>Acciones</th>
    </tr>

    <%
      List<Empleados> listaEmpleados = (List<Empleados>) request.getAttribute("listaEmpleados");
      if (listaEmpleados != null) {
        for (Empleados e : listaEmpleados) {
    %>

    <tr>
      <td><%= e.getIdEmpleado() %></td>
      <td><%= e.getNombre() %></td>
      <td><%= e.getApellidos() %></td>
      <td><%= e.getEmail()%></td>
      <td><%= e.getSalario()%></td>
      <td><%= e.getNombreEmpresa()%></td>
      <td>
        <a href="empleados?action=edit&id=<%= e.getIdEmpleado() %>">Editar</a> |
        <a href="empleados?action=delete&id=<%= e.getIdEmpleado() %>"
           onclick="return confirm('¿Deseas eliminar este empleado?');">
          Eliminar
        </a>
      </td>
    </tr>
    <%
        }
      }
    %>
  </table>
</div>

<div id="toast" class="toast">
  <span id="toastMensaje"></span>
  <button id="cerrarToast">&times;</button>
</div>
<jsp:include page="footer/footer.jsp" />
<script>
  // Filtro por nombre (DOM)
  document.getElementById("searchInput").addEventListener("keyup", function () {
    const filter = this.value.toLowerCase();
    const rows = document.querySelectorAll("table tr");

    rows.forEach((row, index) => {
      if (index === 0) return;
      const nombre = row.cells[1].textContent.toLowerCase();
      row.style.display = nombre.includes(filter) ? "" : "none";
    });
  });

  // Función para mostrar toast
  function mostrarToast(mensaje) {
    const toast = document.getElementById("toast");
    const toastMensaje = document.getElementById("toastMensaje");
    const cerrarToast = document.getElementById("cerrarToast");

    toastMensaje.textContent = mensaje;
    toast.classList.add("show");

    const timeout = setTimeout(() => {
      toast.classList.remove("show");
    }, 4000);

    cerrarToast.onclick = () => {
      toast.classList.remove("show");
      clearTimeout(timeout);
    };
  }

  // Detectar parámetros en la URL
  window.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const success = params.get("success");
    const error = params.get("error");

    if (success === "created") {
      mostrarToast("✅ Empleado creado con éxito");
    } else if (success === "updated") {
      mostrarToast("✅ Empleado actualizado correctamente");
    } else if (success === "deleted") {
      mostrarToast("✅ Empleado eliminado correctamente");
    }

    if (error === "delete") {
      mostrarToast("❌ Error al eliminar el empleado");
    } else if (error === "save") {
      mostrarToast("❌ Error al guardar el empleado");
    }
  });
</script>
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

