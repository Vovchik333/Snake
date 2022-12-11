package objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Apple {
    private double x;
    private double y;
    private final double size = 10;

    public Apple(){
        x = getNum((int)Math.round(Math.random() * 390));
        y = getNum((int)Math.round(Math.random() * 390));
    }

    public static int getNum(int num){
        return num - (num % 10);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void drawApple(GraphicsContext graphicsContext){
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(x, y, size, size);
    }
}
