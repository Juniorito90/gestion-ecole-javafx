package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddClasseController {

    IClasse iClasse = new ClasseImpl();
    @FXML
    private Button ajouterBtn;

    @FXML
    private TextField libelleTxt;

    @FXML
    void onAdd(ActionEvent event) {
        String libelle = libelleTxt.getText();
        boolean rs = iClasse.add(libelle);
        Alert alert;

        if(libelleTxt.getText() == "") {

            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez remplir correctement les champs");
            alert.setResizable(false);
            alert.show();

        }else {
            if(rs) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Insertion réussie");
                alert.setResizable(false);
                alert.show();

                libelleTxt.clear();
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Insertion incorrecte");
                alert.setResizable(false);
                alert.show();
            }
        }
    }

}