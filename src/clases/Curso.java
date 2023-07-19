package clases;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nombre;
    private String codigo;
    private int creditos;
    private String horas;
    private String dias;
    private double precio;
    private List<Maestro> maestros;
    private List<Curso> cursosInscritos;


    public Curso(String nombre, String codigo, int creditos, String horas, String dias, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.horas = horas;
        this.dias = dias;
        this.precio = precio;
        this.maestros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void agregarMaestro(Maestro maestro) {
        maestros.add(maestro);
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public String getMaestrosToString() {
        if (maestros.isEmpty()) {
            return "No hay profesores asignados";
        } else {
            StringBuilder builder = new StringBuilder();
            for (Maestro maestro : maestros) {
                builder.append(maestro.getNombre()).append(", ");
            }
            // Eliminar la última coma y espacio
            builder.setLength(builder.length() - 2);
            return builder.toString();
        }
    }

}
