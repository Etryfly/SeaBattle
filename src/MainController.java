import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.text.ParseException;

public class MainController {
    boolean turn;

    Connection connection = new Connection();
    Game game = new Game();
    Button[][] userButtons = new Button[10][10];
    Button[][] enemyButtons = new Button[10][10];

    @FXML
    public MenuItem hostMenu;
    @FXML
    public MenuItem connectMenu;

    @FXML
    public GridPane userGrid;
    @FXML
    public GridPane enemyGrid;

    @FXML
    public void initialize() {
        initText(userGrid);
        initText(enemyGrid);

    }

    private void drawShips() {
        try {
            for (Ship ship : game.getShips()) {
                for (Coordinate coordinate : ship.getAllCoordinates()) {
                    if (coordinate.getJ() > 9 || coordinate.getI() > 9) {
                        System.out.println(123);
                    }
                    userButtons[coordinate.getI()][coordinate.getJ()].setText(String.valueOf(ship.getHits()));
                    userButtons[coordinate.getI()][coordinate.getJ()].setStyle("-fx-background-color: #008000");
                }
                // break;
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Test Connection");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void attack(Button button) {

        try {
            Coordinate coordinate = (Coordinate) button.getUserData();
            connection.sendCoordinate(coordinate);

            String color = "black";
            Connection.Message message = connection.getMessage();
            switch (message) {
                case HIT:
                    color = "red";
                    break;

                case MISS:
                    color = "blue";
                    break;

                case KILL:
                    color = "red";
                    break;

                case WIN:

                    break;

                case LOSE:

                    break;

            }
            markButton(coordinate, enemyButtons, color);
            showAlert(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enemyAttack() {
        try {
            Connection.Message message = connection.getMessage();
            switch (message) {
                case LOSE -> {
                    break;
                }

                case WIN -> {
                    break;
                }

                case SHOT -> {
                    Coordinate coordinate = connection.getCoordinate();
                    // System.out.println(coordinate);
                    Connection.Message responce = game.getAttack(coordinate);
                    if (responce.equals(Connection.Message.HIT)) {
                        markButton(coordinate, userButtons, "red");
                    }
                    if (responce.equals(Connection.Message.MISS)) {
                        markButton(coordinate, userButtons, "blue");
                    }
                    if (responce.equals(Connection.Message.KILL)) {
                        markButton(coordinate, userButtons, "red");
                    }
                    connection.sendMessage(responce);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }




    public void startGame() {
        disableCreateMenu();
        initGame();
        game.generateRandomShips();
        drawShips();
        disableUserField();
    }

    public void markButton(Coordinate coordinate, Button[][] buttons, String color) {
        buttons[coordinate.getI()][coordinate.getJ()].setStyle("-fx-background-color: " + color);
    }

    @FXML
    public void ConnectOnClick(ActionEvent actionEvent) {
        try {
            connection.createClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
        turn = true;

        startGame();
    }


    @FXML
    public void HostOnClick(ActionEvent actionEvent) {
        startGame();
        enemyGrid.setDisable(true);
        try {
            connection.createServer();

        } catch (Connection.SocketAlreadyCreated socketAlreadyCreated) {
            socketAlreadyCreated.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        turn = false;
        enemyAttack();
        turn = true;
        enemyGrid.setDisable(false);
    }

    private void disableCreateMenu() {
        hostMenu.setDisable(true);
        connectMenu.setDisable(true);
    }

    private void buttonOnClick(Button button) {
        System.out.println(turn);
        if (turn) {
            enemyGrid.setDisable(true);
            turn = false;
            attack(button);
            enemyAttack();
            enemyGrid.setDisable(false);
            turn = true;
        }
    }

    private void disableUserField() {
        for (int i = 0; i < userButtons.length; i++) {
            for (int j = 0; j < userButtons[i].length; j++) {
                userButtons[i][j].setDisable(true);
            }
        }
    }

    private void initGame() {
        initButtons(userGrid, userButtons);
        initButtons(enemyGrid, enemyButtons);

    }

    private void initButtons(GridPane grid, Button[][] buttons) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setMaxHeight(Double.MAX_VALUE);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setUserData(new Coordinate(i,j));
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> buttonOnClick(button));
                grid.add(button, i,j);
                buttons[i][j] = button;
            }
        }
    }

    public void initText(GridPane grid) {
        for (int i = 0; i < 10; i++) {
            Label letter = new Label();
            letter.setText("  " + String.valueOf(Character.valueOf((char) ('A' + i))));
            Label number = new Label();
            number.setText(String.valueOf(i));
            grid.add(letter, i, 10);
            grid.add(number, 10, i);
        }
    }




}
