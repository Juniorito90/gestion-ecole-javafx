package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    IUser iUser = new UserImpl();

    @FXML
    private Button connexionBtn;

    @FXML
    private TextField emailTxt;

    @FXML
    private Button inscriptionBtn;

    @FXML
    private TextField loginTxt;

    @FXML
    private TextField nomTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private TextField prenomTxt;

    @FXML
    private TextField telephoneTxt;

    @FXML
    void onRegister(ActionEvent event) {
        String nom = nomTxt.getText();
        String prenom = prenomTxt.getText();
        String telephone = telephoneTxt.getText();
        String email = emailTxt.getText();
        String login = loginTxt.getText();
        String password = passwordTxt.getText();

        boolean rs = iUser.add(nom, prenom, telephone, email, login, password);
        Alert alert;

        if(nomTxt.getText() == "" || prenomTxt.getText() == "" || telephoneTxt.getText() == "" ||
                emailTxt.getText() == "" || loginTxt.getText() == "" || passwordTxt.getText() == "") {

            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez remplir correctement les champs");
            alert.setResizable(false);
            alert.show();

        }else {
            if (rs) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Insertion réussie");
                alert.setResizable(false);
                alert.show();

                nomTxt.clear();
                prenomTxt.clear();
                telephoneTxt.clear();
                emailTxt.clear();
                loginTxt.clear();
                passwordTxt.clear();
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Insertion incorrecte");
                alert.setResizable(false);
                alert.show();
            }
        }
    }

    @FXML
    void openConnexionFn(ActionEvent event) {
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Connexion.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Connexion");
            // Obtenez la fenêtre actuelle (la fenêtre de connexion)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Fermez la fenêtre actuelle lors de l'ouverture de la fenêtre d'inscription
            primaryStage.setOnShown(e -> currentStage.close());
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
