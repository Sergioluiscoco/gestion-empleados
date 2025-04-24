package com.gestionempleados.gestionempleados.BBDD.DAO;

import com.gestionempleados.gestionempleados.BBDD.BBDDConnector;
import com.gestionempleados.gestionempleados.models.Empresas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresasDAO {
    public List<Empresas> GetAllEmpresas() throws SQLException {
        List<Empresas> emps = new ArrayList<>();


        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id_Empresa, nombre, direccion FROM empresas");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Empresas emp = new Empresas();
            emp.setIdEmpresa(rs.getInt("id_Empresa"));
            emp.setNombre(rs.getString("nombre"));
            emp.setDireccion(rs.getString("direccion"));

            emps.add(emp);

        }
        return emps;
    }

    public Empresas GetEmpresasById(int id) throws SQLException {
        Empresas emp = null;

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id_Empresa, nombre, direccion FROM empresas WHERE id_Empresa = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            emp = new Empresas();
            emp.setIdEmpresa(rs.getInt("id_Empresa"));
            emp.setNombre(rs.getString("nombre"));
            emp.setDireccion(rs.getString("direccion"));
        }

        return emp;
    }

    public void AddEmpresas(Empresas Empresas) throws SQLException {

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO empresas (nombre, direccion) VALUES (?, ?)");
        ps.setString(1, Empresas.getNombre());
        ps.setString(2, Empresas.getDireccion());

        int rows = ps.executeUpdate();
    }

    public void UpdateEmpresas(Empresas Empresas) throws SQLException {

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE empresas SET nombre = ?, direccion = ? WHERE id_Empresa = ?");
        ps.setString(1, Empresas.getNombre());
        ps.setString(2, Empresas.getDireccion());
        ps.setInt(3, Empresas.getIdEmpresa());

        int rows = ps.executeUpdate();
    }


    public void DeleteEmpresas(int id) throws SQLException {

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM empresas WHERE id_Empresa = ?");
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
    }
}