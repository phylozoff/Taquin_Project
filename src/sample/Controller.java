package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;
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
    private Pane pane;
    @FXML
    private RadioButton neuf, seize, vingtcinq, trentesix;

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
    public void bouger(int[] tab){
        int[] tabPos = tab;
        String s = Main.getJ().getGrille().get(tabPos[0]).getPathImg();
        ImageView iv =null;
        if(s!=null){iv= new ImageView(new Image(s));}
        int length = grille.getColumnCount();
        int x =0, y =0;
        for(int i=2; i<length;i++){
            if((tabPos[0]+1)/length <1){
                x=tabPos[0];
                break;
            }else if((tabPos[0]+1)/length <i){
                x=tabPos[0]+1-length*(i-1);
                y=i-1;
                break;
            }
        }
        System.out.println(x+"/"+y);
        grille.setConstraints(iv, y, x);
        nbshots.setText(""+Main.getJ().getNbshots());
    }

    @FXML
    public void up(ActionEvent actionEvent) {
        if(play.isSelected()) {
            int[] tabPos = Main.getJ().move('Z');
            bouger(tabPos);
        }
    }

    public void left(ActionEvent actionEvent) {
        if(play.isSelected()){
            int[] tabPos = Main.getJ().move('Q');
            bouger(tabPos);
        }
    }

    public void right(ActionEvent actionEvent) {
        if(play.isSelected()) {
            int[] tabPos = Main.getJ().move('D');
            bouger(tabPos);
        }
    }

    public void down(ActionEvent actionEvent) {
       if(play.isSelected()) {
           int[] tabPos = Main.getJ().move('S');
           bouger(tabPos);
       }
    }

    public void save(ActionEvent actionEvent) {
        stop();
        Main.getJ().save("Saved");
    }

    @FXML
    public void generateGrid(ActionEvent actionEvent) throws IOException {
       grille.getChildren().clear();
       JeuxConsole j2 = null;
       if(neuf.isSelected()) {
           j2 = new JeuxConsole("src/sample/img.jpg", 9);
           Main.setJ(j2);
           if(grille.getColumnCount()==6){
               grille.getColumnConstraints().remove(5);
               grille.getRowConstraints().remove(5);
           }
           if(grille.getColumnCount()>=5){
               grille.getColumnConstraints().remove(4);
               grille.getRowConstraints().remove(4);
           }
           if(grille.getColumnCount()>=4){
               grille.getColumnConstraints().remove(3);
               grille.getRowConstraints().remove(3);
           }
       }
       if(seize.isSelected()){
           j2 = new JeuxConsole("src/sample/img.jpg", 16);
           Main.setJ(j2);
           if(grille.getColumnCount()==6){
               grille.getColumnConstraints().remove(5);
               grille.getRowConstraints().remove(5);
           }
           if(grille.getColumnCount()>=5){
               grille.getColumnConstraints().remove(4);
               grille.getRowConstraints().remove(4);
           }
           if(grille.getColumnCount()==3){
               grille.getColumnConstraints().add(new ColumnConstraints());
               grille.getRowConstraints().add(new RowConstraints());
           }
       }
        if(vingtcinq.isSelected()){
            j2 = new JeuxConsole("src/sample/img.jpg", 25);
            Main.setJ(j2);
            if(grille.getColumnCount()==6){
                grille.getColumnConstraints().remove(5);
                grille.getRowConstraints().remove(5);
            }
            if(grille.getColumnCount()<5){
                grille.getColumnConstraints().add(new ColumnConstraints());
                grille.getRowConstraints().add(new RowConstraints());
            }
            if(grille.getColumnCount()<4){
                grille.getColumnConstraints().add(new ColumnConstraints());
                grille.getRowConstraints().add(new RowConstraints());
            }
        }
        if(trentesix.isSelected()){
            j2 = new JeuxConsole("src/sample/img.jpg", 36);
            Main.setJ(j2);
            if(grille.getColumnCount()<6){
                grille.getColumnConstraints().add(new ColumnConstraints());
                grille.getRowConstraints().add(new RowConstraints());
            }
            if(grille.getColumnCount()<5){
                grille.getColumnConstraints().add(new ColumnConstraints());
                grille.getRowConstraints().add(new RowConstraints());
            }
            if(grille.getColumnCount()<4){
                grille.getColumnConstraints().add(new ColumnConstraints());
                grille.getRowConstraints().add(new RowConstraints());
            }
        }
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


    @FXML
    public void negative(ActionEvent actionEvent) {
        pane.setBlendMode(BlendMode.DIFFERENCE);
    }

    public void pink(ActionEvent actionEvent) {
        pane.setBlendMode(BlendMode.GREEN);
    }

    public void bauhaus(ActionEvent actionEvent) {
        pane.setBlendMode(BlendMode.SRC_OVER);
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
