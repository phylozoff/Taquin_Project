package sample;

import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class Controller{


   public void sayHelp(ActionEvent actionEvent) {
        Stage popUpStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("popUp1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        popUpStage.setScene(new Scene(root));
        popUpStage.initModality(Modality.APPLICATION_MODAL);    // popup
        popUpStage.showAndWait();
    }

}
