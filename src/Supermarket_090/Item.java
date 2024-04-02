package Supermarket_090;

import java.util.Date;
public class Item {
    private String id;
    private String nama;
    private String type;
    private int stock;
    private Date date;
    private double price;

    public Item() {
    }
    public Item(String id, String nama, String type, int stock, Date date, double price) {
        this.id = id;
        this.nama = nama;
        this.type = type;
        this.stock = stock;
        this.date = date;
        this.price = price;
    }
    public Item(String id, String nama, int stock,  double price) {
        this.id = id;
        this.nama = nama;
        this.stock = stock;
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
