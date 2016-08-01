/* @author ccastelli  */
package helloworld;

import helloworld.models.Person;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

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

    /**
     * Simple login dialog box for showing binding properties usage. In short,
     * JavaFX’s properties are wrapper objects holding actual values while
     * providing change support, invalidation support, and binding capabilities.
     * Properties are wrapper objects that have the ability to make values
     * accessible as read/writable or read-only. All wrapper property classes
     * are located in the javafx.beans.property.* package namespace. Property
     * change support is the ability to add handler code that will respond when
     * a property changes. JavaFX property objects contain an addListener()
     * method. This method will accept two types of functional interfaces,
     * ChangeListener and InvalidationListener.
     *
     * // Adding a change listener (lambda expression)
     * xProperty.addListener((ObservableValue<? extends Number> ov, Number
     * oldVal, Number newVal) -> { // code goes here });
     *
     * // Adding a invalidation listener (lambda expression)
     * xProperty.addListener((Observable o) -> { // code goes here });
     *
     * A change event indicates that the value has changed. An invalidation
     * event is generated, if the current value is not valid anymore. This
     * distinction becomes important, if the ObservableValue supports lazy
     * evaluation, because for a lazily evaluated value one does not know if an
     * invalid value really has changed until it is recomputed. For this reason,
     * generating change events requires eager evaluation while invalidation
     * events can be generated for eager and lazy implementations. The
     * InvalidationListener provides a way to mark values as invalid but does
     * not recompute the value until it is needed. This is often used in UI
     * layouts or custom controls, where you can avoid unnecessary computations
     * when nodes don’t need to be redrawn/repositioned during a layout request
     * or draw cycle. Binding of properties is quite easy to do. The only
     * requirement is that the property invoking the bind must be a
     * read/writeable property. When property A binds to property B the change
     * in property B will update property A, but not the other way. If A is
     * bound to B you can’t update A, as you’ll get a RuntimeException: A bound
     * value cannot be set.
     *
     * Bidirectional Binding --------------------------- allows you to bind
     * properties with the same type allowing changes on either end while
     * keeping a value synchronized. When binding bi-directionally, it’s
     * required that both properties must be read/writable.
     *
     * High-level Binding --------------------------- binding is lazy-evaluated,
     * which means the computation (multiplying) doesn’t occur unless you invoke
     * the property’s (area) value via the get() (or getValue())method. // Area
     * = width * height IntegerProperty width = new SimpleIntegerProperty(10);
     * IntegerProperty height = new SimpleIntegerProperty(10); NumberBinding
     * area = width.multiply(height);
     *
     * Low-Level Binding --------------------------- DoubleProperty radius = new
     * SimpleDoubleProperty(2); DoubleBinding volumeOfSphere = new
     * DoubleBinding() { { super.bind(radius); // initial bind }
     *
     * @Override protected double computeValue() { // Math.pow() (power) cubes
     * the radius return (4 / 3 * Math.PI * Math.pow(radius.get(), 3)); } };
     * @param primStage
     */
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

        // pad lock, W3C SVG path notation as a string
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
        // JavaFX CSS attributes
        passwordField.setStyle("-fx-text-fill:black; "
                + "-fx-prompt-text-fill:gray; "
                + "-fx-highlight-text-fill:black; "
                + "-fx-highlight-fill: gray; "
                + "-fx-background-color: rgba(255, 255, 255, .80); ");
        passwordField.prefWidthProperty().bind(primStage.widthProperty().subtract(55));
        user.passwordProperty().bind(passwordField.textProperty());

        // error icon, W3C SVG path notation as a string
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

        // The StackPane layout allows child nodes to be stacked
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

    /**
     * The HBox layout’s job is to place JavaFX child nodes in a horizontal row
     * (default alignment Pos.TOP_LEFT). The VBox layout is similar to an HBox,
     * except that it places child nodes stacked in a vertical column. The
     * FlowPane layout node allows child nodes in a row to flow based on the
     * available horizontal spacing and wraps nodes to the next line when
     * horizontal space is less than the total of all the nodes’ widths. The
     * BorderPane layout node allows child nodes to be placed in a top, bottom,
     * left, right, or center region.
     */
    public void flowPane(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        HBox hbox = new HBox(5);         // pixels space between child nodes, equivalent of invoking the setSpacing()
        hbox.setPadding(new Insets(1));  // padding between child nodes only
        Rectangle r1 = new Rectangle(10, 10);
        Rectangle r2 = new Rectangle(20, 20);
        Rectangle r3 = new Rectangle(5, 20);
        Rectangle r4 = new Rectangle(20, 5);

        HBox.setMargin(r1, new Insets(2, 2, 2, 2));
        hbox.getChildren().addAll(r1, r2, r3, r4);

        Rectangle v1 = new Rectangle(10, 10);
        Rectangle v2 = new Rectangle(20, 20);
        Rectangle v3 = new Rectangle(5, 20);
        Rectangle v4 = new Rectangle(20, 5);

        VBox vbox = new VBox(5);        // spacing between child nodes only.
        vbox.setPadding(new Insets(1)); // space between vbox border and child nodes column
        VBox.setMargin(r1, new Insets(2, 2, 2, 2)); // margin around r1
        vbox.getChildren().addAll(v1, v2, v3, v4);

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(hbox, vbox);

//      Alternative to flowPane is to change layoutX of vbox when windows shows up 
//       primaryStage.setOnShown((WindowEvent we) -> {
//            System.out.println("hbox width  " + hbox.getBoundsInParent().getWidth());
//            System.out.println("hbox height " + hbox.getBoundsInParent().getHeight());
//            System.out.println("vbox width  " + vbox.getBoundsInParent().getWidth());
//            System.out.println("vbox height " + vbox.getBoundsInParent().getHeight());
//            vbox.setLayoutX(hbox.getBoundsInParent().getWidth() + 2);
//        });
        primaryStage.setTitle("FlowPane Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void gridPane(Stage primaryStage) {
        primaryStage.setTitle("GridPaneForm ");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 380, 150, Color.WHITE);

        // we enable the parent (BorderPane) to resize the GridPane by giving it all the 
        // available horizontal and vertical space.
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        ColumnConstraints column1 = new ColumnConstraints(100); // fixed width
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2);

        Label fNameLbl = new Label("First Name");
        TextField fNameFld = new TextField();
        Label lNameLbl = new Label("Last Name");
        TextField lNameFld = new TextField();

        Button saveButt = new Button("Save");

        // First name label
        GridPane.setHalignment(fNameLbl, HPos.RIGHT);
        gridpane.add(fNameLbl, 0, 0);

        // Last name label
        GridPane.setHalignment(lNameLbl, HPos.RIGHT);
        gridpane.add(lNameLbl, 0, 1);

        // First name field
        GridPane.setHalignment(fNameFld, HPos.LEFT);
        gridpane.add(fNameFld, 1, 0);

        // Last name field
        GridPane.setHalignment(lNameFld, HPos.LEFT);
        gridpane.add(lNameFld, 1, 1);

        // Save button
        GridPane.setHalignment(saveButt, HPos.RIGHT);
        gridpane.add(saveButt, 1, 2);

        // Build top banner area
        FlowPane topBanner = new FlowPane();
        topBanner.setAlignment(Pos.TOP_RIGHT);
        topBanner.setPrefHeight(40);
        String backgroundStyle
                = "-fx-background-color: lightblue;"
                + "-fx-background-radius: 30%;"
                + "-fx-background-inset: 5px;";
        topBanner.setStyle(backgroundStyle);
        SVGPath svgIcon = new SVGPath();
        // icon from 
        svgIcon.setContent("M21.066,20.667c1.227-0.682,1.068-3.311-0.354-5.874c-0.611-1.104-1.359-1.998-2.109-2.623c-0.875,0.641-1.941,1.031-3.102,1.031c-1.164,0-2.231-0.391-3.104-1.031c-0.75,0.625-1.498,1.519-2.111,2.623c-1.422,2.563-1.578,5.192-0.35,5.874c0.549,0.312,1.127,0.078,1.723-0.496c-0.105,0.582-0.166,1.213-0.166,1.873c0,2.938,1.139,5.312,2.543,5.312c0.846,0,1.265-0.865,1.466-2.188c0.2,1.314,0.62,2.188,1.461,2.188c1.396,0,2.545-2.375,2.545-5.312c0-0.66-0.062-1.291-0.168-1.873C19.939,20.745,20.516,20.983,21.066,20.667zM15.5,12.201c2.361,0,4.277-1.916,4.277-4.279S17.861,3.644,15.5,3.644c-2.363,0-4.28,1.916-4.28,4.279S13.137,12.201,15.5,12.201zM24.094,14.914c1.938,0,3.512-1.573,3.512-3.513c0-1.939-1.573-3.513-3.512-3.513c-1.94,0-3.513,1.573-3.513,3.513C20.581,13.341,22.153,14.914,24.094,14.914zM28.374,17.043c-0.502-0.907-1.116-1.641-1.732-2.154c-0.718,0.526-1.594,0.846-2.546,0.846c-0.756,0-1.459-0.207-2.076-0.55c0.496,1.093,0.803,2.2,0.861,3.19c0.093,1.516-0.381,2.641-1.329,3.165c-0.204,0.117-0.426,0.183-0.653,0.224c-0.056,0.392-0.095,0.801-0.095,1.231c0,2.412,0.935,4.361,2.088,4.361c0.694,0,1.039-0.71,1.204-1.796c0.163,1.079,0.508,1.796,1.199,1.796c1.146,0,2.09-1.95,2.09-4.361c0-0.542-0.052-1.06-0.139-1.538c0.492,0.472,0.966,0.667,1.418,0.407C29.671,21.305,29.541,19.146,28.374,17.043zM6.906,14.914c1.939,0,3.512-1.573,3.512-3.513c0-1.939-1.573-3.513-3.512-3.513c-1.94,0-3.514,1.573-3.514,3.513C3.392,13.341,4.966,14.914,6.906,14.914zM9.441,21.536c-1.593-0.885-1.739-3.524-0.457-6.354c-0.619,0.346-1.322,0.553-2.078,0.553c-0.956,0-1.832-0.321-2.549-0.846c-0.616,0.513-1.229,1.247-1.733,2.154c-1.167,2.104-1.295,4.262-0.287,4.821c0.451,0.257,0.925,0.064,1.414-0.407c-0.086,0.479-0.136,0.996-0.136,1.538c0,2.412,0.935,4.361,2.088,4.361c0.694,0,1.039-0.71,1.204-1.796c0.165,1.079,0.509,1.796,1.201,1.796c1.146,0,2.089-1.95,2.089-4.361c0-0.432-0.04-0.841-0.097-1.233C9.874,21.721,9.651,21.656,9.441,21.536z");
        svgIcon.setStroke(Color.LIGHTGRAY);
        svgIcon.setFill(Color.WHITE);

        Text contactText = new Text("Contacts");
        contactText.setFill(Color.WHITE);

        Font serif = Font.font("Dialog", 30);
        contactText.setFont(serif);
        topBanner.getChildren().addAll(svgIcon, contactText);

        root.setTop(topBanner);
        root.setCenter(gridpane);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Menu example. getImtems(): collections that have the ability to notify
     * and update UI controls as items are added or removed.
     * <b>keyboard mnemonics</b>: Alt + first letter after "_"
     * <b>keyboard combinations</b>: shortcuts such as Ctrl + S (Windows) or
     * Command + S (Mac)
     *
     * @param primaryStage
     */
    public void menu(Stage primaryStage) {
        primaryStage.setTitle("Menus Example");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        final StringProperty statusProperty = new SimpleStringProperty();

        InnerShadow iShadow = new InnerShadow();
        iShadow.setOffsetX(3.5f);
        iShadow.setOffsetY(3.5f);
        final Text status = new Text();
        status.setEffect(iShadow);
        status.setX(100);
        status.setY(50);
        status.setFill(Color.LIME);
        status.setFont(Font.font(null, FontWeight.MEDIUM, 18));

        // the label will change consistently with status string property
        status.textProperty().bind(statusProperty);
        statusProperty.set("Keyboard Shortcuts\n"
                + "Ctrl-N, \n"
                + "Ctrl-S, \n"
                + "Ctrl-X \n"
                + "Ctrl-Shift-E");
        root.setCenter(status);

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // File menu - new, save, exit
        Menu fileMenu = new Menu("_File");
        MenuItem newMenuItem = new MenuItem("_New");
        newMenuItem.setOnAction(actionEvent -> statusProperty.set("Ctrl-N"));

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setMnemonicParsing(true); // comination
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S,
                KeyCombination.SHORTCUT_DOWN));
        saveMenuItem.setOnAction(actionEvent -> statusProperty.set("Ctrl-S"));

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X,
                KeyCombination.SHORTCUT_DOWN));
        exitMenuItem.setOnAction(actionEvent -> {
            statusProperty.set("Ctrl-X");
            Platform.exit();
        });

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.E,
                        KeyCombination.SHORTCUT_DOWN,
                        KeyCombination.SHIFT_DOWN),
                () -> statusProperty.set("Ctrl-Shift-E")
        );

        // if you click with mouse right button on every part of the scene but the menu,
        // it will appear a context menu with Exit menu item
        ContextMenu contextFileMenu = new ContextMenu(exitMenuItem);

        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
            if (me.getButton() == MouseButton.SECONDARY) {
                contextFileMenu.show(root, me.getScreenX(), me.getScreenY());
            } else {
                contextFileMenu.hide();
            }
        });

        fileMenu.getItems().addAll(newMenuItem,
                saveMenuItem,
                new SeparatorMenuItem(),
                exitMenuItem
        );

        // Cameras menu - camera 1, camera 2
        Menu cameraMenu = new Menu("_Cameras");
        CheckMenuItem cam1MenuItem = new CheckMenuItem("Show Camera 1");
        cam1MenuItem.setSelected(true);
        cameraMenu.getItems().add(cam1MenuItem);

        CheckMenuItem cam2MenuItem = new CheckMenuItem("Show Camera 2");
        cam2MenuItem.setSelected(true);
        cameraMenu.getItems().add(cam2MenuItem);

        // Alarm menu
        Menu alarmMenu = new Menu("_Alarm");

        // sound or turn alarm off, one exclude the other
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem soundAlarmItem = new RadioMenuItem("Sound Alarm");
        soundAlarmItem.setToggleGroup(tGroup);

        RadioMenuItem stopAlarmItem = new RadioMenuItem("Alarm Off");
        stopAlarmItem.setToggleGroup(tGroup);
        stopAlarmItem.setSelected(true);

        alarmMenu.getItems().addAll(
                soundAlarmItem,
                stopAlarmItem,
                new SeparatorMenuItem());

        Menu contingencyPlans = new Menu("Contingent Plans");
        contingencyPlans.getItems().addAll(
                new CheckMenuItem("Self Destruct in T minus 50"),
                new CheckMenuItem("Turn off the coffee machine "),
                new CheckMenuItem("Run for your lives! "));

        alarmMenu.getItems().add(contingencyPlans);

        menuBar.getMenus().addAll(fileMenu, cameraMenu, alarmMenu);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Picklists examples with ObservableLists. It's enough to remove or add
     * items to these lists for refreshing UI
     *
     * @param primaryStage
     */
    public void observableList(Stage primaryStage) {
        primaryStage.setTitle("Hero Picker: Working with ObservableLists");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 250, Color.WHITE);

        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(50);
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridpane.getColumnConstraints().addAll(column1, column2, column3);

        // Candidates label
        Label candidatesLbl = new Label("Candidates");
        GridPane.setHalignment(candidatesLbl, HPos.CENTER);
        gridpane.add(candidatesLbl, 0, 0);

        // Heroes label
        Label heroesLbl = new Label("Heroes");
        gridpane.add(heroesLbl, 2, 0);
        GridPane.setHalignment(heroesLbl, HPos.CENTER);

        // Candidates
        final ObservableList<String> candidates
                = FXCollections.observableArrayList(
                        "Superman", "Spiderman", "Wolverine",
                        "Police", "Fire Rescue", "Soldiers",
                        "Dad & Mom", "Doctor", "Politician",
                        "Pastor", "Teacher"
                );
        final ListView<String> candidatesListView = new ListView<>(candidates);
        gridpane.add(candidatesListView, 0, 1);

        // heros
        final ObservableList<String> heroes = FXCollections.observableArrayList();
        final ListView<String> heroListView = new ListView<>(heroes);
        gridpane.add(heroListView, 2, 1);

        // select heroes
        Button sendRightButton = new Button(" > ");
        sendRightButton.setOnAction((ActionEvent event) -> {
            String potential = candidatesListView.getSelectionModel().getSelectedItem();
            if (potential != null) {
                candidatesListView.getSelectionModel().clearSelection();
                candidates.remove(potential);
                heroes.add(potential);
            }
        });

        // deselect heroes
        Button sendLeftButton = new Button(" < ");
        sendLeftButton.setOnAction((ActionEvent event) -> {
            String notHero = heroListView.getSelectionModel().getSelectedItem();
            if (notHero != null) {
                heroListView.getSelectionModel().clearSelection();
                heroes.remove(notHero);
                candidates.add(notHero);
            }
        });

        VBox vbox = new VBox(5);
        vbox.getChildren().addAll(sendRightButton, sendLeftButton);

        gridpane.add(vbox, 1, 1);
        root.setCenter(gridpane);

        GridPane.setVgrow(root, Priority.ALWAYS);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * TableView example
     *
     * @param primaryStage
     */
    public void tableView(Stage primaryStage) {
        primaryStage.setTitle("Bosses and Employees: Chapter 4 Working with Tables");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 250, Color.WHITE);

        // create a grid pane
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        root.setCenter(gridpane);

        // candidates label
        Label candidatesLbl = new Label("Boss");
        GridPane.setHalignment(candidatesLbl, HPos.CENTER);
        gridpane.add(candidatesLbl, 0, 0);

        // List of leaders
        ObservableList<Person> leaders = getPeople();
        final ListView<Person> leaderListView = new ListView<>(leaders);
        leaderListView.setPrefWidth(150);
        leaderListView.setMaxWidth(Double.MAX_VALUE);
        leaderListView.setPrefHeight(150);

        /* display first and last name with tooltip using alias. Because each item isn’t a string but a 
         * Person object, the ListView does not know how to render each row (cell) in the ListView control. 
         * To inform the ListView which properties to use from the Person object, the code simply creates 
         * a javafx.util.Callback generic object by specifying the ListView<Person> and a 
         * ListCell<Person> data type. */
        leaderListView.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {

            // code implementation to respond when a row item (ListCell) is selected
            @Override
            public ListCell<Person> call(ListView<Person> param) {
                final Label leadLbl = new Label();
                final Tooltip tooltip = new Tooltip();
                final ListCell<Person> cell = new ListCell<Person>() {
                    /* The job of the updateItem() method is to obtain each person’s information, 
                         * which then updates a Label control (leadLbl) */
                    @Override
                    public void updateItem(Person item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            leadLbl.setText(item.getAliasName());
                            setText(item.getFirstName() + " " + item.getLastName());
                            tooltip.setText(item.getAliasName());
                            setTooltip(tooltip);
                        }
                    }
                }; // ListCell
                return cell;

            }
        }); // setCellFactory

        gridpane.add(leaderListView, 0, 1);

        Label emplLbl = new Label("Employees");
        gridpane.add(emplLbl, 2, 0);
        GridPane.setHalignment(emplLbl, HPos.CENTER);

        final TableView<Person> employeeTableView = new TableView<>();
        employeeTableView.setPrefWidth(300);

        final ObservableList<Person> teamMembers = FXCollections.observableArrayList();
        employeeTableView.setItems(teamMembers);

        TableColumn<Person, String> aliasNameCol = new TableColumn<>("Alias");
        aliasNameCol.setEditable(true);
        aliasNameCol.setCellValueFactory(new PropertyValueFactory("aliasName"));

        aliasNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        firstNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        lastNameCol.setPrefWidth(employeeTableView.getPrefWidth() / 3);

        employeeTableView.getColumns().setAll(aliasNameCol, firstNameCol, lastNameCol);
        gridpane.add(employeeTableView, 2, 1);

        // selection listening
        leaderListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Person> observable, Person oldValue, Person newValue) -> {
            if (observable != null && observable.getValue() != null) {
                teamMembers.clear();
                teamMembers.addAll(observable.getValue().employeesProperty());
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * A private method getPeople() that returns an ObservableList containing
     * Person instances. A boss may contain zero to many employees
     *
     * @return
     */
    private ObservableList<Person> getPeople() {
        ObservableList<Person> people = FXCollections.<Person>observableArrayList();
        Person docX = new Person("Professor X", "Charles", "Xavier");
        docX.employeesProperty().add(new Person("Wolverine", "James", "Howlett"));
        docX.employeesProperty().add(new Person("Cyclops", "Scott", "Summers"));
        docX.employeesProperty().add(new Person("Storm", "Ororo", "Munroe"));

        Person magneto = new Person("Magneto", "Max", "Eisenhardt");
        magneto.employeesProperty().add(new Person("Juggernaut", "Cain", "Marko"));
        magneto.employeesProperty().add(new Person("Mystique", "Raven", "Darkhölme"));
        magneto.employeesProperty().add(new Person("Sabretooth", "Victor", "Creed"));

        Person biker = new Person("Mountain Biker", "Jonathan", "Gennick");
        biker.employeesProperty().add(new Person("Josh", "Joshua", "Juneau"));
        biker.employeesProperty().add(new Person("Freddy", "Freddy", "Guime"));
        biker.employeesProperty().add(new Person("Mark", "Mark", "Beaty"));
        biker.employeesProperty().add(new Person("John", "John", "O'Conner"));
        biker.employeesProperty().add(new Person("D-Man", "Carl", "Dea"));

        people.add(docX);
        people.add(magneto);
        people.add(biker);

        return people;
    }

    private static Task copyWorker;
    
    public void backgroundProcess(Stage primaryStage) {
        final int numFiles = 30;
        
        primaryStage.setTitle("BackgroundProcesses: Chapter 4 Background Processes");
        Group root = new Group();
        Scene scene = new Scene(root, 330, 120, Color.WHITE);

        BorderPane mainPane = new BorderPane();
        mainPane.layoutXProperty().bind(scene.widthProperty().subtract(mainPane.widthProperty()).divide(2));
        root.getChildren().add(mainPane);

        final Label label = new Label("Files Transfer:");
        final ProgressBar progressBar = new ProgressBar(0);
        final ProgressIndicator progressIndicator = new ProgressIndicator(0);

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(label, progressBar, progressIndicator);
        mainPane.setTop(hb);

        final Button startButton = new Button("Start");
        final Button cancelButton = new Button("Cancel");
        final TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefSize(200, 70);
        final HBox hb2 = new HBox();
        hb2.setSpacing(5);
        hb2.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(startButton, cancelButton, textArea);
        mainPane.setBottom(hb2);

        // wire up start button
        startButton.setOnAction((ActionEvent event) -> {
            startButton.setDisable(true);
            progressBar.setProgress(0);
            progressIndicator.setProgress(0);
            textArea.setText("");
            cancelButton.setDisable(false);
            copyWorker = createWorker(numFiles);

            // wire up progress bar
            progressBar.progressProperty().unbind();
            progressBar.progressProperty().bind(copyWorker.progressProperty());
            progressIndicator.progressProperty().unbind();
            progressIndicator.progressProperty().bind(copyWorker.progressProperty());

            // append to text area box
            copyWorker.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                textArea.appendText(newValue + "\n");
            });

            new Thread(copyWorker).start();
        });

        /* cancel button will kill worker and reset. 
         * Once a worker Task is cancelled it cannot be reused. That is why the 
         * Start button re-creates a new Task. If you want a more robust reusable solution, 
         * look at the javafx.concurrent.Service class. */
        cancelButton.setOnAction((ActionEvent event) -> {
            startButton.setDisable(false);
            cancelButton.setDisable(true);
            copyWorker.cancel(true);

            // reset
            progressBar.progressProperty().unbind();
            progressBar.setProgress(0);
            progressIndicator.progressProperty().unbind();
            progressIndicator.setProgress(0);
            textArea.appendText("File transfer was cancelled.");
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Task createWorker(final int numFiles) {
        return new Task() {

            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < numFiles; i++) {
                    long elapsedTime = System.currentTimeMillis();
                    copyFile("some file", "some dest file");
                    elapsedTime = System.currentTimeMillis() - elapsedTime;
                    String status = elapsedTime + " milliseconds";

                    // queue up status
                    updateMessage(status);
                    updateProgress(i + 1, numFiles); // (progress, max)
                }
                return true;
            }
        };
    }

    private void copyFile(String src, String dest) throws InterruptedException {
        // simulate a long time
        Random rnd = new Random(System.currentTimeMillis());
        long millis = rnd.nextInt(1000);
        Thread.sleep(millis);
    }
}
