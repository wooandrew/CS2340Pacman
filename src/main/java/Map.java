import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Map extends Application {
    private int points;

    public void start(Stage stage) {
        
        Scene scene = new Scene(root, 850, 850);
        stage.setTitle("Problem Ideation Form");
        stage.setScene(scene);
        stage.show();
    }

}
