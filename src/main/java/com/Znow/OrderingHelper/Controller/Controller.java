package com.Znow.OrderingHelper.Controller;

import com.Znow.OrderingHelper.Model.Good;
import com.Znow.OrderingHelper.Model.Model;
import com.Znow.OrderingHelper.Order;
import com.Znow.OrderingHelper.View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.Znow.OrderingHelper.Model.Model.goods;

/**
 * Created by User on 28.01.2017.
 */
public class Controller {

    private Model cModel;
    private View cView;

    @FXML
    private Button butNewOrder;

    @FXML
    private VBox itemsPane;

    @FXML
    private HBox optionsPane;

    private Order currentOrder;

    private ScrollPane scrollItemsList;
    private VBox itemsList;

    public Controller() {
        cModel = new Model();
        cView = new View();
    }

    public void startMainWin(Stage mainStage) {
        cView.drawMainView(mainStage);
    }

    public View getView() {
        return cView;
    }

    // Action handling

    @FXML
    public void createNewOrder() {
        if (currentOrder == null) {
            currentOrder = new Order();
        }

        if (currentOrder.isRecentlyCreated()) {
            Button butAddItem = new Button("Add item");
            butAddItem.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    openGoodsList();
                }
            });

            optionsPane.getChildren().add(butAddItem);
        }
        else {
            Stage stage = new Stage();
            VBox root = new VBox();
            root.setPadding(new Insets(25, 20, 25, 20));
            root.setSpacing(7);

            Label lblMessage = new Label("You've created active order already!");
            Button butOk = new Button("OK");
            butOk.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });

            root.getChildren().addAll(lblMessage, butOk);

            Scene contentScene = new Scene(root, 250, 100);
            stage.setScene(contentScene);
            stage.show();
        }

        currentOrder.setRecentlyCreated(false);
    }

    /**
     * Call when the order is completed
     */
    public void applyOrder() {

    }

    /**
     * Call If user wants to cancel the order
     */
    public void cancelOrder() {

    }

    /**
     * Call when user chooses item to add in order
     */
    public void addGoodInOrder() {

    }

    /**
     * Call when user press "Add" button and opens goods list
     */
    public void openGoodsList() {
        Stage stage = new Stage();

        VBox mainPane = new VBox();

        scrollItemsList = new ScrollPane();
        itemsList = new VBox();
        scrollItemsList.setContent(itemsList);

        HBox options = new HBox();

        Button button = new Button("Cancel");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        options.getChildren().add(button);

        for (int i = 0; i < goods.size(); i++) {
            Good good = goods.get(i);
            Button but = new Button(good.getName() + "($" + good.getPrice() + ")");
            itemsList.getChildren().add(but);
            System.out.println(good.getName() + "($" + good.getPrice() + ")");
        }

        mainPane.getChildren().addAll(scrollItemsList, options);

        Scene selectScene = new Scene(mainPane, 200, 300);
        stage.setScene(selectScene);
        stage.show();
    }
}
