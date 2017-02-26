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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
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

    // Action handling for cashier mode

    @FXML
    public void createNewOrder() {
        if (currentOrder == null) {
            currentOrder = new Order();
        }

        if (currentOrder.isRecentlyCreated()) {
            itemsPane.getChildren().clear();

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

    @FXML
    public void showGuide() {
        Label lblInfo = new Label("Welcome to the OrderingHelper!\nNow you can make an order!\n(Some information will be here)");

        itemsPane.getChildren().add(lblInfo);
    }

    /**
     * Call when the order is completed
     */
    public void applyOrder() {
        optionsPane.getChildren().clear();
        pricePane.getChildren().clear();
        itemsPane.getChildren().clear();

        // Getting contact data
        Stage stage = new Stage();

        VBox root = new VBox();

        TextField txtName = new TextField("Put here your name");
        TextField txtMail = new TextField("Put here your e-mail");

        Button okayBut = new Button("OK");
        okayBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentOrder.accept(txtName.getText(), txtMail.getText());
                currentOrder = null;

                stage.close();
            }
        });

        root.getChildren().addAll(txtName, txtMail, okayBut);

        Scene scene = new Scene(root, 150, 300);

        stage.setScene(scene);
        stage.setTitle("Contact Information");
        stage.show();
    }

    /**
     * Call If user wants to cancel the order
     */
    public void cancelOrder() {

        currentOrder.cancel();

        currentOrder = null;
        optionsPane.getChildren().clear();
        pricePane.getChildren().clear();
        itemsPane.getChildren().clear();
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
                good.setOrderedAmount(amount);
                currentOrder.addGood(good, amount);

                Button goodBut = new Button(good.getName() + "  $" + good.getPrice() + "  x" + amount);
                goodBut.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        selectOrderedGood(good, goodBut);
                    }
                });

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

    public void selectOrderedGood(Good good, Button goodButton) {
        Stage stage = new Stage();

        VBox root = new VBox();

        Label qLbl = new Label("Another amount?");
        TextField aSet = new TextField();
        HBox optPanel = new HBox();

        Button setAmount = new Button("Set new amount");
        setAmount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int amount = Integer.parseInt(aSet.getText());
                currentOrder.updateAmount(good, amount);

                // Updating orderedItems and price
                goodButton.setText(good.getName() + "  $" + good.getPrice() + "  x" + amount);
                lblPrice.setText("Total price:   $" + currentOrder.getTotalPrice());

                stage.close();
            }
        });

        Button removeGood = new Button("Remove good from the order");
        removeGood.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentOrder.removeGood(good);

                // Updating orderedItems and price
                itemsPane.getChildren().remove(goodButton);
                lblPrice.setText("Total price:   $" + currentOrder.getTotalPrice());

                stage.close();
            }
        });

        optPanel.getChildren().addAll(setAmount, removeGood);

        root.getChildren().addAll(qLbl, aSet, optPanel);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Good change");
        stage.show();
    }

    // Action handling for admin mode
}
