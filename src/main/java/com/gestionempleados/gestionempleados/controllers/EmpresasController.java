package com.gestionempleados.gestionempleados.controllers;
import com.gestionempleados.gestionempleados.BBDD.DAO.EmpresasDAO;
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

@WebServlet(name = "EmpresasServlet", urlPatterns = {"/empresas"})
public class EmpresasController extends HttpServlet {

    private EmpresasDAO empresasDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        empresasDAO = new EmpresasDAO();
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
                EditEmpresas(request, response);
                break;
            case "delete":
                DeleteEmpresas(request, response);
                break;
            case "list":
            default:
                listEmpresas(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            SaveEmpresas(request, response);
        }
    }

    private void listEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Empresas> Empresas = null;
        try {
            Empresas = empresasDAO.GetAllEmpresas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaEmpresas", Empresas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-empresas.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("empresas-form.jsp");
        dispatcher.forward(request, response);
    }

    private void EditEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEmpresa = Integer.parseInt(request.getParameter("id"));
        Empresas Empresa = null;
        try {
            Empresa = empresasDAO.GetEmpresasById(idEmpresa);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("Empresas", Empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("empresas-form.jsp");
        dispatcher.forward(request, response);
    }

    private void SaveEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idEmpresa");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");

        int idEmp = (idParam == null || idParam.isEmpty()) ? 0 : Integer.parseInt(idParam);

        Empresas emp = new Empresas();
        emp.setNombre(nombre);
        emp.setDireccion(direccion);

        try {
            if (idEmp == 0) {
                empresasDAO.AddEmpresas(emp);
                response.sendRedirect("empresas?action=list&success=created");
            } else {
                emp.setIdEmpresa(idEmp);
                empresasDAO.UpdateEmpresas(emp);
                response.sendRedirect("empresas?action=list&success=updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("empresas?action=list&error=save");
        }
    }

    private void DeleteEmpresas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEmpresa = Integer.parseInt(request.getParameter("id"));
        try {
            empresasDAO.DeleteEmpresas(idEmpresa);
            response.sendRedirect("empresas?action=list&success=deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("empresas?action=list&error=delete");
        }
    }
}

