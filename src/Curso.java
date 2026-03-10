import java.util.Random;

public class Curso {
    //originales de clase
    private String nombreClase;
    //codigo clase
    private static Random random = new Random();
    private String codigoClase;

    //docente
    private Docente docente;

    //manejo de estudiantes
    private final int MAX_ESTUDIANTES = 30;
    private Estudiante[] estudiantes = new Estudiante[MAX_ESTUDIANTES];
    private int contadorEstudiantes;


    public Curso(String nombreClase){
        this.nombreClase = nombreClase;
        this.codigoClase = nombreClase.substring(0, 2)+ String.format("%03d", random.nextInt(999)+1);
    }

    public void inscribirEstudiante(Estudiante estudiante){
        if (this.contadorEstudiantes >= this.MAX_ESTUDIANTES) {
            System.out.println("Curso lleno");
            return;
        }

        if(!estudiante.puedeInscribirse()){
            System.out.println("El estudiante ya tiene el maximo de cursos inscritos");
            return;
        }

        for(int i = 0; i < this.contadorEstudiantes; i++){
            if(this.estudiantes[i].getIdSistema() == estudiante.getIdSistema()){
                System.out.println("Usuario ya inscrito");
                return;
            }
        }

        this.estudiantes[this.contadorEstudiantes] = estudiante;
        estudiante.agregarCurso(this);

        System.out.println(estudiante.getNombre() + " inscrito en " + this.nombreClase +
                "\nEstudiantes inscritos: " + (this.contadorEstudiantes + 1) + "/" + this.MAX_ESTUDIANTES);

        contadorEstudiantes++;
    }

    public void mostrarEstudiantes(String nombreCurso){
        if (!nombreCurso.equals(this.nombreClase)){
            System.out.printf("""
                    Curso: %s
                    
                    El curso no existe.
                    """, nombreCurso);
            return;
        }

        if(contadorEstudiantes == 0 && nombreCurso.equals(this.nombreClase)){
            System.out.printf("""
                    Curso: %s - %s
                    
                    El curso está vacio.
                    """, this.codigoClase,nombreCurso);
            return;
        }

        System.out.printf("""
                Curso: %s- %s
                """, this.codigoClase,nombreCurso);

        for(int i = 0; i < this.contadorEstudiantes;i++){
            System.out.printf("""
                    %d. %d - %s %s
                    """, (i+1),this.estudiantes[i].getIdSistema() ,this.estudiantes[i].getNombre(), this.estudiantes[i].getApellido());
        }
    }

    public void infoCurso(String nombreClase){
        if (nombreClase.equals(this.nombreClase) && this.docente != null){
            System.out.printf("""
                Curso: %s
                Codigo: %s
                Docente: %s %s
                Estudiantes inscritos: %d/%d
                """, this.nombreClase, this.codigoClase, this.docente.getNombre(), this.docente.getApellido(), this.contadorEstudiantes, this.MAX_ESTUDIANTES);
        }else if (nombreClase.equals(this.nombreClase) && this.docente == null){
            System.out.printf("""
                Curso: %s
                Codigo: %s
                Docente: No se ha asignado docente
                Estudiantes inscritos: %d/%d
                """, this.nombreClase, this.codigoClase, this.contadorEstudiantes, this.MAX_ESTUDIANTES);
        }
        else {
            System.out.printf("""
                    Curso: %s
                    
                    El curso no existe.
                    """, nombreClase);
        }
    }

    public void asignarDocente(Docente docente){
        if(this.docente == null){
            this.docente = docente;

            System.out.printf("Docente: %s %s asignado al curso %s\n", docente.getNombre(), docente.getApellido(), this.nombreClase);
        }
        else {
            System.out.println("El curso ya tiene un docente asignado");
        }
    }

    public void desinscribirEstudiante(Estudiante estudiante){
        for (int i = 0; i < this.contadorEstudiantes; i++){

            if(estudiante.getIdSistema() == this.estudiantes[i].getIdSistema()) {

                System.out.printf("Estudiante: %s %s desinscrito del curso", this.estudiantes[i].getNombre(), this.estudiantes[i].getApellido());


                //mover estudiantes hacia la izquierda
                for(int j = i; j < this.contadorEstudiantes - 1; j++){
                    this.estudiantes[j] = this.estudiantes[j+1];
                }

                //eliminar ultima pos
                this.estudiantes[this.contadorEstudiantes - 1] = null;

                estudiante.eliminarCurso(this);
                this.contadorEstudiantes--;
                return;
            }
        }
        System.out.println("Estudiante no encontrado");
    }

    public void buscarEstudiante(int id){
        for (int i = 0; i < this.contadorEstudiantes; i++){
            if (id == this.estudiantes[i].getIdSistema()){
                System.out.printf("""
                        ID: %d
                        Nombre: %s %s
                        Identificacion: %s
                        Correo Electronico: %s
                        Contraseña: %s
                        """, this.estudiantes[i].getIdSistema(), this.estudiantes[i].getNombre(), this.estudiantes[i].getApellido(), this.estudiantes[i].getIdentificacion(), this.estudiantes[i].getEmail(), this.estudiantes[i].getPass());
                return;
            }
        }
        System.out.println("Estudiante no encontrado");
    }


    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Estudiante[] getEstudiantes() {
        return estudiantes;
    }


    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    public int getContadorEstudiantes() {
        return contadorEstudiantes;
    }
}
