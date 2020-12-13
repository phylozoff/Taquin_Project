package sample;

import java.io.IOException;
import java.util.Collections;

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



}
