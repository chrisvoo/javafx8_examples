/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * It requires RunWithArguments plugin:
 * http://plugins.netbeans.org/plugin/53855/run-with-arguments How to deploy
 * JavaFX apps:
 * http://docs.oracle.com/javafx/2/deployment/deploy_quick_start.htm
 *
 * @author chris
 */
public class HelloWorld extends Application {

    /**
     * It runs code specified by the method name passed as argument to the
     * application
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        String app = getParameters().getRaw().get(0);
        try {
            java.lang.reflect.Method method = this.getClass().getMethod(app, Stage.class);
            method.invoke(this, primaryStage);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Draws a button inside a Stage. Stage is equivalent to an application
     * window similar to Java Swing API JFrame or JDialog
     *
     * @param primaryStage passed by start method
     */
    public void helloWorld(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        // lambda expression for new EventHandler<ActionEvent>() {
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        /*
        You can think of a Scene object as a content pane, similar
        to Java Swing’s JPanel, capable of holding zero-to-many Node objects
         */
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shows how to drow lines and their properties
     *
     * @param primaryStage passed by start method
     */
    public void drawingLines(Stage primaryStage) {
        primaryStage.setTitle("Chapter 2 Drawing Lines");

        Group root = new Group();
        // default background color: white
        Scene scene = new Scene(root, 300, 150, Color.GRAY);

        // Red line
        Line redLine = new Line(10, 10, 200, 10);

        // setting common properties
        /* There are many ways to specify color, such as using RGB, HSB,
          or Web hex values. All three methods also have the ability
        to specify alpha value (transparency). */
        redLine.setStroke(Color.RED);
        redLine.setStrokeWidth(10);
        redLine.setStrokeLineCap(StrokeLineCap.BUTT); // end of line

        // creating a dashed pattern
        /*
        Each value represents the number of pixels for a dash segment. To set
        the dash segment array, the first value (10d) is a visible dash segment
        10 pixels wide. Next is a five-pixel empty segment (not visible)...
         */
        redLine.getStrokeDashArray().addAll(10d, 5d, 15d, 5d, 20d);
        redLine.setStrokeDashOffset(0);

        /*
        All root nodes (those extending javafx.scene.Parent) have a method
        getChildren().add() to allow any JavaFX node to be added to the scene graph. */
        root.getChildren().add(redLine);

        // White line
        Line whiteLine = new Line(10, 30, 200, 30);
        whiteLine.setStroke(Color.WHITE);
        whiteLine.setStrokeWidth(10);
        whiteLine.setStrokeLineCap(StrokeLineCap.ROUND);

        root.getChildren().add(whiteLine);

        // Blue line
        Line blueLine = new Line(10, 50, 200, 50);
        blueLine.setStroke(Color.BLUE);
        blueLine.setStrokeWidth(10);

        root.getChildren().add(blueLine);

        // slider min, max, and current value
        Slider slider = new Slider(0, 100, 0);
        slider.setLayoutX(10);
        slider.setLayoutY(95);

        // bind the stroke dash offset property
        redLine.strokeDashOffsetProperty().bind(slider.valueProperty());
        root.getChildren().add(slider);

        Text offsetText = new Text("Stroke Dash Offset: ");
        offsetText.setX(10);
        offsetText.setY(80);
        offsetText.setStroke(Color.WHITE);

        // display stroke dash offset value
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number curVal, Number newVal) -> {
            offsetText.setText("Stroke Dash Offset: " + slider.getValue());
        });
        root.getChildren().add(offsetText);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shows how to drow Shapes
     *
     * @param primaryStage passed by start method
     */
    public void drawingShapes(Stage primaryStage) {
        primaryStage.setTitle("Chapter 2 Drawing Shapes");
        Group root = new Group();
        Scene scene = new Scene(root, 306, 550, Color.WHITE);

        Rectangle roundRect = new Rectangle();
        roundRect.setX(45);
        roundRect.setY(45);
        roundRect.setWidth(100);
        roundRect.setHeight(50);
        // This feature will draw rounded corners on a rectangle
        roundRect.setArcWidth(10);
        roundRect.setArcHeight(40);
        roundRect.setStrokeType(StrokeType.CENTERED);
        roundRect.setStroke(Color.BLACK);
        roundRect.setStrokeWidth(2);
        roundRect.setFill(Color.WHITE);
        root.getChildren().add(roundRect);

        // Sine wave
        CubicCurve cubicCurve = new CubicCurve(
                50, // start x point
                75, // start y point
                80, // control x1 point
                -25, // control y1 point
                110, // control x2 point
                175, // control y2 point
                140, // end x point
                75); // end y point
        cubicCurve.setStrokeType(StrokeType.CENTERED);
        cubicCurve.setStroke(Color.BLACK);
        cubicCurve.setStrokeWidth(3);
        cubicCurve.setFill(Color.WHITE);

        root.getChildren().add(cubicCurve);

        // Ice cream cone
        Path path = new Path();
        path.setStrokeWidth(3);

        // create top part beginning on the left
        MoveTo moveTo = new MoveTo();
        moveTo.setX(50);
        moveTo.setY(150);

        // curve ice cream (dome)
        QuadCurveTo quadCurveTo = new QuadCurveTo();
        quadCurveTo.setX(150);
        quadCurveTo.setY(150);
        quadCurveTo.setControlX(100);
        quadCurveTo.setControlY(50);

        // cone rim
        LineTo lineTo1 = new LineTo();
        lineTo1.setX(50);
        lineTo1.setY(150);

        // left side of cone
        LineTo lineTo2 = new LineTo();
        lineTo2.setX(100);
        lineTo2.setY(275);

        // right side of cone
        LineTo lineTo3 = new LineTo();
        lineTo3.setX(150);
        lineTo3.setY(150);

        path.getElements().addAll(moveTo, quadCurveTo, lineTo1, lineTo2, lineTo3);

        path.setTranslateY(30);

        root.getChildren().add(path);

        // A smile
        QuadCurve quad = new QuadCurve(
                50, // start x point
                50, // start y point
                125,// control x point
                150,// control y point
                150,// end x point
                50);// end y point
        quad.setTranslateY(path.getBoundsInParent().getMaxY());
        quad.setStrokeWidth(3);
        quad.setStroke(Color.BLACK);
        quad.setFill(Color.WHITE);

        root.getChildren().add(quad);

        // outer donut
        Ellipse bigCircle = new Ellipse(
                100, // center x
                100, // center y
                50, // radius x
                75 / 2); // radius y
        bigCircle.setStrokeWidth(3);
        bigCircle.setStroke(Color.BLACK);
        bigCircle.setFill(Color.WHITE);

        // donut hole
        Ellipse smallCircle = new Ellipse(
                100, // center x
                100, // center y
                35 / 2, // radius x
                25 / 2); // radius y

        // make a donut
        Shape donut = Path.subtract(bigCircle, smallCircle);
        donut.setStrokeWidth(1.8);
        donut.setStroke(Color.BLACK);

        // orange glaze
        donut.setFill(Color.rgb(255, 200, 0));

        // add drop shadow
        DropShadow dropShadow = new DropShadow(
                5, // radius
                2.0f, // offset X
                2.0f, // offset Y
                Color.rgb(50, 50, 50, .588));

        donut.setEffect(dropShadow);

        // move slightly down
        donut.setTranslateY(quad.getBoundsInParent().getMinY() + 30);

        root.getChildren().add(donut);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
