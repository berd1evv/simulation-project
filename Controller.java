package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane scene;

    @FXML
    private Circle circle;


    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

        double X = 2;
        double Y = 2;

        @Override
        public void handle(ActionEvent actionEvent) {
            circle.setLayoutX(circle.getLayoutX() + X);
            circle.setLayoutY(circle.getLayoutY() + Y);

            Bounds bounds = scene.getBoundsInLocal();
            boolean rightWall = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
            boolean leftWall = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
            boolean bottomWall = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
            boolean topWall = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

            if (rightWall || leftWall) {
                X *= -1;
            }
            if (bottomWall || topWall) {
                Y *= -1;
            }
        }
    }));


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}