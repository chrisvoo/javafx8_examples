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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
    public void ch1_HelloWorld(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
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
}
