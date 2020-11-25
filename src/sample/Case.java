package sample;

public class Case {
    private int pos;
    private int cost;
    private int finalCost;
    private String pathImg;

    public Case(int x, String img) {
        this.pos = x;
        this.pathImg=img;

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


