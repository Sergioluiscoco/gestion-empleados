
package com.gestionempleados.gestionempleados.BBDD.DAO;

import com.gestionempleados.gestionempleados.BBDD.BBDDConnector;
import com.gestionempleados.gestionempleados.models.Empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosDAO {
    public List<Empleados> GetAllEmpleados() throws SQLException {
        List<Empleados> Empl = new ArrayList<>();

        Connection conn = BBDDConnector.GetInstance().getConnection();
        String sql = "SELECT e.id_Empleado, e.nombre, e.apellidos, e.email, e.salario, e.id_Empresa, emp.nombre AS nombreEmpresa " +
                "FROM empleados e " +
                "JOIN empresas emp ON e.id_Empresa = emp.id_Empresa";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Empleados e = new Empleados();
            e.setIdEmpleado(rs.getInt("id_Empleado"));
            e.setNombre(rs.getString("nombre"));
            e.setApellidos(rs.getString("apellidos"));
            e.setEmail(rs.getString("email"));
            e.setSalario(rs.getDouble("salario"));
            e.setIdEmpresa(rs.getInt("id_Empresa"));
            e.setNombreEmpresa(rs.getString("nombreEmpresa"));

            Empl.add(e);
        }

        return Empl;
    }

    public Empleados GetEmpleadosById(int id) throws SQLException {
        Empleados e = null;

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT id_Empleado, nombre, apellidos, email, salario, id_Empresa FROM empleados WHERE id_Empleado = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            e = new Empleados();
            e.setIdEmpleado(rs.getInt("id_Empleado"));
            e.setNombre(rs.getString("nombre"));
            e.setApellidos(rs.getString("apellidos"));
            e.setEmail(rs.getString("email"));
            e.setSalario(rs.getDouble("salario"));
            e.setIdEmpresa(rs.getInt("id_Empresa"));
        }

        return e;
    }

    public void AddEmpleados(Empleados Empleados) throws SQLException {

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO empleados (nombre, apellidos, email, salario, id_Empresa) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, Empleados.getNombre());
        ps.setString(2, Empleados.getApellidos());
        ps.setString(3, Empleados.getEmail());
        ps.setDouble(4, Empleados.getSalario());
        ps.setInt(5, Empleados.getIdEmpresa());

        int rows = ps.executeUpdate();
    }

    public void UpdateEmpleados(Empleados Empleados) throws SQLException {

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE empleados SET nombre = ?, apellidos = ?, email = ?, salario = ?, id_Empresa = ? WHERE id_Empleado = ?");
        ps.setString(1, Empleados.getNombre());
        ps.setString(2, Empleados.getApellidos());
        ps.setString(3, Empleados.getEmail());
        ps.setDouble(4, Empleados.getSalario());
        ps.setInt(5, Empleados.getIdEmpresa());
        ps.setInt(6, Empleados.getIdEmpleado());

        int rows = ps.executeUpdate();
    }

    public void DeleteEmpleados(int id) throws SQLException {

        Connection conn = BBDDConnector.GetInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM empleados WHERE id_Empleado = ?");
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
    }
}