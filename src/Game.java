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
        Coordinate last = ship.getLastCoordinate();
        if ( (last.getJ() > maxY ) || (last.getI() > maxX)) return false;
        for (Ship s : ships) {
            ArrayList<Coordinate> area = s.getArea();
            for (Coordinate shipCoord : ship.getAllCoordinates()) {

                if (area.contains(shipCoord)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void generateRandomShips() {

        Random random = new Random();
        //for (int i = 4; i > 0; i--) {
        for (int i = 1; i > 0; i--) {
            for (int j = 1; j < 6-i; j++) {



                while (true) {
                    int x = random.nextInt(10);
                    int y = random.nextInt(10);
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

    private boolean isAllShipsKilled() {
        for (Ship ship : ships) {
            if (ship.isAlive()) return false;
        }
        return true;
    }

    public Connection.Message getAttack(Coordinate coordinate) {
        for (Ship ship : ships) {
            for (Coordinate myShipCoordinate : ship.getAllCoordinates()) {
                if (myShipCoordinate.equals(coordinate)) {
                    ship.hitDecrement();
                    if (!ship.isAlive()) {
                        if (isAllShipsKilled()) return Connection.Message.LOSE;
                        return Connection.Message.KILL;
                    } else {
                        return Connection.Message.HIT;
                    }
                }
            }
        }
        return Connection.Message.MISS;
    }
}
