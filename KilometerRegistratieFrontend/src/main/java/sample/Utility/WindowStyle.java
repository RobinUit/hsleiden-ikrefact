package sample.Utility;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WindowStyle {

    private static final Rectangle2D screenSize =
            Screen.getPrimary().getVisualBounds();

    private double[] mouseOffsetXY;
    private Parent root;
    private Stage currentStage;

    public void enableStageDrag(Parent root, Stage currentStage) {
        this.root = root;
        this.currentStage = currentStage;

        handleMouseEvents();
    }

    private void handleMouseEvents() {
        root.setOnMousePressed(this::getMouseOffset);
        root.setOnMouseReleased(this::preventStageReleasedPastTop);
        root.setOnMouseDragged(this::moveStage);
    }

    private void getMouseOffset(MouseEvent mousePressed) {
        mouseOffsetXY = new double[] {
                mousePressed.getSceneX(),
                mousePressed.getSceneY()
        };
    }

    private void preventStageReleasedPastTop(MouseEvent mouseReleased) {
        if (currentStage.getY()<0.0) {
            currentStage.setY(0.0);
        }
    }

    private void moveStage(MouseEvent mouseDragged) {
        preventStageDraggedPastTaskbar(mouseDragged);

        currentStage.setX(mouseDragged.getScreenX() - mouseOffsetXY[0]);
    }

    private void preventStageDraggedPastTaskbar(MouseEvent mouseDragged) {
        if (mouseDragged.getScreenY()<(screenSize.getMaxY()-20)) {
            currentStage.setY(mouseDragged.getScreenY() - mouseOffsetXY[1]);
        }
    }
}
