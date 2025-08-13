package project.com.demo1;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class Apple {
    private int x, y, size;
    private int width, height;

    public Apple(int size, int width, int height) {
        this.size = size;
        this.width = width;
        this.height = height;
        spawn();
    }

    public void spawn() {
        Random random = new Random();
        x = random.nextInt(width / size) * size;
        y = random.nextInt(height / size) * size;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x, y, size, size);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
