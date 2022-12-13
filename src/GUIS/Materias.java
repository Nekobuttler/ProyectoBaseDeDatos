package GUIS;

public class Materias {

    private int IdMateria;

    private String NombreMateria;

    public Materias(){

    }

    public Materias(int IdMateria, String NombreMateria){

        this.IdMateria = IdMateria;
        this.NombreMateria = NombreMateria;
    }

    public int getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(int idMateria) {
        IdMateria = idMateria;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }
}
