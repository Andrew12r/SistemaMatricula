package clases;


import java.util.List;

public class Factura {
    private Estudiante estudiante;
    private List<Curso> cursosInscritos;

    public Factura(Estudiante estudiante, List<Curso> cursosInscritos) {
        this.estudiante = estudiante;
        this.cursosInscritos = cursosInscritos;
    }
    private double obtenerTotalCreditos(List<Curso> cursos) {
        double totalCreditos = 0.0;
        for (Curso curso : cursos) {
            totalCreditos += curso.getCreditos();
        }
        return totalCreditos;
    }

    public void imprimirFactura() {


    }



}
