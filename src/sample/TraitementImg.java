package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TraitementImg {

    public void decoupe(String path, int nbPart) throws IOException {
        File file = new File(path);
        BufferedImage img = ImageIO.read(file);
        int h = img.getHeight();
        int w = img.getWidth();
        int hPart = (int) (h/Math.sqrt(nbPart));
        int wPart = (int) (w/Math.sqrt(nbPart));
        int num= 0;
        for (int x = 0 ; x< w; x+=wPart){

           for (int y = 0; y< h; y+=hPart){
               num++;
               BufferedImage part = img.getSubimage(x,y,wPart,hPart);
               String sousImg= "C:\\Users\\nathan metzger\\IdeaProjects\\Taquin_Project_RETTER_METGER_WATELET\\src\\sample\\Img_"+num+".jpg";
               File f = new File(sousImg);
               if (f.createNewFile()) {
                   System.out.println("File created: " + f.getName());
               }
               ImageIO.write(part, "JPG", f);
               System.out.println("x: "+x +"  ; y: "+y);
           }
        }

    }
    public static void main (String[] args) throws IOException {
        TraitementImg t= new TraitementImg();
        t.decoupe("C:\\Users\\nathan metzger\\IdeaProjects\\Taquin_Project_RETTER_METGER_WATELET\\src\\sample\\img.jpg", 9);
    }
}
