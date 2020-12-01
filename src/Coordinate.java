import java.text.ParseException;
import java.util.Objects;

public class Coordinate {
    private int i,j;

    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public Coordinate(String str) throws ParseException {
        String[] arr = str.split(" ");
        if (arr.length != 2) throw new ParseException(str, 0);
        try {
            i = Integer.parseInt(arr[0]);
            j = Integer.parseInt(arr[1]);

        } catch (NumberFormatException e) {
            throw new ParseException(str, 0);
        }
    }

    public int getI() {
        return i;
    }


    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return i == that.i &&
                j == that.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" ");
        sb.append(j);
        return sb.toString();
    }
}
