package MainGUI;



import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class mediafx {

    private static double anchorX, anchorY;
    private static double anchorAngleX = 0, anchorAngleY = 0;
    private static final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private static final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    // Method to create a JFXPanel containing the 3D axes
    public static JFXPanel create3DAxisPanel() {
        JFXPanel fxPanel = new JFXPanel();

        Platform.runLater(() -> {
            // Materials for the axes
            PhongMaterial redMaterial = new PhongMaterial(Color.RED);
            PhongMaterial greenMaterial = new PhongMaterial(Color.GREEN);
            PhongMaterial blueMaterial = new PhongMaterial(Color.BLUE);

            // Create X axis (red)
            Group xAxis = createArrow(60, 5, redMaterial);
            xAxis.getTransforms().add(new Rotate(90, Rotate.Z_AXIS));

            // Create Y axis (green)
            Group yAxis = createArrow(60, 5, greenMaterial);

            // Create Z axis (blue)
            Group zAxis = createArrow(60, 5, blueMaterial);
            zAxis.getTransforms().add(new Rotate(90, Rotate.X_AXIS));

            // Combine all axes into a group
            Group axes = new Group(xAxis, yAxis, zAxis);
            axes.getTransforms().addAll(rotateX, rotateY);

            // Adjust positioning of the axes
            axes.getTransforms().add(new Translate(0, 0, 0));

            // Camera setup
            PerspectiveCamera camera = new PerspectiveCamera(true);
            camera.setTranslateX(0);
            camera.setTranslateY(0);
            camera.setTranslateZ(-300);
            camera.setNearClip(0.1);
            camera.setFarClip(1000);

            // Scene setup
            Group root = new Group(axes);
            Scene scene = new Scene(root, 800, 600, true);
            scene.setFill(Color.BLACK);
            scene.setCamera(camera);

            // Mouse interaction for rotation
            scene.setOnMousePressed(event -> {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngleX = rotateX.getAngle();
                anchorAngleY = rotateY.getAngle();
            });

            scene.setOnMouseDragged(event -> {
                rotateX.setAngle(anchorAngleX - (anchorY - event.getSceneY()));
                rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()));
            });

            // Attach the JavaFX scene to the JFXPanel
            fxPanel.setScene(scene);
        });

        return fxPanel;
    }

    // Method to create an arrow with a cylinder (body)
    private static Group createArrow(double length, double radius, PhongMaterial material) {
        // Cylinder (body of the arrow)
        Cylinder cylinder = new Cylinder(radius, length);
        cylinder.setMaterial(material);
        cylinder.setTranslateY(-length / 2);

        // Combine into a group
        Group arrow = new Group(cylinder);
        return arrow;
    }
}
