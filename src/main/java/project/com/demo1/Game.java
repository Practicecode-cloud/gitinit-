package project.com.demo1;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Game extends Pane {
    private final int width;
    private final int height;
    private final int size = 20;

    private Snake snake;
    private Apple apple;
    private AnimationTimer timer;
    private long lastUpdate = 0;
    private int score = 0;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;

        Canvas canvas = new Canvas(width, height);
        getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        snake = new Snake(100, 100, size);
        apple = new Apple(size, width, height);

        setFocusTraversable(true);
        requestFocus(); // Ensures key events work without clicking

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP && snake.getDirection() != Direction.DOWN)
                snake.setDirection(Direction.UP);
            else if (e.getCode() == KeyCode.DOWN && snake.getDirection() != Direction.UP)
                snake.setDirection(Direction.DOWN);
            else if (e.getCode() == KeyCode.LEFT && snake.getDirection() != Direction.RIGHT)
                snake.setDirection(Direction.LEFT);
            else if (e.getCode() == KeyCode.RIGHT && snake.getDirection() != Direction.LEFT)
                snake.setDirection(Direction.RIGHT);
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 150_000_000) { // 150ms per frame
                    update(gc);
                    lastUpdate = now;
                }
            }
        };
    }

    public void startGame() {
        timer.start();
    }

    private void update(GraphicsContext gc) {
        snake.move();

        if (snake.getHead().getX() == apple.getX() && snake.getHead().getY() == apple.getY()) {
            snake.grow();
            apple.spawn();
            score++;
        }

        if (snake.checkCollision(width, height)) {
            timer.stop();
            gc.setFill(Color.RED);
            gc.fillText("Game Over! Score: " + score, width / 2 - 50, height / 2);
            return;
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        apple.draw(gc);
        snake.draw(gc);

        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 10, 20);
    }
}
