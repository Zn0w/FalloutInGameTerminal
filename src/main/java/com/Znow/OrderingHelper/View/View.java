package com.Znow.OrderingHelper.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by User on 28.01.2017.
 */
public class View {

    //private Stage mainStage;

    public void drawMainView(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cashierModeView.fxml"));

            Scene contentScene = new Scene(root);

            stage.setTitle("OrderingHelper by Znow");
            stage.setScene(contentScene);

        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.show();
    }
}
