package Logica;

public class Profesor extends Persona{
    private String idProfesor;
    public Profesor(String dni, String nombre, String apellido, String telefono, String idAlumno) {
        super(dni, nombre, apellido, telefono);
        this.idProfesor= idAlumno;
    }
}
