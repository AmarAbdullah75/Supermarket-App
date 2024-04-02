package Supermarket_090;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataItem {
    private List listItem = new ArrayList();

    public DataItem(){
        this.listItem = new ArrayList();
    }
    public List allData(){














        return listItem;
    }
    public void insertData(String id, String nama, String type, int stock, Date date, double price){
        Item item = new Item(id, nama,type,stock,date,price);
        listItem.add(item);
    }
    public void insertData(String id, String nama, int stock, double price){
        Item item = new Item(id, nama,stock,price);
    }
    public Item search(List<Item> items, String id){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)){
                return items.get(i);
            }
        }
        return null;
    }
}
