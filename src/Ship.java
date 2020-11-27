public class Ship {
    private int i,j;
    private int hits;
    public boolean isHorizontal;


    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getHits() {
        return hits;
    }

    public Ship(int i, int j, int hits, boolean isHorizontal) {
        this.i = i;
        this.j = j;
        this.hits = hits;
        this.isHorizontal = isHorizontal;
    }

    public void hitDecrement() {
        hits--;
    }

    public boolean isAlive() {
        return hits != 0;
    }
}
