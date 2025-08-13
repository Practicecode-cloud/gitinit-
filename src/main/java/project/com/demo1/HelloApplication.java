package project.com.demo1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Game game = new Game(600, 600);
        Scene scene = new Scene(game, 600, 600);

        primaryStage.setTitle("Snake Game - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

        game.requestFocus(); // Ensure arrow keys work immediately
        game.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
