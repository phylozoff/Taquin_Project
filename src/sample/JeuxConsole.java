package sample;

import java.io.IOException;
import java.util.*;

import static java.lang.Character.toUpperCase;

public class JeuxConsole extends Jeux{

    private int nbshots;

    /**
     * methode get
     * @return nombre de coups
     */
    public int getNbshots() {
        return nbshots;
    }

    /**
     * constructeur avec sauvegarde
     * @param pathSave
     */
    public JeuxConsole(String pathSave) {
        super(pathSave);
        nbshots = 0;
    }

    /**
     * constructeur avec une image et un nombre de case
     * @param pathImg chemin image
     * @param nbCase  nombre de case total souhaité
     * @throws IOException
     */
    public JeuxConsole(String pathImg, int nbCase) throws IOException {
        super(pathImg, nbCase);
        nbshots = 0;
    }

    /**
     * Verification du charactere en paramettre et mise en majuscule
     * @param c
     * @return c majuscule
     */
    public char verifIn(char c){
        c= toUpperCase(c);
        return (c == 'Z' || c== 'Q' || c=='S' || c=='D')? c: null;
    }

    /**
     * Mouvement en fonction d'un entier
     * @param i entier
     * @return tableau avec la position des deux case ayant été deplacé
     */
    public int[] moveIa(int i){
        if(i!=-1){
            Collections.swap(this.grille, i, posVide);
            int[] res = {i, posVide};
            this.posVide=i;
            return res;
        }
        return null;
    }

    /**
     * Mouvement en fonction d'un charactere (q,s,z,d)
     * @param d char
     * @return tableau avec la position des deux case ayant été inversé
     */
    public int[] move(char d){
        int posD=-1;
        int cote= (int) Math.sqrt(this.NbCase);
        switch (d){
            case ('Z'):
                posD= this.posVide-cote;
                break;

            case ('Q'):
                posD= this.posVide-1;
                break;

            case ('S'):
                posD=this.posVide+cote;
                break;

            case('D'):
                posD=this.posVide+1;
                break;
        }
        //inverser les deux position
        if (mouvementPossible(this.posVide).contains(posD)){

            Collections.swap(this.grille, posD, posVide);
            int[] res = {posD, posVide};
            this.posVide=posD;
            nbshots++;
            return res;
        }

        return null;
    }

    /**
     * Permet de convetir une postion en 1D (position dans grille) en un couple x,y de position ( necessaire calcule des distances de manhattan
     * @param i postion à convertir
     * @return x,y
     */
    private int[] pos2D(int i){
        return new int[]{(int) i % (int) Math.sqrt(this.NbCase),(int) i / (int) Math.sqrt(this.NbCase)};
    }

    /**
     * Permets de derterminer quel mouvement est le plus otpimal parmis ceux possibles
     * minimum des distances manhattan de tout les mouvements possibles
     * @return position du mouvement optimal entier
     * @throws InterruptedException
     */
    public int resolution() throws InterruptedException {
        //Map<Integer, Integer> disman= new TreeMap<Integer, Integer>();
        int n = this.posVide;

        if (!estResolue(this.grille)){

            ArrayList<Integer> c=mouvementPossible(n);
            int minPos = 1000000;
            int minDist = 10000000;
            for (Integer i : c){
                int posFi=this.grille.get(i).getPos();
                int distanceMan= Math.abs(pos2D(i)[0]-pos2D(posFi)[0])+Math.abs(pos2D(i)[1]-pos2D(posFi)[1]);
                if (minDist>distanceMan && distanceMan!=1){
                    minPos=i;
                    minDist=distanceMan;
                }
                //disman.put(i, distanceMan);
            }
            return minPos;
        }
        return -1;
    }

    /**
     * determine si le taquin est resolue
     * @param g grille de case
     * @return boolean
     */
    public boolean estResolue(ArrayList<Case> g){
        for (int i=0; i<this.NbCase; i++){
            if(g.get(i).getPos()!=i){
                return false;
            }
        }
        return true;
    }




}
