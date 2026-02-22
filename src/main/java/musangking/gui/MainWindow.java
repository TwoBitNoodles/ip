package musangking.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private MusangKing musangking;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image musangkingImage = new Image(this.getClass().getResourceAsStream("/images/musangking.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setMusangKing(MusangKing mk) {
        musangking = mk;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = musangking.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, musangkingImage)
        );
        userInput.clear();

        if (response.equals(Ui.GOODBYE.toString())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(4));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}
