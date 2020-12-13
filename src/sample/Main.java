package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {

    public static void setJ(JeuxConsole j) {
        Main.j = j;
    }

    private static JeuxConsole j;

    public static JeuxConsole getJ() {
        return j;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Taquin");
        Scene scene = new Scene(root, 600, 400);

        Label timeLabel = (Label)scene.lookup("#label");
        timeLabel.setText("0:00:00");

        j = new JeuxConsole("src/sample/img.jpg", 16);
        GridPane grid = (GridPane) scene.lookup("#grille");
        int taille = (int) Math.sqrt(j.getNbCase());
        int nombre = 0;
        String s = null;
        ImageView iv = null;
        for(int i=0; i<taille;i++) {
            for(int k=0; k<taille;k++) {
                s = j.getGrille().get(nombre).getPathImg();
                nombre++;
                if(s!=null) {
                    iv = new ImageView(new Image(s));
                    GridPane.setConstraints(iv, i, k);
                    grid.getChildren().add(iv);
                }
            }
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
