package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AddEtudiantController {

    IEtudiant iEtudiant = new EtudiantImpl();
    @FXML
    private Button AjouterBtn;

    @FXML
    private TextField adresseTxt;

    @FXML
    private ComboBox<Classe> classeCb;

    @FXML
    private TextField nomTxt;

    @FXML
    private TextField prenomTxt;

    @FXML
    void onAdd(ActionEvent event) {
        String nom = nomTxt.getText().trim();
        String prenom = prenomTxt.getText().trim();
        String adresse = adresseTxt.getText().trim();
        Classe classe = classeCb.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || classe == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez remplir correctement tous les champs");
            alert.setResizable(false);
            alert.show();
        } else {
            int idClasse = classe.getId();
            boolean rs = iEtudiant.add(nom, prenom, adresse, idClasse);
            Alert alert;

            if (rs) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Insertion réussie");
                alert.setResizable(false);
                alert.show();

                nomTxt.clear();
                prenomTxt.clear();
                adresseTxt.clear();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Insertion incorrecte");
                alert.setResizable(false);
                alert.show();
            }
        }
    }


    @FXML
    void initialize(){
        IClasse iClasse = new ClasseImpl();
        List<Classe> classes = iClasse.list();

        classeCb.getItems().addAll(classes);
    }

}