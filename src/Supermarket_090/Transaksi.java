package Supermarket_090;

import java.util.ArrayList;
import java.util.List;

public class Transaksi extends Item{
    private Item item;
    private int qty;
    private List listTransaksi = new ArrayList();
    public Transaksi(){
    }
    public Transaksi(Item item, int qty){
        this.item = item;
        this.qty = qty;
    }
    public void insertTransaksi(Item item, int qty){
        Transaksi transaksi = new Transaksi(item,qty);
        listTransaksi.add(transaksi);
    }
    public List allData(){
        return listTransaksi;
    }
    public int getQty(){
        return qty;
    }
    public void setQty(int qty){
        this.qty = qty;
    }
    public Item getItem(){
        return item;
    }
    public boolean cariTransaksi(List<Transaksi> transaksi, String id, int banyak){
        for (Transaksi tr : transaksi) {
            if (tr.getItem().getId().equals(id)) {
                tr.setQty(tr.getQty() + banyak);
                return true;
            }
        }
        return false;
    }
    public double total(int qty){
        return  qty * item.getPrice();
    }
}
