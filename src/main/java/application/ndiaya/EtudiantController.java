package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EtudiantController {

    @FXML
    private Button AjouterBtn;

    @FXML
    private Button ClasseMenu;

    @FXML
    private TableColumn<?, ?> EmailCol;

    @FXML
    private TableColumn<?, ?> IDCol;

    @FXML
    private TableColumn<?, ?> LoginCol;

    @FXML
    private TableColumn<?, ?> NomCol;

    @FXML
    private TableColumn<?, ?> PasswordCol;

    @FXML
    private TableColumn<?, ?> PrenomCol;

    @FXML
    private Button StatistiqueMenu;

    @FXML
    private TableColumn<?, ?> TelephoneCol;

    @FXML
    private Button extraireBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private TableView<?> tbViewEtudiant;

    @FXML
    void openAddEtudiantFn(ActionEvent event) {
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddEtudiant.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Ajouter un Etudiant");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openClasseFn(ActionEvent event) {
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Classe.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Classes");
            // Obtenez la fenêtre actuelle (la fenêtre de connexion)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Fermez la fenêtre actuelle lors de l'ouverture de la fenêtre d'inscription
            primaryStage.setOnShown(e -> currentStage.close());
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openExtraireFn(ActionEvent event) {

    }

    @FXML
    void openStatistiqueFn(ActionEvent event) {

    }

    @FXML
    void refreshTable(ActionEvent event) {

    }

}
