package paint_and_music;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintScene {
    public static void drawScene(GraphicsContext graphicsContext, Canvas canvas){
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
