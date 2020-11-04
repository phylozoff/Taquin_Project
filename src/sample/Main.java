package sample;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Taquin");
        Text n = new Text("0");
        Text p = new Text("ValouDu57");
        Text pseudo = new Text("User : ");
        Scene scene = new Scene(root, 600, 400);
        TextFlow txt3 = (TextFlow) scene.lookup("#pseudo");
        txt3.getChildren().addAll(pseudo,p);

        Label timeLabel = (Label)scene.lookup("#label");
        timeLabel.setText("0");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
