package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.File;
import java.util.List;

public class ExtractionController {

    @FXML
    private Button listeEtudiantBtn;

    @FXML
    private Button listeEtudiantParClasseBtn;

    private EtudiantImpl etudiantImpl = new EtudiantImpl();
    @FXML
    void listeEtudiantPDF(ActionEvent event) {
        List<Etudiant> etudiants = etudiantImpl.list();// Obtenez la liste des étudiants à partir de votre source de données

        // Obtenez le répertoire de travail actuel (racine du projet)
        String projectDir = System.getProperty("user.dir");

        // Définissez le nom du fichier de sortie
        String filename = "liste_etudiants.pdf";

        // Construisez le chemin complet du fichier de sortie
        String outputPath = projectDir + File.separator + filename;

        // Vérifier si le fichier existe déjà, si oui, supprimer le fichier existant
        File outputFile = new File(outputPath);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        // Appeler la méthode pour générer le PDF
        EtudiantPdfGenerator.generatePdf(etudiants, outputPath);

        // Afficher une alerte pour informer l'utilisateur que le fichier a été généré
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Génération PDF");
        alert.setHeaderText("Le fichier PDF a été généré avec succès !");
        alert.setContentText("Le fichier a été enregistré à l'emplacement : " + outputPath);
        alert.showAndWait();
    }

    @FXML
    void listeEtudiantParClasseExcel(ActionEvent event) {
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Non Disponible!");
        alert.setResizable(false);
        alert.show();
    }

    // Méthode utilitaire pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}