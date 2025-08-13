package project.com.demo1;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point2D> body;
    private Direction direction;
    private int size;

    public Snake(int startX, int startY, int size) {
        this.size = size;
        body = new ArrayList<>();
        body.add(new Point2D(startX, startY));
        direction = Direction.RIGHT;
    }

    public void move() {
        Point2D head = body.get(0);
        Point2D newHead = head;

        switch (direction) {
            case UP -> newHead = head.add(0, -size);
            case DOWN -> newHead = head.add(0, size);
            case LEFT -> newHead = head.add(-size, 0);
            case RIGHT -> newHead = head.add(size, 0);
        }

        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    public void grow() {
        body.add(body.get(body.size() - 1));
    }

    public boolean checkCollision(int width, int height) {
        Point2D head = body.get(0);
        if (head.getX() < 0 || head.getY() < 0 ||
                head.getX() >= width || head.getY() >= height) return true;

        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) return true;
        }
        return false;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        for (Point2D point : body) {
            gc.fillRect(point.getX(), point.getY(), size, size);
        }
    }

    public Point2D getHead() { return body.get(0); }
    public void setDirection(Direction dir) { this.direction = dir; }
    public Direction getDirection() { return direction; }
}
