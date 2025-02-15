import java.util.ArrayList;

public class Ship {
    private Coordinate coordinate;
    private int size;
    private int hits;
    public boolean isHorizontal;




    public int getSize() {
        return size;
    }

    public Ship(int size, boolean isHorizontal, Coordinate coord) {
        coordinate = coord;
        this.size = size;
        hits = size;
        this.isHorizontal = isHorizontal;
    }

    public void hitDecrement() {
        hits--;
    }

    public boolean isAlive() {
        System.out.println(hits);
        return hits != 0;
    }

    public Coordinate getLastCoordinate() {
        Coordinate last;
        if(isHorizontal) last = new Coordinate(coordinate.getI() + size, coordinate.getJ());
        else last = new Coordinate(coordinate.getI() , coordinate.getJ() + size);
        return last;
    }

    public ArrayList<Coordinate> getAllCoordinates() {
        Coordinate last = getLastCoordinate();
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
        if(isHorizontal) last = new Coordinate(coordinate.getI() + size, coordinate.getJ());
        else last = new Coordinate(coordinate.getI() , coordinate.getJ() + size);
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
