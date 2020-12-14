package sample;

import java.io.IOException;
import java.util.*;

import static java.lang.Character.toUpperCase;

public class JeuxConsole extends Jeux{

    private int nbshots;

    public int getNbshots() {
        return nbshots;
    }

    public JeuxConsole(String pathSave) {
        super(pathSave);
        nbshots = 0;
    }

    public JeuxConsole(String pathImg, int nbCase) throws IOException {
        super(pathImg, nbCase);
        nbshots = 0;
    }

    public char verifIn(char c){
        c= toUpperCase(c);
        return (c == 'Z' || c== 'Q' || c=='S' || c=='D')? c: null;
    }
    public int[] moveIa(int i){
        if(i!=-1){
            System.out.println(posVide);
            System.out.println(i);
            Collections.swap(this.grille, i, posVide);
            int[] res = {i, posVide};
            return res;
        }
        return null;
    }

    public int[] move(char d){
        System.out.println(posVide);
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

    private int[] pos2D(int i){
        return new int[]{(int) i % (int) Math.sqrt(this.NbCase),(int) i / (int) Math.sqrt(this.NbCase)};
    }
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
                if (minDist>distanceMan && distanceMan!=0){
                    minPos=i;
                    minDist=distanceMan;
                }
                //disman.put(i, distanceMan);
            }
            return minPos;
        }
        return -1;
    }
    public boolean estResolue(ArrayList<Case> g){
        for (int i=0; i<this.NbCase; i++){
            if(g.get(i).getPos()!=i){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        JeuxConsole j = new JeuxConsole("src/sample/img.jpg", 16);
        j.resolution();

    }



}
