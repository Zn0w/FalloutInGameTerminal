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
	
	public void runCustomerView(Stage stage) {
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("/cashierModeView.fxml"));
            
            stage.setTitle("OrderingHelper by Zn0w");
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	stage.show();
    }

    public void runSellerView() {
    	Stage sellerStage = new Stage();
    	
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("/adminModeView.fxml"));
            
            sellerStage.setTitle("OrderingHelper by Zn0w (seller mode)");
            sellerStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	sellerStage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
    
}
