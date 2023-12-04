package EntregableJDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultas {

    private String url = "jdbc:mariadb://localhost:3306/instituto";
    private String user = "root";
    private String pass = "Europa";

    public consultas() {
    }

    public void crearTablas() {
        Connection con = null;
        PreparedStatement stmTabEst = null;
        PreparedStatement stmTabCursos = null;
        PreparedStatement stmTabIns = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);
            String queryTableEstudiantes = "CREATE TABLE IF NOT EXISTS estudiantes ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre VARCHAR(50),"
                    + " edad INT,"
                    + " direccion VARCHAR(50),"
                    + " Correo_electronico VARCHAR(50));";

            String queryTableCursos = "CREATE TABLE IF NOT EXISTS cursos ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nombre_del_curso VARCHAR(50),"
                    + " descripcion VARCHAR(50),"
                    + " creditos INT);";

            String queryTableInscripciones = "CREATE TABLE IF NOT EXISTS inscripciones ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + " estudiante_id INT,"
                    + " curso_id INT,Fecha_Inscripcion DATE,"
                    + " FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id),"
                    + " FOREIGN KEY (curso_id) REFERENCES cursos(id)"
                    + "ON DELETE CASCADE ON UPDATE CASCADE);";

            stmTabEst = con.prepareStatement(queryTableEstudiantes);
            stmTabCursos = con.prepareStatement(queryTableCursos);
            stmTabIns = con.prepareStatement(queryTableInscripciones);

            int rs = stmTabEst.executeUpdate();
            int rs1 = stmTabCursos.executeUpdate();
            int rs2 = stmTabIns.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void insertarEstudiantes(Estudiantes e) {
        Connection con = null;
        PreparedStatement instEst = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String insertQuery = "INSERT INTO estudiantes(nombre, edad, direccion, Correo_electronico) VALUES(?,?,?,?)";
            instEst = con.prepareStatement(insertQuery);
            instEst.setString(1, e.getNombre());
            instEst.setInt(2, e.getEdad());
            instEst.setString(3, e.getDireccion());
            instEst.setString(4, e.getMail());
            instEst.execute();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void borrarEstudiantes(int idEst) {
        Connection con = null;
        PreparedStatement delEst = null;

        try {

            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String delQuery = "DELETE FROM estudiantes WHERE id = ?;";
            delEst = con.prepareStatement(delQuery);
            delEst.setInt(1, idEst);

            delEst.execute();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void borrarInscripciones(int idInsc) {
        Connection con = null;
        PreparedStatement delInsc = null;

        try {

            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String delQuery = "DELETE FROM inscripciones WHERE id = ?;";
            delInsc = con.prepareStatement(delQuery);
            delInsc.setInt(1, idInsc);

            delInsc.execute();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void borrarCursos(int idCurs) {
        Connection con = null;
        PreparedStatement delEst = null;

        try {

            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String delQuery = "DELETE FROM cursos WHERE id = ?;";
            delEst = con.prepareStatement(delQuery);
            delEst.setInt(1, idCurs);

            delEst.execute();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void insertarCursos(Cursos c) {
        Connection conn = null;
        PreparedStatement instCurs = null;

        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pass);

            String insertQuery = "INSERT INTO cursos(nombre_del_curso, descripcion, creditos) VALUES(?,?,?)";
            instCurs = conn.prepareStatement(insertQuery);
            instCurs.setString(1, c.getNombreCurso());
            instCurs.setString(2, c.getDescripción());
            instCurs.setDouble(3, c.getCreditos());

            instCurs.execute();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void insertarAlumnosEnCursos(int idCurs, int idEst, Date fecha) {
        Connection conn = null;
        PreparedStatement instInsc = null;

        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pass);

            String insertQuery = "INSERT INTO Inscripciones (Estudiante_ID, Curso_ID, Fecha_Inscripcion) VALUES (?, ?, ?)";
            instInsc = conn.prepareStatement(insertQuery);
            instInsc.setInt(1, idEst);
            instInsc.setInt(2, idCurs);
            instInsc.setDate(3, fecha);

            instInsc.execute();
            

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarAlumnos() {
        Connection con = null;

        PreparedStatement consultaAlumnos = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostarAlumnos = "SELECT id,nombre FROM estudiantes";

            consultaAlumnos = con.prepareStatement(mostarAlumnos);

            rs = consultaAlumnos.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1) + " Nombre: " + rs.getNString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarAlumnosPorNombre(String nombreEstUpd) {
        Connection con = null;

        PreparedStatement consultaAlumnosPorNombre = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostarAlumnosPorNombre = "SELECT * FROM estudiantes WHERE nombre LIKE ?";

            consultaAlumnosPorNombre = con.prepareStatement(mostarAlumnosPorNombre);
            consultaAlumnosPorNombre.setString(1, nombreEstUpd + "%");
            rs = consultaAlumnosPorNombre.executeQuery();

            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1) + " Nombre: " + rs.getNString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarCursosPorNombre(String nombreCursUpd) {
        Connection con = null;

        PreparedStatement consultaCursosPorNombre = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostarCursosPorNombre = "SELECT * FROM cursos WHERE nombre_del_curso LIKE ?";

            consultaCursosPorNombre = con.prepareStatement(mostarCursosPorNombre);

            consultaCursosPorNombre.setString(1, nombreCursUpd + "%");

            rs = consultaCursosPorNombre.executeQuery();

            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1) + " Nombre: " + rs.getNString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarCursos() {
        Connection con = null;

        PreparedStatement consultaCursos = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostrarCursos = "SELECT id, nombre_del_curso FROM cursos";

            consultaCursos = con.prepareStatement(mostrarCursos);

            rs = consultaCursos.executeQuery();

            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1) + " Nombre: " + rs.getNString(2));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarInscripciones() {
        Connection con = null;

        PreparedStatement consultaInscripciones = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostrarInscripciones = "SELECT id, Estudiante_ID, Curso_id FROM inscripciones";

            consultaInscripciones = con.prepareStatement(mostrarInscripciones);

            rs = consultaInscripciones.executeQuery();
            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1) + " id del alumno: " + rs.getInt(2) + " id del curso: " + rs.getInt(3));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarCursosConId(int idCurs) {
        Connection con = null;

        PreparedStatement consultaCursosPorID = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostrarCursosPorID = "SELECT estudiantes.nombre "
                    + "FROM estudiantes "
                    + "JOIN inscripciones ON estudiantes.id = inscripciones.estudiante_id "
                    + "WHERE inscripciones.curso_id = ?";

            consultaCursosPorID = con.prepareStatement(mostrarCursosPorID);
            consultaCursosPorID.setInt(1, idCurs);

            rs = consultaCursosPorID.executeQuery();

            while (rs.next()) {
                System.out.println(" Nombre: " + rs.getNString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }

    public void mostrarCursosDeUnEstudiante(int idEst) {
        Connection con = null;

        PreparedStatement consultaCursosEstudiantePorID = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String mostrarCursosEstudiantesPorID = "SELECT cursos.nombre_del_curso "
                    + "FROM cursos "
                    + "JOIN inscripciones ON cursos.id = inscripciones.curso_id "
                    + "WHERE inscripciones.estudiante_id = ?";

            consultaCursosEstudiantePorID = con.prepareStatement(mostrarCursosEstudiantesPorID);
            consultaCursosEstudiantePorID.setInt(1, idEst);

            rs = consultaCursosEstudiantePorID.executeQuery();

            while (rs.next()) {
                System.out.println(" Nombre del curso: " + rs.getNString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }

    }
public void cambiarNombreEstudiante(int idEst, String nuevoNombre) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            // Consulta SQL para actualizar el nombre del estudiante
             String sql = "UPDATE estudiantes SET nombre = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1, nuevoNombre);
            updateStatement.setInt(2, idEst);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nombre del estudiante actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún estudiante con el nombre especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el nombre del estudiante: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
    
    public void cambiarEdadEstudiante(int idEst, int nuevaEdad) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            // Consulta SQL para actualizar el nombre del estudiante
            String sql = "UPDATE estudiantes SET edad = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setInt(1,nuevaEdad );
            updateStatement.setInt(2, idEst);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Edad del estudiante actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún estudiante con la edad especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la edad del estudiante: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
     public void cambiarDireccionEstudiante(int idEst, String nuevaDireccion) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            // Consulta SQL para actualizar el nombre del estudiante
            String sql = "UPDATE estudiantes SET direccion = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1,nuevaDireccion );
            updateStatement.setInt(2, idEst);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("direccion del estudiante actualizada con éxito.");
            } else {
                System.out.println("No se encontró ningún estudiante con la dirección especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la dirección del estudiante: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
     public void cambiarCorreoEstudiante(int idEst, String nuevoCorreo) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            // Consulta SQL para actualizar el nombre del estudiante
            String sql = "UPDATE estudiantes SET Correo_electronico = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1,nuevoCorreo );
            updateStatement.setInt(2, idEst);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("correo del estudiante actualizada con éxito.");
            } else {
                System.out.println("No se encontró ningún estudiante con el correo especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el correo del estudiante: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
      public void cambiarNombreCurso(int idCurs, String nuevoNombreCurso) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String sql = "UPDATE cursos SET nombre_del_curso = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1,nuevoNombreCurso );
            updateStatement.setInt(2, idCurs);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nombre del curso actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún curso con el nombre especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el nombre del curso: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
      
    public void cambiarDescripcionCurso(int idCurs, String nuevoDescripcionCurso) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String sql = "UPDATE cursos SET nombre_del_curso = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1,nuevoDescripcionCurso );
            updateStatement.setInt(2, idCurs);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Descripcion del curso actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún curso con la descripcion especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la descripcion del curso: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
    
    public void cambiarCreditosCurso(int idCurs, int nuevoCreditoCurso) {
        Connection con = null;
        PreparedStatement updateStatement = null;

        try {
            con = DriverManager.getConnection(this.url, this.user, this.pass);

            String sql = "UPDATE cursos SET nombre_del_curso = ? WHERE id = ?";

            updateStatement = con.prepareStatement(sql);
            updateStatement.setInt(1,nuevoCreditoCurso );
            updateStatement.setInt(2, idCurs);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Descripcion del curso actualizado con éxito.");
            } else {
                System.out.println("No se encontró ningún curso con la descripcion especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la descripcion del curso: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
