package sample;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Character.toUpperCase;
import static java.util.Objects.isNull;

public class JeuxConsole extends Jeux implements Runnable{
    public JeuxConsole(String pathSave) {
        super(pathSave);
    }

    public JeuxConsole(String pathImg, int nbCase) throws IOException {
        super(pathImg, nbCase);
    }

    public char verifIn(char c){
        c= toUpperCase(c);
        return (c == 'Z' || c== 'Q' || c=='S' || c=='D')? c: null;
    }
    public void move(char d){
        int posD=-1;
        int cote= (int) Math.sqrt(this.NbCase);
        switch (d){
            case ('Z'):
                posD= this.posVide-cote;
                break;

            case ('Q'):
                posD= this.posVide--;
                break;

            case ('S'):
                posD=this.posVide+cote;
                break;

            case('D'):
                posD=this.posVide++;
                break;
        }
        //inverser les deux possition
        if (mouvementPossible(this.posVide).contains(posD)){
            Collections.swap(this.grille, posD, posVide);
            this.posVide=posD;
        }

    }
    @Override
    public void run() {
        //boolean affiche=true;
        Scanner sc;
        while (true){
            System.out.println(this.grille.size());

            for (int i=0; i<4; i++){
                String ligne="| ";
                for (int k=0; k<4; k++){
                    //(this.grille.size()== (k+i*4)? this.gr)
                    ligne += this.grille.get(k+i*4);
                    //System.out.println(k+i*4);
                    ligne += " | ";
                    //System.out.println(ligne);
                    //ligne="| ";
                }
                System.out.println(ligne);

            }
            sc= new Scanner(System.in);
            char c = sc.next().charAt(0);
            if (isNull(verifIn(c))) {
                System.out.println("erreur");
            } else {
                move(c);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        JeuxConsole j= new JeuxConsole("src/sample/img.jpg", 16);

        j.run();
    }


}
