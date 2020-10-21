package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Taquin");
        Text s = new Text("shots");
        Text n = new Text("0");
        Text c = new Text("chronomètre");
        Text p = new Text("ValouDu57");
        Scene scene = new Scene(root, 600, 400);
        TextFlow txt = (TextFlow) scene.lookup("#textshot");
        txt.getChildren().add(s);
        TextFlow txt1 = (TextFlow) scene.lookup("#shots");
        txt1.getChildren().add(n);
        TextFlow txt2 = (TextFlow) scene.lookup("#chrono");
        txt2.getChildren().add(c);
        TextFlow txt3 = (TextFlow) scene.lookup("#pseudo");
        txt3.getChildren().add(p);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
