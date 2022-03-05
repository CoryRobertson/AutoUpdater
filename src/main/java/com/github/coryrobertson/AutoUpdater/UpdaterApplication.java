package com.github.coryrobertson.AutoUpdater;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;


public class UpdaterApplication extends Application
{

    final String FXML_FILENAME = "AutoUpdater.fxml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException
    {

        Parent root;
        root = FXMLLoader.load(getClass().getClassLoader().getResource(FXML_FILENAME));
        stage.setTitle("AutoUpdater");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


}
