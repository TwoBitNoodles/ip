package musangking.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for MusangKing using FXML.
 */
public class Main extends Application {

    private MusangKing musangking = new MusangKing();

    @Override
    public void start(Stage stage) {
        try {
            // load gui
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMusangKing(musangking);  // inject the MusangKing instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
