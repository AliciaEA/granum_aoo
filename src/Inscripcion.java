import java.util.ArrayList;
import java.util.Scanner;

public class Inscripcion {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Persona> personas = new ArrayList<>();
    static ArrayList<Curso> cursos = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.print("""
            ====================================
                    SISTEMA INSCRIPCIONES (POLIMÓRFICO)
            ====================================
            1. Registrar estudiante
            2. Registrar docente
            3. Crear curso
            4. Inscribir estudiante en curso
            5. Asignar docente a curso
            6. Mostrar información de usuarios
            7. Mostrar estudiantes de un curso
            0. Salir
            ====================================
            Opcion: \s""");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> registrarPersona(true);
                case 2 -> registrarPersona(false);
                case 3 -> crearCurso();
                case 4 -> inscribirEstudianteCurso();
                case 5 -> asignarDocenteCurso();
                case 6 -> mostrarUsuariosSistema();
                case 7 -> mostrarEstudiantesCurso();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    public static void registrarPersona(boolean esEstudiante) {
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Apellido: "); String apellido = sc.nextLine();
        System.out.print("Identificacion: "); String id = sc.nextLine();
        System.out.print("Edad: "); int edad = Integer.parseInt(sc.nextLine());

        if (esEstudiante) {
            System.out.print("Carrera: ");
            String carrera = sc.nextLine();
            personas.add(new Estudiante(nombre, apellido, id, edad, carrera));
        } else {
            System.out.print("Especialidad: ");
            String esp = sc.nextLine();
            personas.add(new Docente(nombre, apellido, id, edad, esp));
        }
        System.out.println("Registro exitoso.");
    }

    // metodo polimorfico
    public static void mostrarUsuariosSistema() {
        if (personas.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }
        System.out.println("\n--- LISTADO GENERAL ---");
        for (Persona p : personas) {
            ejecutarAccionPolimorfica(p);
            System.out.println("-----------------------");
        }
    }

    public static void ejecutarAccionPolimorfica(Persona p) {
        p.mostrarInfo(); //poli

        if (p instanceof Estudiante e) {
            System.out.println("Tipo: Estudiante | Carrera: " + e.getCarrera());
        } else if (p instanceof Docente d) {
            System.out.println("Tipo: Docente | Especialidad: " + d.getEspecialidad());
        }
    }

    public static void crearCurso() {
        System.out.print("Nombre del curso: ");
        cursos.add(new Curso(sc.nextLine()));
        System.out.println("Curso creado.");
    }

    public static void inscribirEstudianteCurso() {
        listarCursos();
        if (cursos.isEmpty()) return;

        System.out.print("Seleccione índice del curso: ");
        int cIdx = Integer.parseInt(sc.nextLine());

        System.out.println("Seleccione estudiante:");
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i) instanceof Estudiante) {
                System.out.println(i + ". " + personas.get(i).getNombre());
            }
        }
        int eIdx = Integer.parseInt(sc.nextLine());

        if (personas.get(eIdx) instanceof Estudiante est) {
            cursos.get(cIdx).inscribirEstudiante(est);
        }
    }

    public static void asignarDocenteCurso() {
        listarCursos();
        System.out.print("Seleccione curso: ");
        int cIdx = Integer.parseInt(sc.nextLine());

        System.out.println("Seleccione docente:");
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i) instanceof Docente) {
                System.out.println(i + ". " + personas.get(i).getNombre());
            }
        }
        int dIdx = Integer.parseInt(sc.nextLine());

        if (personas.get(dIdx) instanceof Docente doc) {
            cursos.get(cIdx).asignarDocente(doc);
            doc.agregarCurso(cursos.get(cIdx));
        }
    }

    public static void mostrarEstudiantesCurso() {
        listarCursos();
        if (cursos.isEmpty()) return;
        System.out.print("Seleccione curso: ");
        int cIdx = Integer.parseInt(sc.nextLine());
        cursos.get(cIdx).mostrarEstudiantes(cursos.get(cIdx).getNombreClase());
    }

    private static void listarCursos() {
        if (cursos.isEmpty()) System.out.println("No hay cursos.");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println(i + ". " + cursos.get(i).getNombreClase());
        }
    }
}