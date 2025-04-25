
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionempleados.gestionempleados.models.Empresas" %>

<html>
<head>
  <title>Listado de Empresas</title>
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
  <h1>Listado de Empresas</h1>
  </div>

  <div class="actions">
    <button type="button" onclick="window.history.back()" class="back-button">
      ⟵ Volver
    </button>
    <a href="empresas?action=new" class="button">
      Crear nueva empresa
    </a>
  </div>

  <label for="searchInput"></label>
  <input type="text" id="searchInput" placeholder="Buscar empresa por nombre...">

  <table>
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Dirección</th>
      <th>Acciones</th>
    </tr>

    <%
      List<Empresas> listaEmpresas = (List<Empresas>) request.getAttribute("listaEmpresas");
      if (listaEmpresas != null) {
        for (Empresas emp : listaEmpresas) {
    %>

    <tr>
      <td><%= emp.getIdEmpresa() %></td>
      <td><%= emp.getNombre() %></td>
      <td><%= emp.getDireccion() %></td>
      <td>
        <a href="empresas?action=edit&id=<%= emp.getIdEmpresa() %>">Editar</a> |
        <a href="empresas?action=delete&id=<%= emp.getIdEmpresa() %>"
           onclick="return confirm('¿Deseas eliminar esta empresa?');">
          Eliminar
        </a>
      </td>
    </tr>
    <%
        }
      }
    %>
  </table>
  <jsp:include page="footer/footer.jsp" />

<div id="toast" class="toast">
  <span id="toastMensaje"></span>
  <button id="cerrarToast">&times;</button>
</div>

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
      mostrarToast("✅ Empresa creada con éxito");
    } else if (success === "updated") {
      mostrarToast("✅ Empresa actualizada correctamente");
    } else if (success === "deleted") {
      mostrarToast("✅ Empresa eliminada correctamente");
    }

    if (error === "delete") {
      mostrarToast("❌ Error al eliminar la empresa");
    } else if (error === "save") {
      mostrarToast("❌ Error al guardar la empresa");
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

