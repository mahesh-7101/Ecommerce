package com.example.ecommerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res =HelloApplication.connection.executeQuery("select max(OrderID) as OrderID from orders");
        int orderID=1;
        if(res.next()){
            orderID=res.getInt("orderID")+1;

        }
        Timestamp date=new Timestamp(Calendar.getInstance().getTime().getTime());
        String query =String.format("Insert into orders values('%s','%s','%s','%s')",orderID,productID,HelloApplication.emailId,date);
        int response=HelloApplication.connection.executeUpdate(query);
        if(response>0) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your Order is placed");
            dialog.showAndWait();
        }
        else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed, please check username/password and try again");
            dialog.showAndWait();
        }


    }
}
