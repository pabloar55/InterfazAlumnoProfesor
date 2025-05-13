package Persistencia;

import Logica.Persona;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class InicializarDatabase {
    private String URL = "jdbc:mariadb://localhost:3306/";
    private String user = "root";
    private String password = "";
    private String crearDB = "create database if not exists colegio";
    private String crearTablaPersonas = "create table if not exists personas (dni varchar(9) primary key ,\n" +
            "            nombre varchar(30), apellido varchar(30), telefono varchar(12));";
    private String crearTablaAlumnos = "create table if not exists alumnos(id_alumn int primary key auto_increment,\n" +
            "                                               dni varchar(9) not null unique,\n" +
            "                foreign key fk_alumn (dni) references personas(dni) on delete cascade);";
    private String crearTablaProfesores = "create table if not exists profesores(id_prof int primary key auto_increment,\n" +
            "                                         dni varchar(9) not null unique,\n" +
            "                                           foreign key fk_prof (dni) references personas(dni) on delete cascade);";
    private ArrayList<Persona> personas;
    private String consultaPersonas = "select p.dni, p.nombre, p.apellido, p.telefono,\n" +
            "       case when pr.dni is not null and a.dni is not null then 'ProfesorAlumno'\n" +
            "        when pr.dni is not null then 'Profesor'\n" +
            "        when a.dni is not null then 'Alumno' end as 'rol'\n" +
            "from personas p\n" +
            " left join profesores pr on pr.dni = p.dni\n" +
            "left  join alumnos a on a.dni = p.dni;";

    public InicializarDatabase() throws SQLException {

        Connection c = DriverManager.getConnection(URL, user, password);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(crearDB);
        URL = URL + "colegio";
        c = DriverManager.getConnection(URL, user, password);
        stmt = c.createStatement();
        stmt.executeUpdate(crearTablaPersonas);
        stmt.executeUpdate(crearTablaAlumnos);
        stmt.executeUpdate(crearTablaProfesores);
    }

    public ArrayList<Persona> cargarDatos() throws SQLException {
        personas = new ArrayList<>();
        Connection c = DriverManager.getConnection(URL, user, password);
        Statement stmt = c.createStatement();
        stmt.executeQuery(consultaPersonas);
        ResultSet rs = stmt.getResultSet();
        return cargarArray(rs);
    }

    public void aniadirPersonaDB(Persona p) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, "root", "");
        PreparedStatement stmt = connection.prepareStatement("insert into personas (dni, nombre, apellido, telefono) values (?, ?, ?,?)");
        stmt.setString(1, p.getDni());
        stmt.setString(2, p.getNombre());
        stmt.setString(3, p.getApellido());
        stmt.setString(4, p.getTelefono());
        stmt.executeUpdate();
        if (p.getRol().equals("Alumno")) {
            stmt = connection.prepareStatement("insert into alumnos (dni) values (?)");
            stmt.setString(1, p.getDni());
            stmt.executeUpdate();
        }
        if (p.getRol().equals("Profesor")) {
            stmt = connection.prepareStatement("insert into profesores (dni) values (?)");
            stmt.setString(1, p.getDni());
            stmt.executeUpdate();
        }
        if (p.getRol().equals("ProfesorAlumno")) {
            stmt = connection.prepareStatement("insert into profesores (dni) values (?)");
            stmt.setString(1, p.getDni());
            stmt.executeUpdate();
            stmt = connection.prepareStatement("insert into alumnos (dni) values (?)");
            stmt.setString(1, p.getDni());
            stmt.executeUpdate();
        }
    }

    public void borrarPersonaDB(String dni) throws SQLException {
        Connection c = DriverManager.getConnection(URL, user, password);
        PreparedStatement st = c.prepareStatement("delete from personas where dni=?");
        st.setString(1, dni);
        st.executeUpdate();
    }

    public void modificarPersonaDB(Persona personaNueva, Persona personaAntigua) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, user, password);
        PreparedStatement stmt = connection.prepareStatement("update personas  set nombre=?,  apellido=?, telefono=? where dni= ?");
        stmt.setString(1, personaNueva.getNombre());
        stmt.setString(2, personaNueva.getApellido());
        stmt.setString(3, personaNueva.getTelefono());
        stmt.setString(4, personaNueva.getDni());
        stmt.executeUpdate();
        if (personaAntigua.getRol().equals("ProfesorAlumno") && personaNueva.getRol().equals("Alumno")) {
            stmt = connection.prepareStatement("delete from profesores where dni=?");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
        } else if (personaAntigua.getRol().equals("ProfesorAlumno") && personaNueva.getRol().equals("Profesor")) {
            stmt = connection.prepareStatement("delete from alumnos where dni=?");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
        } else if (personaAntigua.getRol().equals("Profesor") && personaNueva.getRol().equals("Alumno")) {
            stmt = connection.prepareStatement("delete from profesores where dni=?");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
            stmt = connection.prepareStatement("insert into alumnos (dni) values (?)");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
        } else if (personaAntigua.getRol().equals("Alumno") && personaNueva.getRol().equals("Profesor")) {
            stmt = connection.prepareStatement("delete from alumnos where dni=?");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
            stmt = connection.prepareStatement("insert into profesores (dni) values (?)");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
        } else if (personaAntigua.getRol().equals("Profesor") && personaNueva.getRol().equals("ProfesorAlumno")) {
            stmt = connection.prepareStatement("insert into alumnos (dni) values (?)");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();

        } else if (personaAntigua.getRol().contains("Alumno") && personaNueva.getRol().equals("ProfesorAlumno")) {
            stmt = connection.prepareStatement("insert into profesores (dni) values (?)");
            stmt.setString(1, personaAntigua.getDni());
            stmt.executeUpdate();
        }
    }
    public ArrayList<Persona> ordenar(String orden) throws SQLException {
        personas = new ArrayList<>();
        Connection c = DriverManager.getConnection(URL, user, password);
        PreparedStatement stmt = c.prepareStatement("select p.dni, p.nombre, p.apellido, p.telefono,\n" +
                "       case when pr.dni is not null and a.dni is not null then 'ProfesorAlumno'\n" +
                "        when pr.dni is not null then 'Profesor'\n" +
                "        when a.dni is not null then 'Alumno' end as 'rol'\n" +
                "from personas p\n" +
                " left join profesores pr on pr.dni = p.dni\n" +
                "left  join alumnos a on a.dni = p.dni" +
                "order by ?;");
        stmt.setString(1, orden);
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        return cargarArray(rs);
    }
    public ArrayList<Persona> cargarArray(ResultSet rs) throws SQLException {
        ArrayList<Persona> personas = new ArrayList<>();
        while (rs.next()) {
            if (rs.getString(5).equals("ProfesorAlumno")) {
                Persona a = new Persona(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                personas.add(a);
            } else if (rs.getString(5).equals("Alumno")) {
                Persona a = new Persona(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                personas.add(a);
            } else if (rs.getString(5).equals("Profesor")) {
                Persona p = new Persona(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
                personas.add(p);
            }
        }
        return personas;
    }
}