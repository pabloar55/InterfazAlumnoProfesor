package Logica;

public class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String rol;

    public Persona(String dni, String nombre, String apellido, String telefono, String rol) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.rol= rol;
    }

    public Persona(String dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }
}