import java.util.Scanner;

public class Inscripcion{

    static Scanner sc = new Scanner(System.in);

    static Estudiante[] estudiantes = new Estudiante[100];
    static Docente[] docentes = new Docente[50];
    static Curso[] cursos = new Curso[50];

    static int contadorEstudiantes = 0;
    static int contadorDocentes = 0;
    static int contadorCursos = 0;

    public static void main(String[] args) {

        int opcion;

        do {

            System.out.print("""
            ====================================
                    SISTEMA INSCRIPCIONES
            ====================================
            1. Registrar estudiante
            2. Registrar docente
            3. Crear curso
            4. Inscribir estudiante en curso
            5. Desinscribir estudiante de curso
            6. Asignar docente a curso
            7. Mostrar estudiantes de un curso
            8. Buscar estudiante en curso
            9. Mostrar cursos de estudiante
            10. Mostrar cursos de docente
            11. Mostrar cursos
            0. Salir
            ====================================
            Opcion: \s""");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> registrarEstudiante();

                case 2 -> registrarDocente();

                case 3 -> crearCurso();

                case 4 -> inscribirEstudianteCurso();

                case 5 -> desinscribirEstudianteCurso();

                case 6 -> asignarDocenteCurso();

                case 7 -> mostrarEstudiantesCurso();

                case 8 -> buscarEstudianteCurso();

                case 9 -> mostrarCursosEstudiante();

                case 10 -> mostrarCursosDocente();

                case 11 -> mostrarCursos();

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opcion invalida");
            }

        } while (opcion != 0);
    }

    // REGISTRAR ESTUDIANTE
    public static void registrarEstudiante() {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Identificacion: ");
        String id = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Carrera: ");
        String carrera = sc.nextLine();

        estudiantes[contadorEstudiantes] =
                new Estudiante(nombre, apellido, id, edad, carrera);

        contadorEstudiantes++;

        System.out.println("Estudiante registrado.");
    }

    // REGISTRAR DOCENTE
    public static void registrarDocente() {

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Identificacion: ");
        String id = sc.nextLine();

        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Especialidad: ");
        String esp = sc.nextLine();

        docentes[contadorDocentes] =
                new Docente(nombre, apellido, id, edad, esp);

        contadorDocentes++;

        System.out.println("Docente registrado.");
    }

    // CREAR CURSO
    public static void crearCurso() {

        System.out.print("Nombre del curso: ");
        String nombre = sc.nextLine();

        cursos[contadorCursos] = new Curso(nombre);

        contadorCursos++;

        System.out.println("Curso creado.");
    }

    // MOSTRAR CURSOS
    public static void mostrarCursos() {

        if (contadorCursos == 0) {
            System.out.println("No hay cursos.");
            return;
        }

        for (int i = 0; i < contadorCursos; i++) {

            System.out.println((i) + ". "
                    + cursos[i].getNombreClase()
                    + " (" + cursos[i].getCodigoClase() + ")");
        }
    }

    // INSCRIBIR ESTUDIANTE
    public static void inscribirEstudianteCurso() {

        mostrarCursos();

        if(contadorCursos == 0){
            return;
        }

        System.out.print("Seleccione curso: ");
        int c = Integer.parseInt(sc.nextLine());

        System.out.println("Seleccione estudiante: ");

        for (int i = 0; i < contadorEstudiantes; i++) {

            System.out.println((i) + ". "
                    + estudiantes[i].getNombre()
                    + " " + estudiantes[i].getApellido());
        }

        int e = Integer.parseInt(sc.nextLine());

        cursos[c].inscribirEstudiante(estudiantes[e]);
    }

    // DESINSCRIBIR
    public static void desinscribirEstudianteCurso() {

        mostrarCursos();

        System.out.print("Seleccione curso: ");
        int c = Integer.parseInt(sc.nextLine());

        System.out.println("Seleccione estudiante: ");

        for (int i = 0; i < contadorEstudiantes; i++) {

            System.out.println((i) + ". "
                    + estudiantes[i].getNombre()
                    + " " + estudiantes[i].getApellido());
        }

        int e = Integer.parseInt(sc.nextLine());

        cursos[c].desinscribirEstudiante(estudiantes[e]);
    }

    // ASIGNAR DOCENTE
    public static void asignarDocenteCurso() {

        mostrarCursos();

        System.out.print("Seleccione curso: ");
        int c = Integer.parseInt(sc.nextLine());

        System.out.print("Seleccione docente: ");

        for (int i = 0; i < contadorDocentes; i++) {

            System.out.println((i) + ". "
                    + docentes[i].getNombre()
                    + " " + docentes[i].getApellido());
        }

        int d = Integer.parseInt(sc.nextLine());

        cursos[c].asignarDocente(docentes[d]);

        docentes[d].agregarCurso(cursos[c]);
    }

    // MOSTRAR ESTUDIANTES
    public static void mostrarEstudiantesCurso() {

        mostrarCursos();

        System.out.print("Seleccione curso: ");
        int c = sc.nextInt();
        sc.nextLine();

        cursos[c].mostrarEstudiantes(cursos[c].getNombreClase());
    }

    // BUSCAR ESTUDIANTE
    public static void buscarEstudianteCurso() {

        mostrarCursos();

        System.out.print("Seleccione curso: ");
        int c = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese ID del estudiante: ");
        int id = Integer.parseInt(sc.nextLine());

        cursos[c].buscarEstudiante(id);
    }

    // CURSOS ESTUDIANTE
    public static void mostrarCursosEstudiante() {

        for (int i = 0; i < contadorEstudiantes; i++) {

            System.out.println((i) + ". "
                    + estudiantes[i].getNombre()
                    + " " + estudiantes[i].getApellido());
        }

        int e = Integer.parseInt(sc.nextLine());

        estudiantes[e].mostrarCursos();
    }

    // CURSOS DOCENTE
    public static void mostrarCursosDocente() {

        for (int i = 0; i < contadorDocentes; i++) {

            System.out.println((i) + ". "
                    + docentes[i].getNombre()
                    + " " + docentes[i].getApellido());
        }

        int d = Integer.parseInt(sc.nextLine());

        docentes[d].mostrarCursos();
    }

}