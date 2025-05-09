package Persistencia;

import Logica.Persona;

import java.sql.*;
import java.util.ArrayList;

public class InicializarDatabase {
    private String URL = "jdbc:mariadb://localhost:3306/";
    private String user = "root";
    private String password = "";
    private String crearDB = "create database if not exists prog_alumnoProfe";
    private String crearTablaPersonas = "create table if not exists personas (dni varchar(9) primary key ,\n" +
            "            nombre varchar(30), apellido varchar(30), telefono varchar(12));";
    private String crearTablaAlumnos = "create table if not exists alumnos(id_alumn int primary key auto_increment,\n" +
            "                                               dni varchar(9) not null,\n" +
            "                foreign key fk_alumn (dni) references personas(dni) on delete cascade);";
    private String crearTablaProfesores = "create table if not exists profesores(id_prof int primary key auto_increment,\n" +
            "                                         dni varchar(9) not null,\n" +
            "                                           foreign key fk_prof (dni) references personas(dni) on delete cascade);";
    private ArrayList<Persona> personas;
    private String consultaPersonas = "select p.dni, p.nombre, p.apellido, p.telefono, 'profesor'\n" +
            "from personas p\n" +
            "         join profesores pr on pr.dni= p.dni\n" +
            "union\n" +
            "select p.dni, p.nombre, p.apellido, p.telefono, 'alumno'\n" +
            "from personas p\n" +
            "         join alumnos a on a.dni=p.dni;";

    public InicializarDatabase() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, user, password);
            Statement stmt = c.createStatement();
            stmt.executeUpdate(crearDB);
            URL = URL + "prog_alumnoProfe";
            c = DriverManager.getConnection(URL, user, password);
            stmt = c.createStatement();
            stmt.executeUpdate(crearTablaPersonas);
            stmt.executeUpdate(crearTablaAlumnos);
            stmt.executeUpdate(crearTablaProfesores);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Persona> cargarDatos() {
        personas = new ArrayList<>();
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, user, password);
            Statement stmt = c.createStatement();
            stmt.executeQuery(consultaPersonas);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                if (rs.getString(5).equals("alumno")) {
                    Persona a = new Persona(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5));
                    personas.add(a);
                }
                if (rs.getString(5).equals("profesor")) {
                    Persona p = new Persona(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5));
                    personas.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personas;
    }

    public void aniadirPersonaDB(Persona p) {
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into personas (dni, nombre, apellido, telefono) values (?, ?, ?,?)");
            preparedStatement.setString(1, p.getDni());
            preparedStatement.setString(2, p.getNombre());
            preparedStatement.setString(3, p.getApellido());
            preparedStatement.setString(4, p.getTelefono());
            preparedStatement.executeUpdate();
            if (p.getRol().equals("alumno")) {
                preparedStatement = connection.prepareStatement("insert into alumnos (dni) values (?)");
                preparedStatement.setString(1, p.getDni());
                preparedStatement.executeUpdate();
            }
            if (p.getRol().equals("profesor")) {
                preparedStatement = connection.prepareStatement("insert into profesores (dni) values (?)");
                preparedStatement.setString(1, p.getDni());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarPersonaDB(String dni) throws SQLException {
        Connection c = DriverManager.getConnection(URL, user, password);
        PreparedStatement st = c.prepareStatement("delete from personas where dni=?");
        st.setString(1, dni);
        st.executeUpdate();
    }
}