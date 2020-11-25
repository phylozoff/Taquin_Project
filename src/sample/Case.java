package sample;

public class Case {
    private int pos;
    private int cost;
    private int finalCost;
    private String pathImg;

    public Case(int x) {
        this.pos = x;
        if (pos==0){
            pathImg=null;
        }
        else {
            pathImg= "src/images/img_"+this.pos+".jpg";
        }

    }

    public int getPos() {
        return pos;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFinalCost(int finalCost) {
        this.finalCost = finalCost;
    }

    public int getCost() {
        return cost;
    }

    public int getFinalCost() {
        return finalCost;
    }
}


