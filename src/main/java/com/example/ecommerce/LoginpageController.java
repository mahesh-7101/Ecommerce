package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginpageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query = String.format("Select * from user where emailID = '%s' AND pass = '%s'",email.getText(),password.getText());
        ResultSet res = HelloApplication.connection.executeQuery(query);
        if(res.next()){
            HelloApplication.emailId=res.getString("emailID");
            String userType = res.getString("usertype");
            if(userType.equals("Seller")){
                AnchorPane sellerpage = FXMLLoader.load((getClass().getResource("SellerPage.fxml")));
                HelloApplication.root.getChildren().add(sellerpage);
            }
            else
            {
                System.out.println("we are logged in as Buyer");
                ProductPage productPage=new ProductPage();
                Header header =new Header();
                AnchorPane productPane=new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutX(150);
                productPane.setLayoutY(150);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);
            }
            System.out.println("The user is present in the User Table");
        }
        else{
            System.out.println("The user is not present");
        }
    }
}
