package application.ndiaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StatistiqueController {

    IEtudiant iEtudiant = new EtudiantImpl();

    @FXML
    private Button ClasseMenu;

    @FXML
    private Button EtudiantMenu;

    @FXML
    private PieChart statistiqueChart;

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
    void initialize(){
        iEtudiant.getNbEtudiantsParClasse().stream().forEach(x->{
            statistiqueChart.getData().add(new Data(x.getLibelle(),x.getValue()));
        });

    }

}
