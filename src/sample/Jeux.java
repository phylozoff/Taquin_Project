package sample;

import java.io.IOError;
import java.io.IOException;

public class Jeux {
    String pathImg;
    int[][] grille;
    int NbCase;

    public Jeux(String pathImg, int nbCase) throws IOException {
        this.pathImg = pathImg;

        double sqrt= Math.sqrt(nbCase);
        if ( (sqrt - Math.floor(sqrt)) == 0) {
            int sq= (int)sqrt;
            this.grille = new int[sq][sq];
            NbCase = nbCase;
        }
        else {
            this.grille = new int[3][3];
            this.NbCase= 9;
        }
        try {
            TraitementImg.decoupe(this.pathImg, this.NbCase);
        }
        catch (IOException e){
            this.pathImg= "./src/sample/img.jpg";
            TraitementImg.decoupe(this.pathImg, this.NbCase);
        }
        int nb= 1;
        int sq = (int) Math.sqrt(this.NbCase);
        for (int i= 0 ; i<sq; i++){
            for (int j=0 ; j<sq; j++){
                this.grille[i][j]=nb;
                nb++;
            }
        }

    }

    public void melanger(){

    }


}
