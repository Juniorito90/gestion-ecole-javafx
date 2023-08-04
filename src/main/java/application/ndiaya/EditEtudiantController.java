package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class EditEtudiantController {

    IEtudiant iEtudiant = new EtudiantImpl();
    IClasse iClasse = new ClasseImpl();

    @FXML
    private TextField nomTxt;

    @FXML
    private TextField prenomTxt;

    @FXML
    private TextField adresseTxt;

    @FXML
    private ComboBox<Classe> classeCb; // Utilisation de l'id "classeCb"

    @FXML
    private Button modifierBtn;

    @FXML
    void onEdit(ActionEvent event) {
        String newNom = nomTxt.getText();
        String newPrenom = prenomTxt.getText();
        String newAdresse = adresseTxt.getText();
        Classe newClasse = classeCb.getValue();

        if (newNom.isEmpty() || newPrenom.isEmpty() || newAdresse.isEmpty() || newClasse == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez remplir tous les champs!");
            alert.setResizable(false);
            alert.show();
        } else {
            // Vérifier si l'utilisateur a choisi une nouvelle classe dans le ComboBox
            int selectedClasseIndex = classeCb.getSelectionModel().getSelectedIndex();
            Classe oldClasse = etudiantToEdit.getClasse();

            // Si l'utilisateur n'a pas choisi une nouvelle classe, conserver la classe actuelle
            if (selectedClasseIndex == -1) {
                newClasse = oldClasse;
            }

            if (newClasse == null) {
                // Handle the case where the user did not select a new class
                newClasse = oldClasse;
            }

            boolean rs = iEtudiant.edit(etudiantToEdit.getId(), newNom, newPrenom, newAdresse, newClasse.getId());
            if (rs) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Modification réussie");
                alert.setResizable(false);
                alert.show();

                nomTxt.clear();
                prenomTxt.clear();
                adresseTxt.clear();
                classeCb.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Modification incorrecte");
                alert.setResizable(false);
                alert.show();
            }
        }
    }

    private Etudiant etudiantToEdit;

    public void setEtudiantToEdit(Etudiant etudiant) {
        this.etudiantToEdit = etudiant;
        if (etudiant != null) {
            nomTxt.setText(etudiant.getNom());
            prenomTxt.setText(etudiant.getPrenom());
            adresseTxt.setText(etudiant.getAdresse());

            // Effacer les éléments existants du ComboBox avant d'en ajouter de nouveaux
            classeCb.getItems().clear();

            // Remplir le ComboBox avec les classes disponibles
            List<Classe> classes = iClasse.list();
            classeCb.getItems().addAll(classes);

            // Pré-sélectionner la classe de l'étudiant dans le ComboBox
            Classe classe = etudiant.getClasse();
            classeCb.getSelectionModel().select(classe);
        }
    }
}
