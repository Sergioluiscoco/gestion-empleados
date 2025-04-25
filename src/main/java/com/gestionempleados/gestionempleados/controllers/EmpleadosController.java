
package com.gestionempleados.gestionempleados.controllers;

import com.gestionempleados.gestionempleados.BBDD.DAO.EmpleadosDAO;
import com.gestionempleados.gestionempleados.BBDD.DAO.EmpresasDAO;
import com.gestionempleados.gestionempleados.models.Empleados;
import com.gestionempleados.gestionempleados.models.Empresas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmpleadosServlet", urlPatterns = {"/empleados"})
public class EmpleadosController extends HttpServlet {

    private EmpleadosDAO empleadosDAO;
    private EmpresasDAO empresasDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        empleadosDAO = new EmpleadosDAO();
        empresasDAO = new EmpresasDAO(); // Necesario para listar empresas en formulario
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                EditEmpleados(request, response);
                break;
            case "delete":
                DeleteEmpleados(request, response);
                break;
            case "list":
            default:
                listEmpleados(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            SaveEmpleados(request, response);
        }
    }

    private void listEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empleados> Empleados = null;
        try {
            Empleados = empleadosDAO.GetAllEmpleados();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaEmpleados", Empleados);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-empleados.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Empresas> listaEmpresas = empresasDAO.GetAllEmpresas();
            request.setAttribute("listaEmpresas", listaEmpresas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("empleados-form.jsp");
        dispatcher.forward(request, response);
    }

    private void EditEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEmpleado = Integer.parseInt(request.getParameter("id"));
        Empleados Empleado = null;
        try {
            Empleado = empleadosDAO.GetEmpleadosById(idEmpleado);
            List<Empresas> listaEmpresas = empresasDAO.GetAllEmpresas();
            request.setAttribute("listaEmpresas", listaEmpresas);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("Empleados", Empleado);
        RequestDispatcher dispatcher = request.getRequestDispatcher("empleados-form.jsp");
        dispatcher.forward(request, response);
    }

    private void SaveEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("idEmpleado");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        double salario = Double.parseDouble(request.getParameter("salario"));
        String idEmpresa = request.getParameter("idEmpresa");

        int idEmpl = (idParam == null || idParam.isEmpty()) ? 0 : Integer.parseInt(idParam);

        Empleados emp = new Empleados();
        emp.setNombre(nombre);
        emp.setApellidos(apellidos);
        emp.setEmail(email);
        emp.setSalario(salario);
        emp.setIdEmpresa(Integer.parseInt(idEmpresa));

        try {
            if (idEmpl == 0) {
                empleadosDAO.AddEmpleados(emp);
                response.sendRedirect("empleados?action=list&success=created");
            } else {
                emp.setIdEmpleado(idEmpl);
                empleadosDAO.UpdateEmpleados(emp);
                response.sendRedirect("empleados?action=list&success=updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("empleados?action=list&error=save");
        }
    }

    private void DeleteEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEmpleado = Integer.parseInt(request.getParameter("id"));
        try {
            empleadosDAO.DeleteEmpleados(idEmpleado);
            response.sendRedirect("empleados?action=list&success=deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("empleados?action=list&error=delete");
        }
    }
}
