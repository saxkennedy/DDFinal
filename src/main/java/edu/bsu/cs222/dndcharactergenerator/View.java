package edu.bsu.cs222.dndcharactergenerator;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.graalvm.compiler.phases.common.NodeCounterPhase;

public class View extends Application {

    public static void main(String[] args) {launch((args));}
    @Override
    public void start(Stage stage){
        TextField characterName = new TextField();
        Button go = new Button();
        go.setOnAction(actionEvent->{

            //Do the thing


        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(characterName,go);
        stage.setTitle("Buttcheeks Ahoy");
        Scene scene0=new Scene(vbox,400,400);
        stage.setScene(scene0);
        stage.show();

    }

}
