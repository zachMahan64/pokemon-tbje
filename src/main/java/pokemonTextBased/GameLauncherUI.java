package pokemonTextBased;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.ExecutionException;

public class GameLauncherUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pokémon Text-Based Adventure Launcher");

        // Main container
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #1e1e1e;");

        // Title
        Text title = new Text("Pokémon Text-Based Adventure");
        title.setFont(Font.font("Consolas", 30));
        title.setStyle("-fx-fill: #ffffff;");
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        // Image placeholder
        ImageView logo = new ImageView(new Image("file:resources/titleLogoImage.png"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(300);

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(logo);

        // Launch Button
        Button launchButton = new Button("Launch Game");
        launchButton.setFont(Font.font("Consolas", 16));
        launchButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-background-radius: 10;");

        launchButton.setOnAction(e -> {
            primaryStage.hide();
            try {
                Game.main(null);
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        });

        centerBox.getChildren().add(launchButton);

        root.setCenter(centerBox);

        // Scene setup
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
