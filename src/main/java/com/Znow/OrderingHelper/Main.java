package com.Znow.OrderingHelper;

import com.Znow.OrderingHelper.Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by User on 28.01.2017.
 */
public class Main extends Application {

    public static DBConnector connector;
    private Controller mController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        connector = new DBConnector();

        mController = new Controller();
        mController.startMainWin(primaryStage);
    }

    public DBConnector getConnector() {
        return connector;
    }
}
