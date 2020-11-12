package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class Controller{

    @FXML
    private Label label, nbshots;
    private ToggleButton play;

   public void sayHelp(ActionEvent actionEvent) {
        Stage popUpStage = new Stage();
        Parent root = null;
        popUpStage.setTitle("Help");
        try {
            root = FXMLLoader.load(getClass().getResource("popUp1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text s = new Text("Cliquez sur IA pour vous aider à résoudre le jeu");
        Scene sc = new Scene(root);
        TextFlow txt = (TextFlow) sc.lookup("#instructions");
        txt.getChildren().add(s);
        popUpStage.setScene(sc);
        popUpStage.initModality(Modality.APPLICATION_MODAL);    // popup
        popUpStage.showAndWait();
    }

    public void hyperLink(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("What's Taquin ?");
        WebView browser = new WebView();
        browser.getEngine().load("https://en.wikipedia.org/wiki/15_puzzle");
        VBox vBox = new VBox(browser);
        Scene scene = new Scene(vBox, 960, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void playTaquin(ActionEvent actionEvent) {
        /*if(play.isSelected()){

        }else{

        }*/


    }

    @FXML
    public void up(ActionEvent actionEvent) {

    }

    public void left(ActionEvent actionEvent) {
    }

    public void right(ActionEvent actionEvent) {
    }

    public void down(ActionEvent actionEvent) {

    }

    public void save(ActionEvent actionEvent) {

    }
}
