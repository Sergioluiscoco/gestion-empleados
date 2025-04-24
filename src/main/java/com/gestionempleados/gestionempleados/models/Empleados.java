package com.gestionempleados.gestionempleados.models;

public class Empleados {
    private int idEmpleado;
    private String nombre;
    private String apellidos;
    private String email;
    private double salario;
    private int idEmpresa;
    private String nombreEmpresa;

    public Empleados() {
    }

    public Empleados(int idEmpleado, String nombre, String apellidos, String email, double salario, int idEmpresa, String nombreEmpresa) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.salario = salario;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
    }


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
}