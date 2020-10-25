package sample;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    HostServices hostServices;
    @FXML
    private void handleOnButtonAction(ActionEvent e){
        System.out.println("button clicked");
    }
    @FXML
    private void handleOnMouseClicked(MouseEvent m){
        if(((Hyperlink)m.getSource()).getId()=="hyperlink"){hostServices.showDocument("https://en.wikipedia.org/wiki/15_puzzle");};
    }

    public void setGetHostController(HostServices hostServices){
        this.hostServices=hostServices;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }
}
