package Logica;

public class Alumno extends Persona{
    private String idAlumno;
    public Alumno(String dni, String nombre, String apellido, String telefono, String idAlumno) {
        super(dni, nombre, apellido, telefono);
        this.idAlumno= idAlumno;
    }
}
