package com.dev.pos.controller;

import com.dev.pos.Enum.BoType;
import com.dev.pos.bo.BoFactory;
import com.dev.pos.bo.custom.BatchBo;
import com.dev.pos.dao.DaoFactory;
import com.dev.pos.dao.custom.BatchDao;
import com.dev.pos.dto.BatchDTO;
import com.dev.pos.util.security.qr.QRdataGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class NewBatchFormController {
    public AnchorPane context;
    public ImageView imgQR;
    public TextField txtQTy;
    public TextField txtBuyingPrice;
    public TextField txtShowPrice;
    public TextField txtSellingPrice;
    public RadioButton rdNo;
    public ToggleGroup discount;
    public RadioButton rdYes;
    public TextField txtProductCode;
    public TextArea txtDescription;
    public Button btnSave;

    String uniqueData = null;

    BatchBo batchBo = BoFactory.getInstance().getBo(BoType.BATCH);

    public void initialize() throws WriterException {
        setQrCode();

    }


    private void setQrCode() throws WriterException {

         uniqueData = QRdataGenerator.generate(30);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(
                qrCodeWriter.encode(
                        uniqueData,
                        BarcodeFormat.QR_CODE,198,196)

        );
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        imgQR.setImage(image);


    }

    public void setProductCode(int code, String description) {
        txtProductCode.setText(String.valueOf(code));
        txtDescription.setText(description);

    }

    private void loadBatch(BatchBo batchBo) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        try {
            if (btnSave.getText().equalsIgnoreCase("save batch")) {
                boolean isSaved = batchBo.saveBatch(
                        new BatchDTO(

                                "1",
                                txtProductCode.getText(),
                                Integer.parseInt(txtQTy.getText()),
                                Double.valueOf(txtSellingPrice.getText()),
                                rdNo.isSelected(),
                                Double.valueOf(txtShowPrice.getText()),
                                Double.valueOf(txtBuyingPrice.getText()),
                                Integer.valueOf(txtProductCode.getText())

                        )
                );
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Batch saved successfully").show();
                } else
                    new Alert(Alert.AlertType.INFORMATION, "Batch save failed").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }


}
