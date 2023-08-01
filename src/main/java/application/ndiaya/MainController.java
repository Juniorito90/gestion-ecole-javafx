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

public class MainController {

    IUser iUser = new UserImpl();

    @FXML
    private Button connexionBtn;

    @FXML
    private Button inscriptionBtn;

    @FXML
    private TextField loginTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    void onConnect(ActionEvent event) {
        String login = loginTxt.getText();
        String password = passwordTxt.getText();
        boolean rs = iUser.login(login, password);
        Alert alert;
        if(login == "" || password == ""){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez remplir correctement les champs");
            alert.setResizable(false);
            alert.show();
        }else{
            if(rs) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Bienvenue Utilisateur : "+login);
                alert.setHeaderText("Connexion réussie");
                alert.setResizable(false);
                alert.show();

                Stage primaryStage = new Stage();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("Etudiant.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.centerOnScreen();
                    primaryStage.initModality(Modality.APPLICATION_MODAL);
                    primaryStage.setResizable(false);
                    primaryStage.setTitle("Etudiants");
                    // Obtenez la fenêtre actuelle (la fenêtre de connexion)
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    // Fermez la fenêtre actuelle lors de l'ouverture de la fenêtre d'inscription
                    primaryStage.setOnShown(e -> currentStage.close());
                    primaryStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Veuillez vérifier votre login ou votre mot de passe");
                alert.setHeaderText("Connexion échouée");
                alert.setResizable(false);
                alert.show();
            }
        }
    }

    @FXML
    void openInscriptionFn(ActionEvent event) {
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Inscription");
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
