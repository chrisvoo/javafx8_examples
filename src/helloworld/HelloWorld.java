/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
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
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
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
import javafx.scene.text.Font;
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

        /* Sine wave
         * The startX, startY, endX, and endY parameters are the starting and
         * ending points of a curved line. Control points are used to
         * stretch or enlarge sine wave. A control point is a line perpendicular
         * to a tangent line on the curve.    */
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
        /*
         Path elements actually extend from the javafx.scene.shape.PathElement
         class, which is used only in the context of a Path object. So you won’t
         be able to instantiate a LineTo class to be put in the
         scene graph.
         */
        Path path = new Path();
        path.setStrokeWidth(3);

        // create top part beginning on the left. Just remember that the classes with
        // To as a suffix are path elements, not Shape nodes.
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

    /**
     * Show colors and gradient
     *
     * @param primaryStage passed by start method
     */
    public void colors(Stage primaryStage) {
        primaryStage.setTitle("Chapter 2 Painting Colors");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 300, Color.WHITE);

        // Red ellipse with radial gradient color
        Ellipse ellipse = new Ellipse(100, 50 + 70 / 2, 50, 70 / 2);
        RadialGradient gradient1 = new RadialGradient(
                0, // focusAngle
                .1, // focusDistance
                80, // centerX
                45, // centerY
                120, // radius
                false, // proportional
                CycleMethod.NO_CYCLE, // cycleMethod
                new Stop(0, Color.RED), new Stop(1, Color.BLACK) // stops
        );

        ellipse.setFill(gradient1);
        root.getChildren().add(ellipse);

        // thick black line behind second shape
        Line blackLine = new Line();
        blackLine.setStartX(170);
        blackLine.setStartY(30);
        blackLine.setEndX(20);
        blackLine.setEndY(140);
        blackLine.setFill(Color.BLACK);
        blackLine.setStrokeWidth(10.0f);
        blackLine.setTranslateY(ellipse.getBoundsInParent().getHeight() + ellipse.getLayoutY() + 10);

        root.getChildren().add(blackLine);

        // A rectangle filled with a linear gradient with a translucent color.
        Rectangle rectangle = new Rectangle();
        rectangle.setX(50);
        rectangle.setY(50);
        rectangle.setWidth(100);
        rectangle.setHeight(70);
        rectangle.setTranslateY(ellipse.getBoundsInParent().getHeight() + ellipse.getLayoutY() + 10);

        LinearGradient linearGrad = new LinearGradient(
                0, // start X
                0, // start Y
                0, // end X
                1, // end Y
                true, // proportional
                CycleMethod.NO_CYCLE, // cycle colors
                // stops
                new Stop(0.1f, Color.rgb(255, 200, 0, .784)),
                new Stop(1.0f, Color.rgb(0, 0, 0, .784)));

        rectangle.setFill(linearGrad);
        root.getChildren().add(rectangle);

        // A rectangle filled with a linear gradient with a reflective cycle.
        Rectangle roundRect = new Rectangle();
        roundRect.setX(50);
        roundRect.setY(50);
        roundRect.setWidth(100);
        roundRect.setHeight(70);
        roundRect.setArcWidth(20);
        roundRect.setArcHeight(20);
        roundRect.setTranslateY(ellipse.getBoundsInParent().getHeight()
                + ellipse.getLayoutY()
                + 10
                + rectangle.getBoundsInParent().getHeight()
                + rectangle.getLayoutY() + 10);

        LinearGradient cycleGrad = new LinearGradient(
                50, // start X
                50, // start Y
                70, // end X
                70, // end Y
                false, // proportional
                CycleMethod.REFLECT, // cycleMethod
                new Stop(0f, Color.rgb(0, 255, 0, .784)),
                new Stop(1.0f, Color.rgb(0, 0, 0, .784))
        );

        roundRect.setFill(cycleGrad);
        root.getChildren().add(roundRect);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Radom colored rotated text
     *
     * @param primaryStage passed by start method
     */
    public void text(Stage primaryStage) {
        primaryStage.setTitle("Chapter 2 Drawing Text");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt((int) scene.getWidth());
            int y = rand.nextInt((int) scene.getHeight());
            int red = rand.nextInt(255);
            int green = rand.nextInt(255);
            int blue = rand.nextInt(255);

            Text text = new Text(x, y, "JavaFX 8");

            int rot = rand.nextInt(360);
            text.setFill(Color.rgb(red, green, blue, .99));
            text.setRotate(rot);
            root.getChildren().add(text);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Changing font text
     *
     * @param primaryStage passed by start method
     */
    public void textFont(Stage primaryStage) {
        primaryStage.setTitle("Chapter 2 Changing Text Fonts");

        System.out.println("Font families: ");
        Font.getFamilies().stream().forEach(i -> {
            System.out.println(i);
        });
        System.out.println("Font names: ");
        Font.getFontNames().stream().forEach(i -> {
            System.out.println(i);
        });

        Group root = new Group();
        Scene scene = new Scene(root, 580, 250, Color.WHITE);

        // Serif with drop shadow
        Text text2 = new Text(50, 50, "JavaFX 8: Intro. by Example");
        Font serif = Font.font("Serif", 30);
        text2.setFont(serif);
        text2.setFill(Color.RED);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(2.0f);
        dropShadow.setColor(Color.rgb(50, 50, 50, .588));
        text2.setEffect(dropShadow);
        root.getChildren().add(text2);

        // SanSerif
        Text text3 = new Text(50, 100, "JavaFX 8: Intro. by Example");
        Font sanSerif = Font.font("SanSerif", 30);
        text3.setFont(sanSerif);
        text3.setFill(Color.BLUE);
        root.getChildren().add(text3);

        // Dialog
        Text text4 = new Text(50, 150, "JavaFX 8: Intro. by Example");
        Font dialogFont = Font.font("Dialog", 30);
        text4.setFont(dialogFont);
        text4.setFill(Color.rgb(0, 255, 0));
        root.getChildren().add(text4);

        // Monospaced
        Text text5 = new Text(50, 200, "JavaFX 8: Intro. by Example");
        Font monoFont = Font.font("Monospaced", 30);
        text5.setFont(monoFont);
        text5.setFill(Color.BLACK);
        root.getChildren().add(text5);

        // Reflection
        Reflection refl = new Reflection();
        refl.setFraction(0.8f);
        refl.setTopOffset(5);
        text5.setEffect(refl);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
