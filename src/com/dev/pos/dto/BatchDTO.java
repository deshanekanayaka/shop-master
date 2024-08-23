package com.dev.pos.dto;

public class BatchDTO {

    private String code;
    private String barcode;
    private int qtyOnHand;
    private double sellingPrice;
    private boolean isAvailable;
    private double showPrice;
    private double buyingPrice;


    public BatchDTO() {
    }

    public BatchDTO(String code, String barcode, int qtyOnHand, double sellingPrice, boolean isAvailable, double showPrice, double buyingPrice, int productCode) {
        this.setCode(code);
        this.setBarcode(barcode);
        this.setQtyOnHand(qtyOnHand);
        this.setSellingPrice(sellingPrice);
        this.setAvailable(isAvailable);
        this.setShowPrice(showPrice);
        this.setBuyingPrice(buyingPrice);
        this.setProductCode(productCode);
    }

    private int productCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }
}
