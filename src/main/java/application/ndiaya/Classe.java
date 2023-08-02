package application.ndiaya;

public class Classe {
    public int id;
    public String libelle;

    public Classe(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Classe() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle; // Retourner le libellé de la classe comme représentation textuelle
    }
}
