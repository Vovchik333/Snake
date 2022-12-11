package moves;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import objects.*;
import paint_and_music.MusicalAccompaniment;
import paint_and_music.PaintScene;

public class SnakeController {
    @FXML
    private Canvas canvas1;

    @FXML
    private Button exit_btn;

    @FXML
    private Button help_btn;

    @FXML
    private Button pause_btn;

    @FXML
    private ComboBox<String> speed_combo;

    @FXML
    private TextField score1;

    @FXML
    private Button start_btn;

    private GraphicsContext graphicsContext;
    private boolean isPause;
    private boolean isWork = false;
    private Timeline timeline;
    private double speedAnimation = 250;

    @FXML
    void initialize() {
        MusicalAccompaniment.playMusic();
        ObservableList<String> list = FXCollections.observableArrayList("100", "250", "500", "1000");
        speed_combo.setItems(list);
        graphicsContext = canvas1.getGraphicsContext2D();
        PaintScene.drawScene(graphicsContext, canvas1);
        speed_combo.setOnAction(event ->
                speedAnimation = Double.parseDouble(speed_combo.getSelectionModel().getSelectedItem()));
        start_btn.setOnAction(event -> newGame());
        pause_btn.setOnAction(event -> isPause = !isPause);
        exit_btn.setOnAction(event -> System.exit(0));
    }

    void newGame()  {
        if(isWork) {
            timeline.stop();
            isWork = false;
        }
        MoveSnake.setInitialParameters();
        PaintScene.drawScene(graphicsContext, canvas1);
        Apple apple = new Apple();
        MoveSnake moveSnake = new MoveSnake(graphicsContext, canvas1, new SnakeForm(), apple, score1);
        apple.drawApple(graphicsContext);
        isPause = false;
        draw(moveSnake);
    }

    void draw(MoveSnake moveSnake) {
        timeline = new Timeline(new KeyFrame(Duration.millis(speedAnimation), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isPause)
                    moveSnake.move();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        isWork = true;
        timeline.play();
    }

    @FXML
    void keyPressed1(KeyEvent event) {
        if(event.getCode() == KeyCode.D)
            MoveSnake.turnRight();
        else if (event.getCode() == KeyCode.A)
            MoveSnake.turnLeft();
    }
}