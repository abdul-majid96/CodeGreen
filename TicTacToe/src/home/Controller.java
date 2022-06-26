package home;
import AI.AdversarialSearchTicTacToe;
import AI.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Button btnPvC;
    @FXML
    private Button btnPvP;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnRestart;
    public int playerTurn = 0;
    ArrayList<Button> buttons;

    Random random = new Random();
    AdversarialSearchTicTacToe ticTacToeAI = new AdversarialSearchTicTacToe();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9));
            for (Button start : buttons) {
                start.setDisable(true);
           }
    }
    public void resetGame(Button button){
        button.setDisable(false);
        button.setText("");
    }
    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }

    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            button.setText("X");
            button.setStyle("-fx-font-size:40;"+"-fx-text-fill: #05D0D6");
            playerTurn = 1;
        }
        else{
            button.setText("O");
            button.setStyle("-fx-font-size:40;"+"-fx-text-fill: #FF5436");
            playerTurn = 0;
        }
    }
    public void checkIfGameIsOver(){
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> btn1.getText() + btn2.getText() + btn3.getText();
                case 1 -> btn4.getText() + btn5.getText() + btn6.getText();
                case 2 -> btn7.getText() + btn8.getText() + btn9.getText();
                case 3 -> btn1.getText() + btn5.getText() + btn9.getText();
                case 4 -> btn3.getText() + btn5.getText() + btn7.getText();
                case 5 -> btn1.getText() + btn4.getText() + btn7.getText();
                case 6 -> btn2.getText() + btn5.getText() + btn8.getText();
                case 7 -> btn3.getText() + btn6.getText() + btn9.getText();
                default -> null;
            };
            //X winner
            if (line.equals("XXX")) {
                label2.setText("X won!");
                label2.setStyle("-fx-text-fill: #05D0D6");
                for (Button button : buttons) {
                    button.setDisable(true);
                }
            }
            //O winner
            else if (line.equals("OOO")) {
                label2.setText("O won!");
                label2.setStyle("-fx-text-fill: #FF5436");
                for (Button button : buttons) {
                    button.setDisable(true);
                }
            }

        }
    }
//CPU Player setup
    public void setupButtonAI(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            button.setText("O");
            button.setStyle("-fx-font-size:40;"+"-fx-text-fill: #FF5436");
            button.setDisable(true);
            makeAIMove();
            checkIfGameIsOver();
        });
    }

    public void makeAIMove(){
        int move = ticTacToeAI.minMaxDecision(getBoardState());
        pickButton(move);
    }

    private void pickButton(int index) {
        buttons.get(index).setText("X");
        buttons.get(index).setStyle("-fx-font-size:40;"+"-fx-text-fill: #05D0D6");
        buttons.get(index).setDisable(true);
    }

    public State getBoardState(){
        String[] board = new String[9];

        for (int i = 0; i < buttons.size(); i++) {
            board[i] = buttons.get(i).getText();
        }
        return new State(0,board);
    }

    public void handleClicks(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnPvC) {
            buttons = new ArrayList<>(Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9));
            buttons.forEach(button ->{
                setupButtonAI(button);
                button.setFocusTraversable(false);
            });
            label1.setText("Player vs CPU");
            label2.setText("");
            buttons.forEach(this::resetGame);
            for (Button button : buttons) {
                button.setDisable(false);
            }
            pickButton(random.nextInt(9));
        }
        if (actionEvent.getSource() == btnPvP) {
            buttons = new ArrayList<>(Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9));
            buttons.forEach(button ->{
                setupButton(button);
                button.setFocusTraversable(false);
                for (Button start : buttons) {
                    start.setDisable(true);
                }
            });
            label1.setText("Player vs Player");
            label2.setText("");
            buttons.forEach(this::resetGame);
            for (Button button : buttons) {
                button.setDisable(false);
            }
        }
        if (actionEvent.getSource() == btnRestart) {

            if (label1.getText()=="Player vs CPU") {
                buttons.forEach(this::resetGame);
                label2.setText("");
                pickButton(random.nextInt(9));
            }
            if (label1.getText()=="Player vs Player"){
                buttons.forEach(this::resetGame);
                label2.setText("");
            }
        }
    }
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

}
