package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClasseController {

    @FXML
    private Button AjouterBtn;

    @FXML
    private Button modifierBtn;

    @FXML
    private Button supprimerBtn;

    @FXML
    private Button EtudiantMenu;

    @FXML
    private TableColumn<Classe, Integer> IDCol;

    @FXML
    private TableColumn<Classe, String> LibelleCol;

    @FXML
    private Button StatistiqueMenu;

    @FXML
    private Button refreshBtn;

    @FXML
    private TableView<Classe> tbViewClasse;

    @FXML
    void openAddClasseFn(ActionEvent event) {
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddClasse.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setUserData(null);
            primaryStage.setTitle("Ajouter une Classe");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openEtudiantFn(ActionEvent event) {
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
    }

    @FXML
    void openStatistiqueFn(ActionEvent event) {
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Statistiques");
            // Obtenez la fenêtre actuelle (la fenêtre de connexion)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Fermez la fenêtre actuelle lors de l'ouverture de la fenêtre d'inscription
            primaryStage.setOnShown(e -> currentStage.close());
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ClasseImpl classeImpl = new ClasseImpl();
    @FXML
    void refreshTable(ActionEvent event) {
        // Rafraîchir la liste des classes après l'ajout d'un nouvelle classe
        List<Classe> classeList = classeImpl.list();
        tbViewClasse.getItems().setAll(classeList);
    }

    @FXML
    void openEditClasseFn(ActionEvent event) {
        Classe selectedClasse = tbViewClasse.getSelectionModel().getSelectedItem();

        if (selectedClasse == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez choisir une classe!");
            alert.setResizable(false);
            alert.show();
        } else {
            openEditClasseWindow(selectedClasse);
        }
    }

    private void openEditClasseWindow(Classe classe) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditClasse.fxml"));
        Parent editClasseRoot = null;
        EditClasseController editClasseController = null;

        try {
            editClasseRoot = fxmlLoader.load();
            editClasseController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        editClasseController.setClasseToEdit(classe);

        Stage editClasseStage = new Stage();
        editClasseStage.setTitle("Modifier une classe");
        editClasseStage.setScene(new Scene(editClasseRoot));
        editClasseStage.centerOnScreen();
        editClasseStage.initModality(Modality.APPLICATION_MODAL);
        editClasseStage.setResizable(false);
        editClasseStage.show();
    }

    IClasse iClasse = new ClasseImpl();
    @FXML
    void onDelete(ActionEvent event) {
        Classe selectedClasse = tbViewClasse.getSelectionModel().getSelectedItem();
        if (selectedClasse != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation de suppression");
            confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer cette classe ?");
            confirmationAlert.setContentText("La classe sera définitivement supprimée. Voulez-vous continuer ?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean deleteResult = iClasse.delete(selectedClasse.getId());
                if (deleteResult) {
                    // Suppression réussie
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Suppression réussie");
                    alert.showAndWait();
                    tbViewClasse.getItems().remove(selectedClasse);
                } else {
                    // Suppression échouée
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de suppression");
                    alert.setHeaderText("La suppression de la classe a échoué.");
                    alert.setContentText("Veuillez réessayer ultérieurement.");
                    alert.showAndWait();
                }
            }
        } else {
            // Aucune classe sélectionnée
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Aucune classe sélectionnée");
            alert.setContentText("Veuillez sélectionner une classe à supprimer.");
            alert.showAndWait();
        }
    }


    @FXML
    void initialize(){
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        LibelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        List<Classe> classeList = classeImpl.list();
        tbViewClasse.getItems().setAll(classeList);

    }
}
