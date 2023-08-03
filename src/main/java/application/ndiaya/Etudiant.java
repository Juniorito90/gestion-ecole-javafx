package application.ndiaya;

public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private int idClasse;

    // Attribut pour stocker la classe associée à l'étudiant
    private Classe classe;

    public Etudiant(int id, String nom, String prenom, String adresse, int idClasse, Classe classe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.idClasse = idClasse;
        this.classe = classe;
    }

    public Etudiant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
