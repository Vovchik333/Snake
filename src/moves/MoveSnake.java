package moves;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import objects.*;

public class MoveSnake {
    private SnakeForm snake;
    private GraphicsContext graphicsContext;
    private TextField score;
    private Canvas canvas;
    private Apple apple;
    private static Directions direct;
    private static Directions directTemp;
    private static double xt = 0;
    private static double yt = 0;
    private static double prev_x = 0;
    private static double prev_y = 0;
    private static BodyPart head;
    private static final double step = 10;
    private static boolean firstStep = true;
    private static boolean isForwardDirect = true;

    public static void setInitialParameters(){
        prev_x = 0;
        prev_y = 0;
        firstStep = true;
        isForwardDirect = true;
    }

    public MoveSnake(GraphicsContext graphicsContext, Canvas canvas, SnakeForm snake, Apple apple, TextField score){
        this.graphicsContext = graphicsContext;
        this.canvas = canvas;
        this.snake = snake;
        this.apple = apple;
        this.score = score;
        direct = Directions.RIGHT;
        snake.enlargeBody(0, 0);;
        head = snake.getBody().get(0);
        score.setText(SnakeForm.getLength() + "");
    }

    public void move(){
        for (int i = 0; i < SnakeForm.getLength(); i++){
            if(i == 0) {
                moveHead();
            }
            else
                moveBody(snake.getBody().get(i));
        }
        if (firstStep)
            firstStep = false;
        else
            clearStep();
        checkSnakeAteApple();
        nextHeadPos();
    }

    public void moveBody(BodyPart bodyPart){
        double x_tmp = bodyPart.getX();
        double y_tmp = bodyPart.getY();
        bodyPart.setX(prev_x);
        bodyPart.setY(prev_y);
        snake.drawBody(graphicsContext, bodyPart);
        prev_x = x_tmp;
        prev_y = y_tmp;
    }

    public void moveHead() {
        if (isForwardDirect) {
            snake.drawHead(graphicsContext, head);
        } else {
            checkTurnRes();
            head.setX(xt);
            head.setY(yt);
            direct = directTemp;
            snake.drawHead(graphicsContext, head);
            isForwardDirect = true;
        }
    }

    public void nextHeadPos(){
        prev_x = head.getX();
        prev_y = head.getY();
        if (direct == Directions.RIGHT) {
            head.setX(head.getX() + step);
            if (head.getX() >= canvas.getWidth())
                head.setX(0);
        } else if (direct == Directions.DOWN) {
            head.setY(head.getY() + step);
            if (head.getY() >= canvas.getHeight())
                head.setY(0);
        } else if (direct == Directions.LEFT) {
            head.setX(head.getX() - step);
            if (head.getX() < 0)
                head.setX(390);
        } else if (direct == Directions.UP) {
            head.setY(head.getY() - step);
            if (head.getY() < 0)
                head.setY(390);
        }
    }

    public void clearStep(){
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillRect(prev_x, prev_y, 10, 10);
    }

    public void checkSnakeAteApple() {
        if((int)apple.getX() == (int)head.getX() && (int)apple.getY() == (int)head.getY()) {
            apple.setX(Apple.getNum((int)Math.round(Math.random() * 390)));
            apple.setY(Apple.getNum((int)Math.round(Math.random() * 390)));
            snake.enlargeBody(prev_x, prev_y);
            score.setText(SnakeForm.getLength() + "");
            isEndGame();
            apple.drawApple(graphicsContext);
        }
    }

    public void checkTurnRes(){
        if(xt > 390)
            xt = 0;
        if(xt < 0)
            xt = 390;
        if(yt < 0)
            yt = 390;
        if(yt > 390)
            yt = 0;
    }

    public static void turnRight(){
        isForwardDirect = false;
        switch (direct){
            case UP:
                yt = head.getY() + step;
                xt = head.getX() + step;
                directTemp = Directions.RIGHT;
                break;
            case DOWN:
                yt = head.getY() - step;
                xt = head.getX() - step;
                directTemp = Directions.LEFT;
                break;
            case RIGHT:
                xt = head.getX() - step;
                yt = head.getY() + step;
                directTemp = Directions.DOWN;
                break;
            case LEFT:
                xt = head.getX() + step;
                yt = head.getY() - step;
                directTemp = Directions.UP;
                break;
        }
    }

    public static void turnLeft(){
        isForwardDirect = false;
        switch (direct){
            case UP:
                yt = head.getY() + step;
                xt = head.getX() - step;
                directTemp = Directions.LEFT;
                break;
            case DOWN:
                yt = head.getY() - step;
                xt = head.getX() + step;
                directTemp = Directions.RIGHT;
                break;
            case RIGHT:
                xt = head.getX() - step;
                yt = head.getY() - step;
                directTemp = Directions.UP;
                break;
            case LEFT:
                xt = head.getX() + step;
                yt = head.getY() + step;
                directTemp = Directions.DOWN;
                break;
        }
    }

    public void isEndGame(){
        if(SnakeForm.getLength() == 1600){
            System.exit(0);
        }
    }
}