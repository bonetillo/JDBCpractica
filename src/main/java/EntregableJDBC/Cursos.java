
package EntregableJDBC;

public class Cursos {
    private int id;
    private String nombreCurso;
    private String descripción;
    private double creditos;

    public Cursos( String nombreCurso, String descripción, double creditos) {
        
        this.nombreCurso = nombreCurso;
        this.descripción = descripción;
        this.creditos = creditos;
    }

    public double getCreditos() {
        return creditos;
    }

    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
    
    
}
