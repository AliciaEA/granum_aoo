import java.util.Random;

public class Persona {
    //datos basicos de persona
    private String nombre;
    private  String apellido;
    private String identificacion;
    private int edad;

    //crear emails
    private String email;
    private static Random random = new Random();
    private String pass;

    //contador de persona agregada al sistema
    static int contadorPersona;
    private int idSistema;

    //constructor
    public Persona(String nombre, String apellido, String identificacion, int edad){
        this.idSistema = ++Persona.contadorPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.edad = edad;

        this.email = nombre.toLowerCase() + apellido.substring(0, 2).toLowerCase() + identificacion.substring(0,3) + "@gmail.com";
        this.pass = String.format("%04d", random.nextInt(9999)+1) + "UNI";
    }

    public void mostrarInfo(){
        System.out.printf("""
                ID: %d
                Nombre: %s
                Apellido: %s
                Identificacion: %s
                Edad: %d
                Email: %s
                Password: %s
                """, this.idSistema, this.nombre, this.apellido, this.identificacion, this.edad, this.email, this.pass);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdSistema() {
        return idSistema;
    }
}
