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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ClasseController {

    @FXML
    private Button AjouterBtn;

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

    }

    private ClasseImpl classeImpl = new ClasseImpl();
    @FXML
    void refreshTable(ActionEvent event) {
        // Rafraîchir la liste des classes après l'ajout d'un nouvel étudiant
        List<Classe> classeList = classeImpl.list();
        tbViewClasse.getItems().setAll(classeList);
    }


    @FXML
    void initialize(){
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        LibelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        List<Classe> classeList = classeImpl.list();
        tbViewClasse.getItems().setAll(classeList);

    }
}
