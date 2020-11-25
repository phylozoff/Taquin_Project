package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
import java.util.Timer;
import java.util.TimerTask;

public class Controller{

    private Timer timer = new Timer();
    private Task timerTask;
    private javax.swing.Timer blinkTask;
    private boolean debut=true;
    @FXML
    private Label label, nbshots;
    @FXML
    private ToggleButton play;
    @FXML
    private GridPane grille;
    @FXML
    private TextField length;
    @FXML
    private Pane pane;

   public void sayHelp(ActionEvent actionEvent) {
        Stage popUpStage = new Stage();
        Parent root = null;
        popUpStage.setTitle("Help");
        try {
            root = FXMLLoader.load(getClass().getResource("IA.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text s = new Text("Cliquez sur [IA] pour vous aider à résoudre le jeu.");
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
    public void playTaquin(ActionEvent actionEvent) throws InterruptedException {
        if(debut){
            play.setText("Stop");
            start();
            debut=false;
        }else {
            if (play.isSelected()) {
                play.setText("Stop");
                resume();
            } else {
                play.setText("Play");
                pause();
            }
        }

    }

    @FXML
    public void up(ActionEvent actionEvent) {
        if(play.isSelected()) {
            int[] tabPos = Main.getJ().move('Z');
            nbshots.setText(String.valueOf(Main.getJ().getNbshots()));
            String s = Main.getJ().getGrille().get(tabPos[0]).getPathImg();
            ImageView iv = new ImageView(new Image(s));
            grille.setConstraints(iv, 0, 0);
            nbshots.setText(""+Main.getJ().getNbshots());
        }
    }

    public void left(ActionEvent actionEvent) {
        if(play.isSelected()){
            int[] tabPos = Main.getJ().move('Q');
            nbshots.setText(String.valueOf(Main.getJ().getNbshots()));
            String s = Main.getJ().getGrille().get(tabPos[0]).getPathImg();
            ImageView iv = new ImageView(new Image(s));
            grille.setConstraints(iv, 0, 0);
            nbshots.setText(""+Main.getJ().getNbshots());
        }
    }

    public void right(ActionEvent actionEvent) {
        if(play.isSelected()) {
            int[] tabPos = Main.getJ().move('D');
            nbshots.setText(String.valueOf(Main.getJ().getNbshots()));
            String s = Main.getJ().getGrille().get(tabPos[0]).getPathImg();
            ImageView iv = new ImageView(new Image(s));
            grille.setConstraints(iv, 0, 0);
            nbshots.setText(""+Main.getJ().getNbshots());
        }
    }

    public void down(ActionEvent actionEvent) {
       if(play.isSelected()) {
           int[] tabPos = Main.getJ().move('S');
           nbshots.setText(String.valueOf(Main.getJ().getNbshots()));
           String s = Main.getJ().getGrille().get(tabPos[0]).getPathImg();
           ImageView iv = new ImageView(new Image(s));
           grille.setConstraints(iv, 0, 0);
           nbshots.setText(""+Main.getJ().getNbshots());
       }
    }

    public void save(ActionEvent actionEvent) {
        stop();
        Main.getJ().save("Saved");
    }


    public void nouvelleTaille(ActionEvent actionEvent) {
        Stage popUpStage = new Stage();
        Parent root = null;
        popUpStage.setTitle("Taille");
        try {
            root = FXMLLoader.load(getClass().getResource("taille.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text s = new Text("Veuillez entrer la taille de la nouvelle grille :");
        Scene sc = new Scene(root);
        TextFlow txt = (TextFlow) sc.lookup("#phrase");
        txt.getChildren().add(s);
        popUpStage.setScene(sc);
        popUpStage.initModality(Modality.APPLICATION_MODAL);    // popup
        popUpStage.showAndWait();
    }

    @FXML
    public void generateGrid(ActionEvent actionEvent) throws IOException {
        String t = length.getText();
        if (t != null && !t.isEmpty()) {
            int length = Integer.parseInt(t);
            if(Math.sqrt(length)==(int)Math.sqrt(length) && length>=9) {
                JeuxConsole j2 = new JeuxConsole("src/sample/img.jpg", length);
                Main.setJ(j2);
                int taille = (int) Math.sqrt(j2.getNbCase());
                int nombre = 0;
                String s = null;
                ImageView iv = null;
                for (int i = 0; i < taille; i++) {
                    for (int k = 0; k < taille; k++) {
                        s = j2.getGrille().get(nombre).getPathImg();
                        nombre++;
                        if (s != null) {
                            iv = new ImageView(new Image(s));
                            GridPane.setConstraints(iv, i, k);
                            grille.getChildren().add(iv);
                        }
                    }
                }
            }
        }
    }


    @FXML
    public void negative(ActionEvent actionEvent) {
        pane.setBlendMode(BlendMode.DIFFERENCE);
    }

    public void applyTheme(ActionEvent actionEvent) {

    }


    private class Task extends TimerTask {

        final long start;
        private boolean running;
        public Task() {
            this(System.currentTimeMillis());
        }
        public Task(Task task) {
            this(task.start);
        }
        public Task(long start) {
            this.start=start;
        }

        @Override
        public boolean cancel() {
            final boolean cancel = super.cancel();
            running=false;
            return cancel;
        }

        public boolean isRunning() {
            return running;
        }

        @Override
        public void run() {
            running=true;
            final long time = System.currentTimeMillis() - start;
            final int seconds = (int) (time / 1000);
            final String message = String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
            Platform.runLater(new Runnable() {
                public void run() {
                    label.setText(message);
                }
            });
        }

    };



    private void start() {
        blinkStop();
        timerTask = new Task();
        timer.schedule(timerTask, 1000, 1000);
    }

    private void pause() {
        timerTask.cancel();
        blinkStart();
    }

    private void resume() {
        blinkStop();
        timerTask=new Task(timerTask);
        timer.schedule(timerTask, 1000, 1000);
    }

    private void stop() {
        timerTask.cancel();
        blinkStop();
        timer.purge();
    }

    private void blinkStart() {
        if ( blinkTask!=null ) {
            blinkTask.stop();
        }
        blinkTask = new javax.swing.Timer(1000, e-> label.setVisible(!label.isVisible()));
        blinkTask.start();
    }

    private void blinkStop() {
        if ( blinkTask!=null ) {
            blinkTask.stop();
            blinkTask=null;
            label.setVisible(true);
        }
    }

}
