package sample;

public class Case {
    private int pos;
    private String pathImg;

    /**
     * to string
     * @return
     */
    @Override
    public String toString() {
        return "Case{" +
                "pos=" + pos +
                ", pathImg :"+pathImg+'}';
    }

    /**
     * get path img
     * @return chemin de l'image
     */
    public String getPathImg() {
        return pathImg;
    }


    /**
     * constructeur de la case
     * @param x position final
     */
    public Case(int x) {
        this.pos = x;
        if (pos==0){
            pathImg="File:src/images/img_null_d.jpg";
        }
        else {
            pathImg= "File:src/images/img_"+this.pos+".jpg";
        }

    }

    /**
     * get pos
     * @return position final
     */
    public int getPos() {
        return pos;
    }

}


