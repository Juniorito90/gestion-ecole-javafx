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
import java.util.stream.Collectors;

public class EtudiantController {

    @FXML
    private Button AjouterBtn;

    @FXML
    private Button modifierBtn;

    @FXML
    private Button supprimerBtn;

    @FXML
    private Button ClasseMenu;

    @FXML
    private TableColumn<Classe, Integer> IDCol;

    @FXML
    private TableColumn<Classe, String> NomCol;

    @FXML
    private TableColumn<Classe, String> PrenomCol;

    @FXML
    private TableColumn<Classe, String> AdresseCol;

    @FXML
    private TableColumn<Classe, Integer> ClasseCol;

    @FXML
    private Button StatistiqueMenu;

    @FXML
    private Button extraireBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    private TextField EtudiantInput;

    @FXML
    private TableView<Etudiant> tbViewEtudiant;

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
        Stage primaryStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Extraction.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Extraction");
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

    private EtudiantImpl etudiantImpl = new EtudiantImpl();
    @FXML
    void refreshTable(ActionEvent event) {
        // Rafraîchir la liste des classes après l'ajout d'un nouvel étudiant
        List<Etudiant> etudiantList = etudiantImpl.list();
        tbViewEtudiant.getItems().setAll(etudiantList);
    }

    @FXML
    void onSearch(ActionEvent event) {
        Alert alert;
        if(EtudiantInput.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Veuillez saisir un Etudiant!");
            alert.setResizable(false);
            alert.show();
        }else{
            List<Etudiant> etudiantList = etudiantImpl.list();
            List<Etudiant> tempE = etudiantList.stream().filter(x->x.getNom().startsWith(EtudiantInput.getText())
                    || x.getPrenom().startsWith(EtudiantInput.getText())
                    || x.getAdresse().startsWith(EtudiantInput.getText()))
                    .collect(Collectors.toList());

            tbViewEtudiant.getItems().clear();
            tbViewEtudiant.getItems().addAll(tempE);
        }
    }

    @FXML
    void openEditEtudiantFn(ActionEvent event) {
        Etudiant selectedEtudiant = tbViewEtudiant.getSelectionModel().getSelectedItem();

        if (selectedEtudiant == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez choisir un etudiant!");
            alert.setResizable(false);
            alert.show();
        } else {
            openEditEtudiantWindow(selectedEtudiant);
        }
    }

    private void openEditEtudiantWindow(Etudiant etudiant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditEtudiant.fxml"));
            Parent root = loader.load();

            EditEtudiantController editController = loader.getController();
            editController.setEtudiantToEdit(etudiant);

            Stage stage = new Stage();
            stage.setTitle("Modifier un étudiant");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onDelete(ActionEvent event) {
        Etudiant selectedEtudiant = tbViewEtudiant.getSelectionModel().getSelectedItem();

        if (selectedEtudiant == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Veuillez choisir un étudiant à supprimer!");
            alert.setResizable(false);
            alert.show();
        } else {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setHeaderText("Confirmer la suppression");
            confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer l'étudiant sélectionné?");
            confirmationAlert.setResizable(false);

            // Personnaliser les boutons de la boîte de dialogue de confirmation
            ButtonType confirmButton = new ButtonType("Confirmer", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);

            // Afficher la boîte de dialogue de confirmation et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == confirmButton) {
                // L'utilisateur a confirmé la suppression, supprimer l'étudiant de la base de données
                boolean rs = etudiantImpl.delete(selectedEtudiant.getId());
                if (rs) {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setHeaderText("Suppression réussie");
                    successAlert.setResizable(false);
                    successAlert.show();

                    // Rafraîchir la liste des étudiants après la suppression
                    List<Etudiant> etudiantList = etudiantImpl.list();
                    tbViewEtudiant.getItems().setAll(etudiantList);
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Erreur lors de la suppression");
                    errorAlert.setResizable(false);
                    errorAlert.show();
                }
            }
        }
    }


    @FXML
    void initialize(){
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        AdresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ClasseCol.setCellValueFactory(new PropertyValueFactory<>("idClasse"));

        List<Etudiant> etudiantList = etudiantImpl.list();
        tbViewEtudiant.getItems().setAll(etudiantList);
    }

}
