package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditClasseController {

    IClasse iClasse = new ClasseImpl();

    @FXML
    private TextField libelleTxt;

    @FXML
    private Button modifierBtn;

    @FXML
    void onEdit(ActionEvent event) {
        String newLibelle = libelleTxt.getText();

        if(newLibelle.isEmpty()){
            Alert alert;
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez saisir le libelle");
            alert.setResizable(false);
            alert.show();
        }else{
            boolean rs = iClasse.edit(classeToEdit.getId(), newLibelle);
            if(rs) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Modification réussie");
                alert.setResizable(false);
                libelleTxt.clear();
                alert.show();

                libelleTxt.clear();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Modification incorrecte");
                alert.setResizable(false);
                alert.show();
            }
        }
    }

    private Classe classeToEdit;
    public void setClasseToEdit(Classe classe) {
        this.classeToEdit = classe;
        libelleTxt.setText(classe.getLibelle());
    }

}