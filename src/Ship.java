import java.util.ArrayList;

public class Ship {
    private Coordinate coordinate;
    private int hits;
    public boolean isHorizontal;




    public int getHits() {
        return hits;
    }

    public Ship(int hits, boolean isHorizontal, Coordinate coord) {
        coordinate = coord;
        this.hits = hits;
        this.isHorizontal = isHorizontal;
    }

    public void hitDecrement() {
        hits--;
    }

    public boolean isAlive() {
        return hits != 0;
    }

    public ArrayList<Coordinate> getAllCoordinates() {
        Coordinate last;
        if(isHorizontal) last = new Coordinate(coordinate.getI() + hits, coordinate.getJ());
        else last = new Coordinate(coordinate.getI() , coordinate.getJ() + hits);
        int I = coordinate.getI();
        int J = coordinate.getJ();
        int maxI = last.getI();
        int maxJ = last.getJ();
        if (isHorizontal) {
            maxJ++;
        } else {
            maxI++;
        }

        ArrayList<Coordinate> result = new ArrayList<>();
        for (int i = I; i < maxI; i++) {
            for (int j = J; j < maxJ; j++) {
                result.add(new Coordinate(i,j));
            }
        }

        return result;
    }

    public ArrayList<Coordinate> getArea() {
        Coordinate last;
        if(isHorizontal) last = new Coordinate(coordinate.getI() + hits, coordinate.getJ());
        else last = new Coordinate(coordinate.getI() , coordinate.getJ() + hits);
        int I = coordinate.getI() - 1;
        I = (I < 0) ? 0 : I;
        int J = coordinate.getJ() - 1;
        J = (J < 0) ? 0 : J;
        int maxI = last.getI() + 1;
        int maxJ = last.getJ() + 1;
        if (isHorizontal) {
            maxJ++;
        } else {
            maxI++;
        }
        maxI = (maxI > 10) ? 10 : maxI;
        maxJ = (maxJ > 10) ? 10 : maxJ;

        ArrayList<Coordinate> result = new ArrayList<>();
        for (int i = I; i < maxI; i++) {
            for (int j = J; j < maxJ; j++) {
                result.add(new Coordinate(i,j));
            }
        }

        return result;

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
