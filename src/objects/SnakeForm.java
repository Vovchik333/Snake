package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class SnakeForm {
    private static int lengthSnake;
    private ArrayList<BodyPart> body;

    public SnakeForm(){
        lengthSnake = 0;
        body = new ArrayList<>();
    }

    public ArrayList<BodyPart> getBody() {
        return body;
    }

    public static int getLength() {
        return lengthSnake;
    }

    public void enlargeBody(double x, double y){
        body.add(new BodyPart(x, y));
        lengthSnake++;
    }

    public void drawHead(GraphicsContext graphicsContext, BodyPart head){
        graphicsContext.setFill(Color.CYAN);
        graphicsContext.fillRect(head.getX(), head.getY(), head.getSize(), head.getSize());
    }

    public void drawBody(GraphicsContext graphicsContext, BodyPart bodyPart){
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillRect(bodyPart.getX(), bodyPart.getY(), bodyPart.getSize(), bodyPart.getSize());
        graphicsContext.setFill(Color.LEMONCHIFFON);
        graphicsContext.fillRoundRect(bodyPart.getX(), bodyPart.getY(), bodyPart.getSize(), bodyPart.getSize(), 5, 5);
    }
}