
public class Docente extends Persona{
    private String especialidad;

    //manejo de cursos
    private Curso[] cursos;
    private int contadorCursos;
    static final int MAX_CURSOS = 10;

    public Docente(String nombre, String apellido, String identificacion, int edad, String especialidad) {
        super(nombre, apellido, identificacion, edad);
        this.especialidad = especialidad;
        this.cursos = new Curso[MAX_CURSOS];
        this.contadorCursos = 0;
    }

    public void agregarCurso(Curso curso){
        if (this.contadorCursos >= MAX_CURSOS){
            System.out.println("El docente ya ha sido asignado a la cantidad maximas de cursos: " + MAX_CURSOS );
            return;
        }

        if(curso == null){
            System.out.println("Curso invalido");
            return;
        }

        for(int i = 0; i < this.contadorCursos; i++)
        {
            if(this.cursos[i] == curso){
                System.out.println("El docente ya ha sido asignado a este curso");
                return;
            }
        }

        this.cursos[this.contadorCursos] = curso;
        this.contadorCursos++;
    }

    public void eliminarCurso(Curso curso){
        for(int i = 0; i < this.contadorCursos; i++)
        {
            if(this.cursos[i]== curso){
                for (int j = i; j < this.contadorCursos - 1; j++){
                    this.cursos[j] = this.cursos[j+1];
                }

                this.cursos[this.contadorCursos -1] = null;
                this.contadorCursos--;
                return;
            }
        }
        System.out.println("El docente no esta inscrito en ese curso");
    }

    public void mostrarCursos(){
        if(this.contadorCursos == 0){
            System.out.println("El docente no tiene cursos activos");
            return;
        }
        System.out.println("Cursos asignados: ");
        for(int i = 0; i < this.contadorCursos; i++){
            System.out.println((i+1) + ". "+ this.cursos[i].getNombreClase());
        }
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Curso[] getCursos() {
        return cursos;
    }

    public int getContadorCursos() {
        return contadorCursos;
    }
}
