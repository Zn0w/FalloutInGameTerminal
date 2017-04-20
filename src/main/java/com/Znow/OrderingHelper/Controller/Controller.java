package com.Znow.OrderingHelper.Controller;

import com.Znow.OrderingHelper.Model.ActiveOrder;
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
import static com.Znow.OrderingHelper.Model.Model.orders;

/**
 * Created by User on 28.01.2017.
 */
public class Controller {

    private Stage primaryStage;
	
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

    public void init() {
        cModel.loadGoods();
        cModel.loadActiveOrders();
    }
    
    public void runStartWin(Stage stage) {
    	cView.drawStartView(stage);
    	
    	primaryStage = stage;
    }

    public void startMainWin() {
        cView.drawClientView(primaryStage);
    }

    public void startAdminWin() {
        cView.drawSellerView(primaryStage);
    }

    // Action handling for startup window
    
    public void goAsCustomer() {
    	System.out.println("Customer");
    }
    
    public void goAsSeller() {
    	System.out.println("Seller");
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

    @FXML
    public void openSellerMode() {
        startAdminWin();
    }

    // Action handling for seller mode

    public void openGoodManager() {
        Stage stage = new Stage();

        VBox root = new VBox();
        ScrollPane goodListScroll = new ScrollPane();

        VBox goodListPane = new VBox();
        goodListScroll.setContent(goodListPane);

        StackPane butPane = new StackPane();

        for (int i = 0; i < Model.goods.size(); i++) {
            Good good = goods.get(i);

            Button goodBut = new Button(good.getName() + "   $" + good.getPrice());
            goodBut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    goodSelected(good);
                }
            });

            goodListPane.getChildren().add(goodBut);
        }

        Button okBut = new Button("OK");
        okBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        butPane.getChildren().add(okBut);
        root.getChildren().addAll(goodListScroll, butPane);

        Scene scene = new Scene(root, 300 ,300);

        stage.setScene(scene);
        stage.setTitle("Good Manager");

        stage.show();
    }

    public void goodSelected(Good good) {
        Stage goodStage = new Stage();

        VBox root = new VBox();

        Label lblName = new Label("Name: " + good.getName());
        Label lblPrice = new Label("Price: $" + good.getPrice());

        TextField txtSetName = new TextField("Put here new name if you want to change the name");
        TextField txtSetPrice = new TextField("Put here new price if you want to change the name");

        HBox optionsPane = new HBox();

        Button updateBut = new Button("Update information");
        updateBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txtSetName.getText() != "Put here new name if you want to change the name" || txtSetName.getText() != null)
                    cModel.updateGood(good, txtSetName.getText());

                if (txtSetPrice.getText() != "Put here new price if you want to change the name" || txtSetPrice.getText() != null)
                    cModel.updateGood(good, Integer.parseInt(txtSetPrice.getText()));
            }
        });

        Button removeBut = new Button("Remove good");
        removeBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cModel.deleteGood(good);
            }
        });

        optionsPane.getChildren().addAll(updateBut, removeBut);
        root.getChildren().addAll(lblName, lblPrice, txtSetName, txtSetPrice, optionsPane);

        Scene contentScene = new Scene(root, 250, 400);

        goodStage.setScene(contentScene);
        goodStage.setTitle("Good information");

        goodStage.show();
    }

    public void openActiveOrdersMenu() {
        Stage stage = new Stage();

        VBox root = new VBox();
        ScrollPane ordScroll = new ScrollPane();

        VBox ordList = new VBox();
        ordScroll.setContent(ordList);

        for (int i = 0; i < Model.orders.size(); i++) {
            ActiveOrder order = orders.get(i);

            Button butOrd = new Button(order.getCustName() + "|" + order.getOrderContent()) ;
            butOrd.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    opentActiveOrderWindow(order);
                    stage.close();
                }
            });

            ordList.getChildren().add(butOrd);
        }

        Button butOk = new Button("OK");
        butOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        root.getChildren().addAll(ordList, butOk);

        Scene scene = new Scene(root);

        stage.setTitle("Active Orders");
        stage.setScene(scene);

        stage.show();
    }

    public void opentActiveOrderWindow(ActiveOrder order) {
        Stage stage = new Stage();

        VBox root = new VBox();

        Label name = new Label("Customer name: " + order.getCustName());
        Label mail = new Label("Customer mail: " + order.getCustMail());
        Label content = new Label("Order content: " + order.getOrderContent());

        HBox options = new HBox();

        Button butDone = new Button("Done");
        butDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveInHistory("done");
                stage.close();
            }
        });

        Button butCancel = new Button("Cancel Order");
        butCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveInHistory("cancelled");
                stage.close();
            }
        });

        Button butClose = new Button("Close");
        butClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        options.getChildren().addAll(butDone, butCancel, butClose);

        root.getChildren().addAll(name, mail, content, options);

        Scene scene = new Scene(root);

        stage.setTitle("Order Description");
        stage.setScene(scene);

        stage.show();
    }

    // History
    public void saveInHistory(String status) {

    }
}





















