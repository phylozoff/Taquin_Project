package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
public class TraitementImg {

    public void decoupe(String path, int nbPart) throws IOException {
        File file = new File(path);
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        BufferedImage img = ImageIO.read(file);
        int h = img.getHeight();
        int w = img.getWidth();
        int hPart = (int) (h/Math.sqrt(nbPart));
        int wPart = (int) (w/Math.sqrt(nbPart));
        int num= 0;
        h = (int) (hPart*Math.sqrt(nbPart));
        w = (int) (wPart*Math.sqrt(nbPart));
        for (int x = 0 ; x< w; x+=wPart){

           for (int y = 0; y< h; y+=hPart){
               System.out.println("x: "+x +"  ; y: "+y +" ; h :" +h +" ; w :" +w);
               num++;
               BufferedImage part = img.getSubimage(x,y,wPart,hPart);
               String sousImg= "Img_"+num+".jpg";
               File f = new File(sousImg);
               if (f.createNewFile()) {
                   System.out.println("File created: " + f.getName());
               }
               ImageIO.write(part, "JPG", f);
               //System.out.println("x: "+x +"  ; y: "+y);
           }
        }

    }
    public static void main (String[] args) throws IOException {
        TraitementImg t= new TraitementImg();
        t.decoupe("./src/sample/img.jpg", 9);
    }
}
