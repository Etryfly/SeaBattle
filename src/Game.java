import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Ship> ships;

    int maxX = 9;
    int maxY = 9;


    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Game() {
        ships = new ArrayList<>();

    }

    public boolean checkShipInsert(Ship ship) {
        for (Ship s :
                ships) {
            ArrayList<Coordinate> area = s.getArea();
            for (Coordinate shipCoord : ship.getAllCoordinates()) {
                if (shipCoord.getJ() > maxY || shipCoord.getI() > maxX) return false;
                if (area.contains(shipCoord)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void generateRandomShips() {

        Random random = new Random();
        for (int i = 4; i > 0; i--) {
            for (int j = 1; j < 6-i; j++) {


                while (true) {
                    int x = random.nextInt(9) ;
                    int y = random.nextInt(9) ;
                    boolean align = random.nextBoolean();
                    Ship ship = new Ship(i, align, new Coordinate(x, y));
                    if (checkShipInsert(ship)) {
                        ships.add(ship);
                        break;
                    }
                    ship.isHorizontal = !align;
                    if (checkShipInsert(ship)) {
                        ships.add(ship);
                        break;
                    }

                }
            }
        }
    }
}
