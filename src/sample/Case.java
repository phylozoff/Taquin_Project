package sample;

public class Case {
    private int pos;
    private int cost;
    private int finalCost;

    public Case(int x) {
        this.pos = x;
    }

    public int getX() {
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


