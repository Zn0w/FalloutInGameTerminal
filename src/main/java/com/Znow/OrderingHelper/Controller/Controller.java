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
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
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

    @FXML
    private HBox pricePane;

    private Order currentOrder;

    private Label lblPrice;

    private ScrollPane scrollItemsList;
    private VBox itemsList;

    public Controller() {
        cModel = new Model();
        cView = new View();
    }

    public void goodInit() {
        cModel.loadGoods();
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

            Button butApplyOrder = new Button("Apply order");
            butApplyOrder.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    applyOrder();
                }
            });

            Button butCancelOrder = new Button("Cancel order");
            butCancelOrder.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    cancelOrder();
                }
            });

            optionsPane.getChildren().addAll(butAddItem, butApplyOrder, butCancelOrder);

            lblPrice = new Label("Total price:   $" + currentOrder.getTotalPrice());

            pricePane.getChildren().add(lblPrice);
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

        Button button = new Button("Done");
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
            but.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectGood(good);
                }
            });
            itemsList.getChildren().add(but);
        }

        mainPane.getChildren().addAll(scrollItemsList, options);

        Scene selectScene = new Scene(mainPane, 200, 300);
        stage.setScene(selectScene);
        stage.show();
    }

    public void selectGood(Good good) {
        Stage stage = new Stage();
        stage.setTitle(good.getName() + " - $" + good.getPrice());

        VBox mainPane = new VBox();

        HBox enterPane = new HBox();

        Label lbl = new Label("Enter amount: ");
        TextField txt = new TextField();

        enterPane.getChildren().addAll(lbl, txt);

        HBox buttonsPane = new HBox();

        Button acceptBut = new Button("OK");
        acceptBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int amount = Integer.parseInt(txt.getText());
                currentOrder.addGood(good, amount);

                Button goodBut = new Button(good.getName() + "  $" + good.getPrice() + "  x" + amount);

                // Updating orderedItems and price
                itemsPane.getChildren().add(goodBut);
                lblPrice.setText("Total price:   $" + currentOrder.getTotalPrice());

                stage.close();
            }
        });
        Button cancelBut = new Button("Cancel");
        cancelBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        buttonsPane.getChildren().addAll(acceptBut, cancelBut);

        mainPane.getChildren().addAll(enterPane, buttonsPane);

        Scene contentScene = new Scene(mainPane);

        stage.setScene(contentScene);
        stage.show();
    }
}
