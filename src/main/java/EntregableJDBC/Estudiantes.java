
package EntregableJDBC;


public class Estudiantes {
    private int id;
    private String nombre;
    private int edad;
    private String direccion;
    private String mail;

    public Estudiantes( String nombre, int edad, String direccion, String mail) {
        
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
