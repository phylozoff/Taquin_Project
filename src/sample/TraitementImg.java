package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
public class TraitementImg {

    /**
     * Methode permettant le decoupage d'une image, complexité O(n^2) sachant que nous ne depassseront pas 7*7 la complexité totale de ce
     * cette fonction a une complexité de 60 dans le pire des cas
     * @param path chemin de l'image
     * @param nbPart nombre de case voulu
     * @throws IOException
     */

    public static void decoupe(String path, int nbPart) throws IOException {
        File file = new File(path);
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        BufferedImage img = ImageIO.read(file);
        int h = img.getHeight();
        int w = img.getWidth();
        int RnbPart= (int) Math.sqrt(nbPart);
        int hPart = h/RnbPart;
        int wPart = w/RnbPart;
        int num= 0;
        h = hPart*RnbPart;
        w = wPart*RnbPart;
        for (int x = 0 ; x< w; x+=wPart){

           for (int y = 0; y< h; y+=hPart){
               System.out.println("x: "+x +"  ; y: "+y +" ; h :" +h +" ; w :" +w);
               num++;
               BufferedImage part = img.getSubimage(x,y,wPart,hPart);
               String sousImg= "src/images/img_"+num+".jpg";
               File f = new File(sousImg);

               if (f.createNewFile()) {
                   System.out.println("File created: " + f.getName());
               }
               ImageIO.write(part, "JPG", f);
               //System.out.println("x: "+x +"  ; y: "+y);
           }
        }

    }
}
