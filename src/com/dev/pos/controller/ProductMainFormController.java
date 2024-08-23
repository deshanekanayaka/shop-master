package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.ProductBo;
import com.dev.pos.dao.DatabaseAccessCode;
import com.dev.pos.dto.ProductDTO;
import com.dev.pos.dto.tm.ProductTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMainFormController {

    public AnchorPane context;
    public TextField txtProductCode;
    public TextArea txtProductDescription;
    public Button btnSave;
    public TextField txtSearch;

    public TableView<ProductTm> tblProduct;
    public TableColumn<ProductTm,Integer> colProductId;
    public TableColumn<ProductTm,String> colDescription;
    public TableColumn<ProductTm,Button> colShowMore;
    public TableColumn<ProductTm,Button> colDelete;

    public TextField txtSelectedProductCode;
    public TextArea txtSelectedDescription;

    public TableView tblProductMain;
    public TableColumn colNo;
    public TableColumn colQty;
    public TableColumn colBuyingPrice;
    public TableColumn colDiscount;
    public TableColumn colShowPrice;
    public TableColumn colSellingPrice;
    public TableColumn colMainDelete;
    public Button newBatchBtn;

    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    String searchText = "";

    public void initialize() throws SQLException, ClassNotFoundException {

        loadProductId();

        colProductId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colShowMore.setCellValueFactory(new PropertyValueFactory<>("showMoreBtn"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));

        loadAllProducts(searchText);

        //Setting data to the right side selected elements, save btn to update
        tblProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
            }
        });

    }

    private void setData(ProductTm newValue) {
        newBatchBtn.setDisable(false);
        btnSave.setText("Update Product");

        txtProductCode.setText(String.valueOf(newValue.getCode()));
        txtSelectedProductCode.setText(String.valueOf(newValue.getCode()));

        txtProductDescription.setText(newValue.getDescription());
        txtSelectedDescription.setText(newValue.getDescription());
    }

    private void loadProductId() {
        try {
            txtProductCode.setText(String.valueOf(productBo.getLastProductId()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnBackToHome(ActionEvent actionEvent) throws IOException {

        setUI("DashboardForm");
    }

    public void btnAddNewProductOnAction(ActionEvent actionEvent) {

    }
    //Save Product
    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            if(btnSave.getText().equalsIgnoreCase("save product")) {

                boolean isSaved = productBo.saveProduct(new ProductDTO(
                        Integer.valueOf(txtProductCode.getText()),
                        txtProductDescription.getText()
                ));
                if(isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Product saved successfully").show();
                    loadAllProducts(searchText);
                    loadProductId();
                    clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                }

            } //Update Product
            else {
                    boolean isUpdated = productBo.updateProduct(new ProductDTO(
                            Integer.valueOf(txtProductCode.getText()),
                            txtProductDescription.getText()
                    ));

                    if(isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Product updated successfully").show();
                        loadAllProducts(searchText);
                        loadProductId();
                        btnSave.setText("Save Product");
                        clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                    }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnAddNewBatchOnAction(ActionEvent actionEvent) throws IOException {


        //txtProductCode length > 0 means there's at least one
        if(txtSelectedProductCode.getText().trim().length() > 0) {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/NewBatchForm.fxml"));
            Parent parent = fxmlLoader.load();
            NewBatchFormController controller = fxmlLoader.getController();
            controller.setProductCode(
                    Integer.valueOf(txtSelectedProductCode.getText()),
                    txtSelectedDescription.getText()

            );

            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select a valid prduct").show();
        }


    }

    private void loadAllProducts(String searchText) throws SQLException, ClassNotFoundException {

        ObservableList<ProductTm> oblist = FXCollections.observableArrayList();
        try {
            for(ProductDTO p: productBo.findAllProducts()) {
                Button showMore = new Button("Show More");
                Button deleteBtn = new Button("Delete Product");
                ProductTm tm = new ProductTm(
                        p.getCode(),
                        p.getDescription(),
                        showMore,
                        deleteBtn
                );

                oblist.add(tm);
            }
            //Add to the table
            tblProduct.setItems(oblist);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void clear() {

        txtProductDescription.clear();
        txtSelectedProductCode.clear();
        txtSelectedDescription.clear();
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
        stage.centerOnScreen();
    }
}
