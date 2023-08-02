package application.ndiaya;

public class ChartPattern {
    private String libelle;
    private double value;

    public ChartPattern(String libelle, double value) {
        this.libelle = libelle;
        this.value = value;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getValue() {
        return value;
    }
}
