package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage {
    ListView<HBox>products;

    ListView<HBox> productsbySearch(String search) throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("select * from product");
        while(res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label productID = new Label();
                Label name = new Label();
                Label price = new Label();
                Button buy = new Button();
                name.setMinWidth(50);
                price.setMinWidth(50);
                productID.setMinWidth(50);
                buy.setText("Buy");
                HBox productDetails = new HBox();

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Order");
                            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("please login first");
                            dialog.showAndWait();
                        } else {
                            System.out.println("you are logged in with " + HelloApplication.emailId);
                            Order order = new Order();
                            try {
                                order.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Buy button is clicked");
                    }
                });
                name.setText(res.getString("productName"));
                price.setText(res.getString("price"));
                productID.setText(res.getString("productID"));
                productDetails.getChildren().addAll(productID, name, price, buy);
                productList.add(productDetails);
            }
        }
        products.setItems(productList);
        return products;

    }
    ListView<HBox> products() throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("select * from product");
        while(res.next()){
            Label productID =new Label();
            Label name =new Label();
            Label price =new Label();
            Button buy =new Button();
            name.setMinWidth(50);
            price.setMinWidth(50);
            productID.setMinWidth(50);
            buy.setText("Buy");
            HBox productDetails = new HBox();

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailId.equals("")){
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Order");
                        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("please login first");
                        dialog.showAndWait();
                    }
                    else{
                       System.out.println("you are logged in with "+HelloApplication.emailId);
                       Order order=new Order();
                        try {
                            order.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("Buy button is clicked");
                }
            });
            name.setText(res.getString("productName"));
            price.setText(res.getString("price"));
            productID.setText(res.getString("productID"));
            productDetails.getChildren().addAll(productID,name,price,buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;

    }

}
