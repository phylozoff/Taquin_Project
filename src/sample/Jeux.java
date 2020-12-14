package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jeux implements Serializable {
    String pathImg;
    ArrayList<Case> grille;
    int NbCase;
    int posVide;
    private static final long serialVersionUID = 342836670337066099L;

    /**
     * Constructeur avec sauvegarde
     * @param pathSave
     */
    public Jeux(String pathSave) {
        load(pathSave);
    }

    /**
     * constructeur avec une image et un nombre de case
     * @param pathImg chemin image
     * @param nbCase  nombre de case total souhaité
     * @throws IOException
     */
    public Jeux(String pathImg, int nbCase) throws IOException {
        this.pathImg = pathImg;

        double sqrt= Math.sqrt(nbCase);
        if ( (sqrt - Math.floor(sqrt)) == 0) {
            int sq= (int)sqrt;
            this.grille = new ArrayList<>();
            NbCase = nbCase;
        }
        else {
            this.grille = new ArrayList<>();
            this.NbCase= 9;
        }
        try {
            TraitementImg.decoupe(this.pathImg, this.NbCase);
        }
        catch (IOException e){
            this.pathImg= "./src/sample/img.jpg";
            TraitementImg.decoupe(this.pathImg, this.NbCase);
        }

        int sq = (int) Math.sqrt(this.NbCase);
        for (int i= 0 ; i<nbCase; i++){
            this.grille.add(new Case(i));

        }
        this.init();

    }

    /**
     * permet de melanger la grille et de definir la position de l'element vide
     */
    private void init(){
        Collections.shuffle(this.grille);
        int i=0;
        for (Case c : this.grille){
            if(c.getPos()==0){
                this.posVide=i;
                break;
            }
            i++;
        }

    }

    /**
     * Permet la generation d'un fichier sauvegarde .taq
     * @param path chemin souhaité de la sauvegarde
     */
    public void save(String path){

        ObjectOutputStream obj = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(path+".taq");
            obj = new ObjectOutputStream(fichier);
            obj.writeObject(this);
            obj.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (obj != null) {
                    obj.flush();
                    obj.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Permet de charger une sauvegarde
     * @param path
     */
    public void load( String path){
        ObjectInputStream obj = null;

        try {
            final FileInputStream fichier = new FileInputStream(path);
            obj = new ObjectInputStream(fichier);
            final Jeux jeux = (Jeux) obj.readObject();
            setPathImg(jeux.getPathImg());
            setGrille(jeux.getGrille());
            setNbCase(jeux.getNbCase());
            setPosVide(jeux.getPosVide());
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (obj != null) {
                    obj.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get chemin de l'image
     * @return string
     */
    public String getPathImg() {
        return pathImg;
    }

    /**
     * get grille
     * @return ArrayList<Case>
     */
    public ArrayList<Case> getGrille() {
        return grille;
    }

    /**
     * get nb case
     * @return int
     */
    public int getNbCase() {
        return NbCase;
    }

    /**
     * get position dans la grille de la case vide
     * @return
     */
    public int getPosVide() {
        return posVide;
    }

    /**
     * setter path img
     * @param pathImg
     */
    private void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    /**
     * setter grille
     * @param grille
     */
    private void setGrille(ArrayList<Case> grille) {
        this.grille = grille;
    }

    /**
     * setter nb case
     * @param nbCase
     */
    private void setNbCase(int nbCase) {
        NbCase = nbCase;
    }

    /**
     * setter pos vide
     * @param posVide
     */
    private void setPosVide(int posVide) {
        this.posVide = posVide;
    }

    /**
     * tostring jeux
     * @return
     */
    public String toString() {
        return "Jeux{" +
                "pathImg='" + pathImg + '\'' +
                ", grille=" + grille +
                ", NbCase=" + NbCase +
                ", posVide=" + posVide +
                '}';
    }

    /**
     * calcul de tout les mouvement possible a une position
     * @param pos int
     * @return ArrayList<Integer> de position de mouvement possible
     */
    public ArrayList<Integer> mouvementPossible(int pos){
        int cote = (int) Math.sqrt(this.NbCase);
        // on verifie si on se trouve sur un cote
        ArrayList<Integer> a = null;
        if ( (pos < cote && pos >= 0)){
            if (pos%cote==0){
                a = new ArrayList<>(Arrays.asList(pos + 1, pos + cote));
            }
            else if (pos%cote ==cote-1){
                a = new ArrayList<>(Arrays.asList(pos - 1, pos + cote));
            }
            else {
                a = new ArrayList<>(Arrays.asList(pos - 1, pos + 1, pos + cote));
            }
        }
        else if (pos < this.NbCase && pos >= this.NbCase-cote){
            if (pos%cote==0){
                a = new ArrayList<>(Arrays.asList(pos + 1, pos - cote));
            }
            else if (pos%cote ==cote-1){
                a = new ArrayList<>(Arrays.asList(pos - 1, pos - cote));
            }
            else {
                a = new ArrayList<>(Arrays.asList(pos - 1, pos + 1, pos - cote)); ///
            }
        }
        else if (pos%cote==0){
            a = new ArrayList<>(Arrays.asList(pos - cote, pos + 1, pos + cote));
        }
        else if (pos%cote ==cote-1){
            a = new ArrayList<>(Arrays.asList(pos - cote, pos - 1, pos + cote));
        }
        else {
            a = new ArrayList<>(Arrays.asList(pos - cote, pos - 1, pos + 1, pos + cote)) ;
        }

        return a;

    }
}
