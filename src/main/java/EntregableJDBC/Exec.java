package EntregableJDBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Exec {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String respuesta = "";
        int idEst;
        int idCurs;
        String nombreEstudiante;
        String nombreCurso;

        consultas c = new consultas();

        c.crearTablas();

        int opcion = 0;

        while (opcion != 8) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Insertar nuevos estudiantes");
            System.out.println("2. Insertar nuevos cursos");
            System.out.println("3. Registrar inscripciones de estudiantes en cursos");
            System.out.println("4. Consultar la lista de estudiantes inscritos en un curso específico");
            System.out.println("5. Consultar los cursos en los que un estudiante particular está inscrito");
            System.out.println("6. Actualizar información de estudiantes o cursos");
            System.out.println("7. Eliminar registros de estudiantes, cursos o inscripciones");
            System.out.println("8. Salir");

            opcion = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea

            if (opcion == 1) {
                String nombre, direccion, mail;
                int edad;
                System.out.println("Introduzca el nombre del estudiane: ");
                nombre = sc.nextLine();

                System.out.println("Introduzca la edad: ");
                edad = Integer.parseInt(sc.nextLine());

                System.out.println("Introduzca la direccion: ");
                direccion = sc.nextLine();

                System.out.println("Introduzca el correo: ");
                mail = sc.nextLine();

                Estudiantes e = new Estudiantes(nombre, edad, direccion, mail);

                c.insertarEstudiantes(e);

            } else if (opcion == 2) {
                String nombreCursos, descripcion;
                double creditos;

                System.out.println("Introduzca el nombre del curso: ");
                nombreCurso = sc.nextLine();

                System.out.println("Introduzca una breve descripcion: ");
                descripcion = sc.nextLine();

                System.out.println("Introduzca el numero de créditos: ");
                creditos = sc.nextDouble();

                Cursos cu = new Cursos(nombreCurso, descripcion, creditos);
                c.insertarCursos(cu);

            } else if (opcion == 3) {
                boolean salir = false;
                while (!salir) {
                    System.out.println("Menú de Inscripciones:");

                    System.out.println("1. Registrar inscripción");

                    System.out.println("2. Salir");

                    int opcionIns = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea

                    switch (opcionIns) {
                        case 1:
                            Date fecha;
                            String answ;

                            System.out.println("Selecciona el nombre del estudiante a inscribir: ");
                            nombreEstudiante = sc.nextLine();
                            c.mostrarAlumnosPorNombre(nombreEstudiante);

                            System.out.println("Elija el id del estudiante de la siguiente lista: ");
                            idEst = sc.nextInt();
                            sc.nextLine(); // Consumir el salto de línea

                            System.out.println("Selecciona el nombre del curso a inscribir: ");
                            nombreCurso = sc.nextLine();
                            c.mostrarCursosPorNombre(nombreCurso);

                            System.out.println("Elija el id del curso en el que se quiera inscribir al alumno: ");
                            idCurs = sc.nextInt();
                            sc.nextLine(); // Consumir el salto de línea

                            fecha = Date.valueOf(LocalDate.now()); // Fecha actual
                            System.out.println("La fecha de inscripción es: " + fecha);

                            System.out.println("¿Desea hacer esa inscripción (Si/No)?");
                            answ = sc.nextLine();

                            if (answ.equalsIgnoreCase("Si")) {
                                c.insertarAlumnosEnCursos(idCurs, idEst, fecha);
                                System.out.println("Inscripción realizada con éxito.");
                            } else {
                                System.out.println("Inscripción cancelada.");
                            }
                            break;

                        case 2:
                            salir = true;
                            break;

                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                            break;
                    }
                }
                // Registrar inscripciones de estudiantes en cursos

            } else if (opcion == 4) {

                System.out.println("Selecciona el nombre del curso a consultar: ");
                nombreCurso = sc.nextLine();
                c.mostrarCursosPorNombre(nombreCurso);
                System.out.println("Seleccione el ID del curso a consultar: ");

                idCurs = sc.nextInt();
                c.mostrarCursosConId(idCurs);

                // Consultar la lista de estudiantes inscritos en un curso específico
                // Aquí debes escribir el código para esta operación
            } else if (opcion == 5) {

                System.out.println("Selecciona el nombre del estudiante: ");
                nombreEstudiante = sc.nextLine();
                c.mostrarAlumnosPorNombre(nombreEstudiante);

                System.out.println("Seleccione el id del estudiante a consultar: ");

                idEst = sc.nextInt();
                c.mostrarCursosDeUnEstudiante(idEst);
                // Consultar los cursos en los que un estudiante particular está inscrito
                // Aquí debes escribir el código para esta operación

            } else if (opcion == 6) {
                while (true) {
                    System.out.println("Menú de Actualización de Información");
                    System.out.println("1. Actualizar información de estudiantes");
                    System.out.println("2. Actualizar información de cursos");
                    System.out.println("3. Volver al menú principal");
                    System.out.print("Seleccione una opción: ");

                    int opcion6 = sc.nextInt();
                    sc.nextLine();

                    switch (opcion6) {
                        case 1:
                            // Lógica para actualizar información de estudiantes
                            System.out.println("Selecciona el nombre del estudiante a cambiar: ");
                            nombreEstudiante = sc.nextLine();
                            c.mostrarAlumnosPorNombre(nombreEstudiante);

                            System.out.println("Seleccione el id del estudiante a cambiar: ");
                            idEst = sc.nextInt();

                            System.out.println("¿Qué datos deseas cambiar?");
                            System.out.println("1. Cambiar nombre");
                            System.out.println("2. Cambiar edad");
                            System.out.println("3. Cambiar dirección");
                            System.out.println("4. Cambiar correo");
                            System.out.println("5. Volver al menú principal");
                            System.out.print("Seleccione una opción: ");
                            int opcion61 = sc.nextInt();

                            switch (opcion61) {
                                case 1:
                                    // Lógica para cambiar el nombre del estudiante
                                    System.out.print("Ingrese el nuevo nombre: ");

                                    String nuevoNombreUp = sc.next();

                                    c.cambiarNombreEstudiante(idEst, nuevoNombreUp);
                                    break;

                                case 2:
                                    // Lógica para cambiar la edad del estudiante
                                    System.out.print("Ingrese la nueva edad: ");
                                    int nuevaEdad = sc.nextInt();
                                    c.cambiarEdadEstudiante(idEst, nuevaEdad);
                                    break;

                                case 3:
                                    System.out.print("Ingrese la nueva dirección: ");
                                    String nuevaDireccion = sc.next();
                                    c.cambiarDireccionEstudiante(idEst, nuevaDireccion);
                                    break;
                                case 4:
                                    System.out.print("Ingrese el nuevo correo: ");
                                    String nuevoCorreo = sc.next();
                                    c.cambiarCorreoEstudiante(idEst, nuevoCorreo);
                                    break;
                                case 5:
                                    return; // Regresar al menú principal
                                default:
                                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                            }

                            break;
                        case 2:
                            System.out.println("Selecciona el nombre del curso a cambiar: ");
                            nombreCurso = sc.nextLine();
                            c.mostrarCursosPorNombre(nombreCurso);

                            System.out.println("Seleccione el id del curso a cambiar: ");
                            idCurs = sc.nextInt();

                            System.out.println("¿Qué datos deseas cambiar?");
                            System.out.println("1. Cambiar nombre del curso");
                            System.out.println("2. Cambiar la descripcion");
                            System.out.println("3. Cambiar los creditos");
                            System.out.print("Seleccione una opción: ");
                            int opcion62 = sc.nextInt();

                            switch (opcion62) {
                                case 1:
                                    // Lógica para cambiar el nombre del estudiante
                                    System.out.print("Ingrese el nuevo nombre del curso: ");

                                    String nuevoNombreCurso = sc.next();

                                    c.cambiarNombreCurso(idCurs, nuevoNombreCurso);
                                    break;

                                case 2:
                                    // Lógica para cambiar la edad del estudiante
                                    System.out.print("Ingrese la nueva descripcion: ");
                                    String nuevaDescripcion = sc.nextLine();
                                    c.cambiarDescripcionCurso(idCurs, nuevaDescripcion);
                                    break;

                                case 3:
                                    System.out.print("Ingrese los nuevos creditos: ");
                                    int nuevosCreditos = sc.nextInt();
                                    c.cambiarCreditosCurso(idCurs, nuevosCreditos);
                                    break;
                                case 4:
                                    return; // Regresar al menú principal
                                default:
                                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                            }

                            break;

                        case 3:
                            return; // Regresar al menú principal
                        default:
                            System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                            break;
                    }
                }
                // Actualizar información de estudiantes o cursos
                // Aquí debes escribir el código para esta operación

            } else if (opcion == 7) {
                boolean salir = false;

                while (!salir) {
                    System.out.println("Menú de Borrado:");
                    System.out.println("1. Borrar Alumnos");
                    System.out.println("2. Borrar Cursos");
                    System.out.println("3. Borrar Inscripciones");
                    System.out.println("4. Salir");

                    int opDel = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea

                    switch (opDel) {
                        case 1:
                            System.out.println("Selecciona el nombre del estudiante a borrar: ");
                            nombreEstudiante = sc.nextLine();
                            c.mostrarAlumnosPorNombre(nombreEstudiante);

                            System.out.println("Elija el ID del alumno de la lista que desea borrar: ");

                            idEst = sc.nextInt();
                            sc.nextLine(); // Consumir el salto de línea
                            c.borrarEstudiantes(idEst);
                            System.out.println("Alumno con ID " + idEst + " ha sido borrado.");
                            break;

                        case 2:
                            System.out.println("Selecciona el nombre del curso a borrar: ");
                            nombreCurso = sc.nextLine();
                            c.mostrarCursosPorNombre(nombreCurso);
                            System.out.println("Elija el ID del curso de la lista que desea borrar: ");
                            c.mostrarCursos();

                            idCurs = sc.nextInt();
                            sc.nextLine(); // Consumir el salto de línea
                            c.borrarCursos(idCurs);
                            System.out.println("Curso con ID " + idCurs + " ha sido borrado.");

                            break;

                        case 3:
                            System.out.println("Lista de Inscripciones:");
                            c.mostrarInscripciones();
                            System.out.println("Elija el ID de la inscripción que desea borrar: ");
                            int idInscripcion = sc.nextInt();
                            sc.nextLine(); // Consumir el salto de línea
                            c.borrarInscripciones(idInscripcion);
                            System.out.println("Inscripción con ID " + idInscripcion + " ha sido borrada.");
                            break;

                        case 4:
                            salir = true;
                            break;

                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                            break;
                    }
                }
                // Eliminar registros de estudiantes, cursos o inscripciones

            } else if (opcion == 8) {
                // Salir del programa

                System.out.println("Saliendo del programa.");
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

}
