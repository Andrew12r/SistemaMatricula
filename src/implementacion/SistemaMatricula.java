package implementacion;

import clases.Curso;
import clases.Estudiante;
import clases.Factura;
import clases.Maestro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaMatricula {
    private static List<Estudiante> estudiantes = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    private static List<Curso> cursosInscritos = new ArrayList<>();
    private static Estudiante estudianteActual;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Cargar la lista de cursos
        cargarCursos();

        boolean salir = false;
        while (!salir) {
            System.out.println("----- Menú Principal -----");
            System.out.println("1) Ingrese los datos del estudiante");
            System.out.println("2) Ingrese los datos del curso");
            System.out.println("3) Reporte de matrículas");
            System.out.println("4) Imprimir Factura");
            System.out.println("5) Salir y guardar");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    ingresarDatosEstudiante(scanner);
                    break;
                case 2:
                    ingresarDatosCurso(scanner);
                    break;
                case 3:
                    generarReporteMatriculas();
                    break;
                case 4:
                    imprimirFactura();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }

        // Aquí se puede guardar la información antes de salir
    }

    private static void cargarCursos() {
        Curso ingles001 = new Curso("Ingles", "PROF001", 3, "3:30pm", "Lun, Mié, Vie", 120.0);
        ingles001.agregarMaestro(new Maestro("Juan Carlos", "M001"));
        cursos.add(ingles001);
        Curso ingles002 = new Curso("Ingles", "PROF002", 3, "6:00pm", "Mar, Juv, Sab", 120.0);
        ingles002.agregarMaestro(new Maestro("Maria Gomez", "M002"));
        cursos.add(ingles002);
        Curso ingles003 = new Curso("Ingles", "PROF003", 3, "10:00am", "Dom", 160.0);
        ingles003.agregarMaestro(new Maestro("Carlos Gutierrez", "M003"));
        cursos.add(ingles003);

        Curso java = new Curso("Java", "PRF011", 4, "8:30am", "Mar, Jue", 210.0);
        java.agregarMaestro(new Maestro("Pilar Loza", "f2001"));
        cursos.add(java);
        Curso java01 = new Curso("Java", "PRF012", 4, "2:30pm", "Lun, Mie", 210.0);
        java01.agregarMaestro(new Maestro("Juquin Pier", "f302"));
        cursos.add(java01);
        Curso java02 = new Curso("Java", "PRF013", 4, "3:50pm", "Vie, Sab", 210.0);
        java02.agregarMaestro(new Maestro("Percy Gutierrez", "f303"));
        cursos.add(java02);

        Curso HtmlyCss  = new Curso("Html y Css", "PRO0001", 4, "8:30am", "Mar, Jue", 210.0);
        java.agregarMaestro(new Maestro("Rosario Flores", "HC020001"));
        cursos.add(HtmlyCss);
        Curso HtmlyCss01 = new Curso("Html y Css", "PRO0002", 4, "2:30pm", "Lun, Mie", 210.0);
        java01.agregarMaestro(new Maestro("Angel Diaz", "HC03002"));
        cursos.add(HtmlyCss01);
        // Agregar más cursos con sus respectivos profesores
    }

    private static void ingresarDatosEstudiante(Scanner scanner) {
        System.out.println("----- Ingrese los datos del estudiante -----");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        try {
            System.out.print("Código: ");
            String codigo = scanner.nextLine(); // Leer el código del estudiante
            Estudiante nuevoEstudiante = new Estudiante(nombre, dni, direccion, edad, sexo, codigo); // Pasar el código al constructor
            estudiantes.add(nuevoEstudiante);
            estudianteActual = nuevoEstudiante; // Asignar el nuevo estudiante como estudiante actual
            System.out.println("Estudiante agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }

    private static void ingresarDatosCurso(Scanner scanner) {
        System.out.println("----- Ingrese los datos del curso -----");
        System.out.println("Lista de cursos:");
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            List<Maestro> maestros = curso.getMaestros();

            StringBuilder profesores = new StringBuilder();
            for (Maestro maestro : maestros) {
                profesores.append(maestro.getNombre()).append(", ");
            }
            // Eliminamos la última coma y espacio
            if (profesores.length() > 0) {
                profesores.setLength(profesores.length() - 2);
            }

            System.out.println((i + 1) + ") " + curso.getNombre() + " - Código: " + curso.getCodigo() +
                    " - Créditos: " + curso.getCreditos() + " - Días: " + curso.getDias() +
                    " - Profesores: " + profesores.toString() + " - Precio: " + curso.getPrecio());

        }


        System.out.print("Seleccione el número del curso: ");
        int numeroCurso = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        if (numeroCurso >= 1 && numeroCurso <= cursos.size()) {
            Curso cursoSeleccionado = cursos.get(numeroCurso - 1);
            estudianteActual.inscribirCurso(cursoSeleccionado);
            System.out.println("El curso '" + cursoSeleccionado.getNombre() + "' ha sido inscrito.");
        } else {
            System.out.println("Número de curso inválido. Por favor, seleccione un número válido.");
        }
    }

    private static void generarReporteMatriculas() {
        System.out.println("----- Reporte de Matrículas -----");
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = estudiantes.get(i);
            System.out.println("Estudiante " + (i + 1) + ":");
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("DNI: " + estudiante.getDni());
            System.out.println("Dirección: " + estudiante.getDireccion());

            System.out.println("Cursos inscritos:");
            List<Curso> cursosInscritosEstudiante = estudiante.getCursosInscritos();
            for (Curso curso : cursosInscritosEstudiante) {
                System.out.println("- " + curso.getNombre() + " - Precio: " + curso.getPrecio());
            }

            System.out.println();
        }
    }

    private static void imprimirFactura() {
        if (estudianteActual != null) {
            Factura factura = new Factura(estudianteActual, cursosInscritos);
            factura.imprimirFactura();
        } else {
            System.out.println("No hay un estudiante seleccionado.");
        }


        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mma");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        String currentTime = hourFormat.format(date);

        System.out.println("                                                                                         Fecha:  " + currentDate);
        System.out.println("                         UTP                                                             Hora:   " + currentTime);
        System.out.println("                                                                                         Pagina:    1          ");
        System.out.println();
        System.out.println("-----------------------------------------------  Ficha de Matricula  2023 -1 ------------------------------------------");
        System.out.println("Nombre del estudiante:  |         " + estudianteActual.getNombre() + "             |       DNI:                    " + estudianteActual.getDni());
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Sede:                 Universidad Tecnologica del Peru - Arequipa           |       Programa:              TIC783                      ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Facultad/Escuela:                  Ing. de Sistemas e Informatica           |       Codigo de estudiante:  " + estudianteActual.getCodigo() + "              ");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("El departamento de Resgistro deja constancia que el (la) Sr. " + estudianteActual.getNombre() +
                " con codigo\n" + estudianteActual.getCodigo() + " ha registrado matricula en el 2° ciclo de acuerdo al detalle a continuacion de indica.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s | %-20s | %-10s | %-15s | %-15s%n", "Codigo", "Cursos", "Créditos", "Vez", "Precio");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        for (Curso curso : cursosInscritos) {
            System.out.printf("%-20s | %-20s | %-10s | %-15s | %-15s%n",
                    curso.getCodigo(), curso.getNombre(), curso.getCreditos(), "1", curso.getPrecio());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");


        double totalPago = 0.0;
        List<Curso> cursosInscritos = estudianteActual.getCursosInscritos();
        for (Curso curso : cursosInscritos) {
            System.out.println("  " + curso.getCodigo() + "       |      " + curso.getNombre() + "       |      " + curso.getCreditos() + "       |      1        |       " + curso.getPrecio() + "        |       ");
            totalPago += curso.getPrecio();
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-80s%s%n", "Total a pagar:", totalPago);

        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("Resumen:");
        System.out.printf("%-39s%d%n", "Total de Cursos Matriculados:", cursosInscritos.size());
        System.out.printf("%-39s%.1f%n", "Total de Creditos:", obtenerTotalCreditos(cursosInscritos));
    }


    private static double obtenerTotalCreditos(List<Curso> cursos) {
        int totalCreditos = 0;
        for (Curso curso : cursos) {
            totalCreditos += curso.getCreditos();
        }
        return totalCreditos;
    }
    public static List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }
}

