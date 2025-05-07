package Persistencia;

import Logica.Alumno;
import Logica.Persona;
import Logica.Profesor;

import java.sql.*;
import java.util.ArrayList;

public class InicializarDatabase {
    private final String URL="jdbc:mariadb://localhost:3306/prog_alumnoProfe";
    private String user="root";
    private String password="";
    private String crearDB = "create database if not exists prog_alumnoProfe";
    private String crearTablaPersonas= "create table if not exists personas (dni varchar(9) primary key ,\n" +
            "            nombre varchar(30), apellido varchar(30), telefono varchar(12));";
    private String crearTablaAlumnos= "create table if not exists alumnos(id_alumn int primary key auto_increment,\n" +
            "                                               dni varchar(9) not null,\n" +
            "                foreign key fk_alumn (dni) references personas(dni) on delete cascade);";
    private String crearTablaProfesores= "create table if not exists profesores(id_prof int primary key auto_increment,\n" +
            "                                         dni varchar(9) not null,\n" +
            "                                           foreign key fk_prof (dni) references personas(dni) on delete cascade);";
    private ArrayList<Persona> personas;
    private String consultaPersonas = "select p.dni, p.nombre, p.apellido, p.telefono, pr.id_prof 'profesor'\n" +
            "from personas p\n" +
            "         join profesores pr on pr.dni= p.dni\n" +
            "union\n" +
            "select p.dni, p.nombre, p.apellido, p.telefono, a.id_alumn 'alumno'\n" +
            "from personas p\n" +
            "         join alumnos a on a.dni=p.dni;";
    public InicializarDatabase() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, user, password);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(crearDB);
            stmt.executeUpdate(crearTablaPersonas);
            stmt.executeUpdate(crearTablaAlumnos);
            stmt.executeUpdate(crearTablaProfesores);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Persona> cargarDatos(){
        personas= new ArrayList<>();
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, user, password);
            Statement stmt = c.createStatement();
            stmt.executeQuery(consultaPersonas);
            ResultSet rs =  stmt.getResultSet();
            while (rs.next()){
                if (rs.getString(6).equals("alumno")){
                    Alumno a = new Alumno(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5));
                    personas.add(Alumno a);
                }
                if (rs.getString(6).equals("alumno")){
                    Profesor pr = new Profesor(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personas;
    }
}
