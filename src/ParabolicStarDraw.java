import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ParabolicStarDraw extends Application {

    final int boxSize = 600;
    final int starSize = 300;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(boxSize, boxSize);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        primaryStage.setTitle("Drawing Operations Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    int a = 3;

                    @Override
                    public void handle(MouseEvent e) {
                        clear(gc);
                        drawStar(gc, new Point2D(boxSize / 4, boxSize / 4), a);
                        drawStar(gc, new Point2D(boxSize / 4 * 3, boxSize / 4 * 3), a);
                        drawStar(gc, new Point2D(boxSize / 4 * 3, boxSize / 4), a);
                        drawStar(gc, new Point2D(boxSize / 4, boxSize / 4 * 3), a);
                        a++;
                    }
                });
    }

    private void clear(GraphicsContext gc) {
        gc.clearRect(0, 0, boxSize, boxSize);
    }

    private void drawStar(GraphicsContext gc, Point2D center, double vertices) {
        double angle = 360 / vertices;
        for (double i = angle; i <= 361; i = i + angle) {
            drawVertex(gc, center, starSize, i, angle);
        }
    }

    private void drawVertex(GraphicsContext gc, Point2D center, int starRadius,
                            double initAngle, double angleStep) {
        int size = starRadius / 2;
        double step = size * 0.1;

        for (double i = 0; i <= size; i = i + step) {
            double angleStart = initAngle * Math.PI / 180;
            double startX = center.getX() + i * Math.sin(angleStart);
            double startY = center.getY() + i * Math.cos(angleStart);

            double angleEnd = (initAngle + angleStep) * Math.PI / 180;
            double endX = center.getX() + (size - i) * Math.sin(angleEnd);
            double endY = center.getY() + (size - i) * Math.cos(angleEnd);

            gc.strokeLine(startX, startY, endX, endY);
        }
    }
}