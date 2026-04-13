package photos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import photos.controller.LoginController;
import photos.model.UserManager;

/**
 * Main entry point for the Photos JavaFX application.
 * Loads the login screen and sets up auto-save on window close.
 */
public class Photos extends Application {

    /**
     * Starts the JavaFX application.
     * Loads login.fxml into the primary stage and sets up
     * the window close handler to save data safely.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/photos/view/login.fxml"));
        Scene scene = new Scene(loader.load());

        LoginController controller = loader.getController();
        controller.setStage(primaryStage);

        // Save on window close — satisfies the "quit safely" requirement
        primaryStage.setOnCloseRequest(e -> {
            try {
                UserManager.getInstance().save();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        primaryStage.setTitle("Photos Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Application entry point.
    public static void main(String[] args) {
        launch(args);
    }
}