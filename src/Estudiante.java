public class Estudiante extends Persona{
    private String carrera;

    //manejo de cursos
    private Curso[] cursos;
    private int contadorCursos;
    static final int MAX_CURSOS = 5;

    public Estudiante(String nombre, String apellido, String identificacion, int edad, String carrera) {
        super(nombre, apellido, identificacion, edad);
        this.carrera = carrera;
        this.cursos = new Curso[MAX_CURSOS];
        this.contadorCursos = 0;
    }

    public void agregarCurso(Curso curso){
        if(this.contadorCursos >= MAX_CURSOS){
            System.out.println("El estudiante no puede ser inscrito a más de " + MAX_CURSOS + " cursos.");
            return;
        }

        if(curso == null){
            System.out.println("Curso invalido");
            return;
        }

        for(int i = 0; i < this.contadorCursos; i++){
            if(this.cursos[i] == curso){
                System.out.println("El estudiante ya está inscrito en el curso: " + curso.getNombreClase());
                return;
            }
        }

        this.cursos[this.contadorCursos] = curso;
        this.contadorCursos++;
    }

    public void eliminarCurso(Curso curso){
        for(int i = 0; i < this.contadorCursos; i++){
            if (this.cursos[i] == curso){
                for(int j = i; j < this.contadorCursos -1; j++){
                    this.cursos[j] = this.cursos[j+1];
                }

                this.cursos[this.contadorCursos-1] = null;
                contadorCursos--;
                return;
            }
        }

        System.out.println("El estudiante no está inscrito en ese curso");
    }

    public void mostrarCursos(){
        if(this.contadorCursos == 0){
            System.out.println("El estudiante no tiene cursos activos");
            return;
            }
        System.out.println("Cursos inscritos");
        for(int i = 0; i < this.contadorCursos; i++){
            System.out.println((i+1) + ". "+ this.cursos[i].getNombreClase());
        }
    }

    public boolean puedeInscribirse(){
        return this.contadorCursos < MAX_CURSOS;
    }

    public int getContadorCursos() {
        return contadorCursos;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
