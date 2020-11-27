import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainController {


    Connection connection = new Connection();
    Game game = new Game();

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

    @FXML
    public void HostOnClick(ActionEvent actionEvent) {
        try {
            connection.createServer();
        } catch (Connection.SocketAlreadyCreated socketAlreadyCreated) {
            socketAlreadyCreated.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        disableCreateMenu();
        initGame();

    }

    @FXML
    public void ConnectOnClick(ActionEvent actionEvent) {
        try {
            connection.createClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

        disableCreateMenu();
        initGame();
    }

    private void disableCreateMenu() {
        hostMenu.setDisable(true);
        connectMenu.setDisable(true);
    }

    private void buttonOnClick(Button button) {

    }

    private void initGame() {
        initButtons(userGrid);
        initButtons(enemyGrid);
    }

    private void initButtons(GridPane grid) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setMaxHeight(Double.MAX_VALUE);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setUserData(i + " " + j);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> buttonOnClick(button));
                grid.add(button, i,j);
            }
        }
    }

    public void initText(GridPane grid) {
        for (int i = 1; i < 11; i++) {
            Label letter = new Label();
            letter.setText(String.valueOf(Character.valueOf((char) ('A' + i - 1))));
            Label number = new Label();
            number.setText(String.valueOf(i));
            grid.add(letter, i, 0);
            grid.add(number, 0, i);
        }
    }




}
