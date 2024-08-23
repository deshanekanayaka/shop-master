package com.dev.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class DashboardFormController {
    public AnchorPane context;

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to Logout...?",ButtonType.NO,ButtonType.YES);

        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get().equals(ButtonType.YES)){
            setUI("LoginForm");
        }
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUI("CustomerForm");
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ProductMainForm");
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }
    public void btnOrderDetailsOnAction(ActionEvent actionEvent) {
    }

    public void btnIncomeReportOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }


}
