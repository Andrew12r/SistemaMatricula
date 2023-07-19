package clases;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private String dni;
    private String direccion;
    private int edad;
    private String sexo;
    private String codigo;
    private List<Curso> cursosInscritos;


    public Estudiante(String nombre, String dni, String direccion, int edad, String sexo, String codigo) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.edad = edad;
        this.sexo = sexo;
        this.codigo = codigo;
        this.cursosInscritos = new ArrayList<>();
    }

    public void inscribirCurso(Curso curso) {
        cursosInscritos.add(curso);
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCodigo() {
        return codigo;
    }




    // Setter del sexo con validación
    public void setSexo(String sexo) {
        sexo = sexo.toUpperCase();
        if (sexo.equals("M") || sexo.equals("F")) {
            this.sexo = sexo;
        } else {
            throw new IllegalArgumentException("El valor del sexo debe ser 'M' o 'F'.");
        }
    }

}
