/* @author ccastelli
 * GUI architectures: http://martinfowler.com/eaaDev/uiArchs.html.
 * In short, JavaFX’s properties are wrapper objects holding actual values while providing change
 * support, invalidation support, and binding capabilities.
 * Properties are wrapper objects that have the ability to make values accessible as read/writable
 * or read-only. All wrapper property classes are located in the javafx.beans.property.* package
 * namespace.
 * Property change support is the ability to add handler code that will respond when a property changes. 
 * JavaFX property objects contain an addListener() method. This method will accept two types of 
 * functional interfaces, ChangeListener and InvalidationListener. 
 *
 * // Adding a change listener (lambda expression)
 * xProperty.addListener((ObservableValue<? extends Number> ov, Number oldVal, Number newVal) -> {
 *  // code goes here 
 * });
 * 
 * // Adding a invalidation listener (lambda expression)
 * xProperty.addListener((Observable o) -> { 
 *  // code goes here
 * });

    A change event indicates that the value has changed. An invalidation event is generated, if the
    current value is not valid anymore. This distinction becomes important, if the ObservableValue
    supports lazy evaluation, because for a lazily evaluated value one does not know if an invalid value
    really has changed until it is recomputed. For this reason, generating change events requires eager
    evaluation while invalidation events can be generated for eager and lazy implementations.
    The InvalidationListener provides a way to mark values as invalid but does not recompute the
    value until it is needed. This is often used in UI layouts or custom controls, where you can avoid
    unnecessary computations when nodes don’t need to be redrawn/repositioned during a layout
    request or draw cycle.
    Binding of properties is quite easy to do. The only requirement is that the property invoking the bind
    must be a read/writeable property.
    when property A binds
    to property B the change in property B will update property A, but not the other way. If A is bound to
    B you can’t update A, as you’ll get a RuntimeException: A bound value cannot be set.
    
    Bidirectional Binding
    ---------------------------
    allows you to bind properties with the same type allowing changes on either
    end while keeping a value synchronized. When binding bi-directionally, it’s required that both
    properties must be read/writable.

    High-level Binding
    ---------------------------
    binding is lazy-evaluated, which means the computation (multiplying) doesn’t occur unless you invoke the
    property’s (area) value via the get() (or getValue())method.
    // Area = width * height
    IntegerProperty width = new SimpleIntegerProperty(10);
    IntegerProperty height = new SimpleIntegerProperty(10);

    NumberBinding area = width.multiply(height);
    
    Low-Level Binding
    ---------------------------
    DoubleProperty radius = new SimpleDoubleProperty(2);
        DoubleBinding volumeOfSphere = new DoubleBinding() {
        {
            super.bind(radius); // initial bind
        }

        @Override
        protected double computeValue() {
            // Math.pow() (power) cubes the radius
            return (4 / 3 * Math.PI * Math.pow(radius.get(), 3));
        }
    };
    

 */
package helloworld;

import helloworld.models.User;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
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
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


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
        } catch (Exception ex) {
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
    
    public void loginDialog(Stage primStage) {
        // create a model representing a user
        User user = new User();
        String MY_PASS = "password1";
        BooleanProperty GRANTED_ACCESS = new SimpleBooleanProperty(false);
        int MAX_ATTEMPTS = 3;
        IntegerProperty ATTEMPTS = new SimpleIntegerProperty(0);
        
        // create a transparent stage
        primStage.initStyle(StageStyle.TRANSPARENT);
        
        Group root = new Group();
        Scene scene = new Scene(root, 320, 112, Color.rgb(0, 0, 0, 0));
        primStage.setScene(scene);
        
        // all text, borders, svg paths will use white
        Color foregroundColor = Color.rgb(255, 255, 255, .9);
        
        // rounded rectangular background 
        Rectangle background = new Rectangle(320, 112);
        background.setX(0);
        background.setY(0);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setFill(Color.rgb(0, 0, 0, .55));
        background.setStrokeWidth(1.5);
        background.setStroke(foregroundColor);
        
        // a read only field holding the user name.
        Text userName = new Text();
        userName.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
        userName.setFill(foregroundColor);
        userName.setSmooth(true);
        userName.textProperty().bind(user.userNameProperty());
        
        // wrap text node
        HBox userNameCell = new HBox();
        userNameCell.prefWidthProperty().bind(primStage.widthProperty().subtract(45));
        userNameCell.getChildren().add(userName);
        
        // pad lock 
        SVGPath padLock = new SVGPath();
        padLock.setFill(foregroundColor);
        padLock.setContent("M24.875,15.334v-4.876c0-4.894-3.981-8.875-8.875-8.875s-8.875,3.981-8.875,8.875v4.876H5.042v15.083h21.916V15.334H24.875zM10.625,10.458c0-2.964,2.411-5.375,5.375-5.375s5.375,2.411,5.375,5.375v4.876h-10.75V10.458zM18.272,26.956h-4.545l1.222-3.667c-0.782-0.389-1.324-1.188-1.324-2.119c0-1.312,1.063-2.375,2.375-2.375s2.375,1.062,2.375,2.375c0,0.932-0.542,1.73-1.324,2.119L18.272,26.956z");
        
        // first row 
        HBox row1 = new HBox();
        row1.getChildren().addAll(userNameCell, padLock);
        
        
        // password text field 
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("SanSerif", 20));
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-text-fill:black; "
                            + "-fx-prompt-text-fill:gray; "
                            + "-fx-highlight-text-fill:black; "
                            + "-fx-highlight-fill: gray; "
                            + "-fx-background-color: rgba(255, 255, 255, .80); ");
        passwordField.prefWidthProperty().bind(primStage.widthProperty().subtract(55));
        user.passwordProperty().bind(passwordField.textProperty());
        
        // error icon 
        SVGPath deniedIcon = new SVGPath();
        deniedIcon.setFill(Color.rgb(255, 0, 0, .9));
        deniedIcon.setStroke(Color.WHITE);// 
        deniedIcon.setContent("M24.778,21.419 19.276,15.917 24.777,10.415 21.949,7.585 16.447,13.087 10.945,7.585 8.117,10.415 13.618,15.917 8.116,21.419 10.946,24.248 16.447,18.746 21.948,24.248z");
        deniedIcon.setVisible(false);
        
        SVGPath grantedIcon = new SVGPath();
        grantedIcon.setFill(Color.rgb(0, 255, 0, .9));
        grantedIcon.setStroke(Color.WHITE);// 
        grantedIcon.setContent("M2.379,14.729 5.208,11.899 12.958,19.648 25.877,6.733 28.707,9.561 12.958,25.308z");
        grantedIcon.setVisible(false);
        
        StackPane accessIndicator = new StackPane();
        accessIndicator.getChildren().addAll(deniedIcon, grantedIcon);
        accessIndicator.setAlignment(Pos.CENTER_RIGHT);
        
        grantedIcon.visibleProperty().bind(GRANTED_ACCESS);
        
        // second row
        HBox row2 = new HBox(3);
        row2.getChildren().addAll(passwordField, accessIndicator);
        HBox.setHgrow(accessIndicator, Priority.ALWAYS);
        
        // user hits the enter key
        passwordField.setOnAction(actionEvent -> {
            if (GRANTED_ACCESS.get()) {
                System.out.printf("User %s is granted access.\n", user.getUserName());
                System.out.printf("User %s entered the password: %s\n", user.getUserName(), user.getPassword());
                Platform.exit();
            } else {
                deniedIcon.setVisible(true); 
            }
            ATTEMPTS.set(ATTEMPTS.add(1).get());
            System.out.println("Attempts: " + ATTEMPTS.get());
        });
        
        // listener when the user types into the password field
        passwordField.textProperty().addListener((obs, ov, nv) -> {
            boolean granted = passwordField.getText().equals(MY_PASS);
            GRANTED_ACCESS.set(granted);
            if (granted) {
                deniedIcon.setVisible(false);
            }
        });
        
        // listener on number of attempts
        ATTEMPTS.addListener((obs, ov, nv) -> {
            if (MAX_ATTEMPTS == nv.intValue()) {
                // failed attemps
                System.out.printf("User %s is denied access.\n", user.getUserName());
                Platform.exit();
            }
        });
        
        VBox formLayout = new VBox(4);
        formLayout.getChildren().addAll(row1, row2);
        formLayout.setLayoutX(12);
        formLayout.setLayoutY(12);

        root.getChildren().addAll(background, formLayout);

        primStage.show();
    }
}
