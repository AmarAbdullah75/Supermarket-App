package Supermarket_090;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupermarketApp {
    Scanner get = new Scanner(System.in);
    DataItem data = new DataItem();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    LocalDate hariIni = LocalDate.now();
    boolean status;
    private Item transaksi;
    private int banyak;

    public static void main(String[] args) {
        SupermarketApp supermarket = new SupermarketApp();
        supermarket.app();
    }
    public void app() {
        System.out.println("    Supermarket App");
        System.out.println("+======================+");
        System.out.println(" 1. Menambahkan Item");
        System.out.println(" 2. Menampilkan Item");
        System.out.println(" 3. Pembelian Item");
        System.out.println(" 4. Keluar");
        System.out.println("+======================+");
        System.out.print("Pilihan: ");
        short menu = get.nextShort();
        switch (menu) {
            case 1:get.nextLine();CreateItem();break;
            case 2: get.nextLine();ReadItem();break;
            case 3: get.nextLine();BuyItem();break;
            case 4: System.out.println("Terimakasih karena telah mempercayai kami!!");break;
            default: System.out.println("Pilihan menu tidak tersedia!!");
        }
    }

    public void CreateItem() {
        System.out.println("+======================+");
        System.out.println("        Add Item");
        System.out.println("+======================+");
        Item item = new Item();
        System.out.print("ID Item                 :");
        item.setId(get.nextLine());
        System.out.print("Name                    :");
        item.setNama(get.nextLine());
        System.out.print("Type                    :");
        item.setType(get.nextLine());
        System.out.print("Exp Date (dd-MM-yyyy)   :");
        if (item.getType().equals("Vegetables") || item.getType().equals("vegetables") || item.getType().equals("Fruit") || item.getType().equals("fruit")) {
            try {
                String tgl = format.format(Date.valueOf(hariIni.plusDays(4)));
                item.setDate(format.parse(tgl));
            }catch (ParseException ex){
                System.out.println("Format tanggal yang benar (dd/MM/yyyy)");
            }
            System.out.println(format.format(item.getDate()));
        } else {
            status = false;
            while (!status) {
                String expDate = get.nextLine();
                try {
                    item.setDate(format.parse(expDate));
                    status = true;
                } catch (ParseException parse) {
                    System.out.println("Format tanggal yang benar (dd/MM/yyyy)");
                }
            }
        }
        System.out.print("Stock                   :");
        item.setStock(get.nextInt());
        System.out.print("Price                   :");
        item.setPrice(get.nextDouble());
        data.insertData(item.getId(), item.getNama(), item.getType(), item.getStock(), item.getDate(), item.getPrice());
        System.out.println("+========================+");
        System.out.println(" Penambahan Item Berhasil!");
        System.out.println("+========================+");
        System.out.print(" Tambahkan Item Lagi?[Y/T] : ");
        char lanjut = get.next().charAt(0);
        get.nextLine();
        if (lanjut == 'y' || lanjut == 'Y') {
            CreateItem();
        } else if (lanjut == 't' || lanjut == 'T') {
            app();
        }
    }

    public void ReadItem() {
        System.out.println("+========================+");
        System.out.println("      Display Item");
        System.out.println("+========================+");
        List listItem =



                new ArrayList();
        listItem = data.allData();
        System.out.println("================================================================================================");
        System.out.println("No     Id        Nama        Tipe          Stock          Tanggal Kadaluarsa         Price");
        System.out.println("================================================================================================");
        for (int i = 0; i < listItem.size(); i++) {
            Item item = (Item) listItem.get(i);
            System.out.printf("%-6d %-9s %-10s %-15s %-10d %-15s %.1f\n",
                    i + 1, item.getId(), item.getNama(), item.getType(), item.getStock(), format.format(item.getDate()), item.getPrice());
        }
        System.out.print(" Tetap lihat data item?[Y/T] : ");
        char lanjut = get.next().charAt(0);
        get.nextLine();
        if (lanjut == 'y' || lanjut == 'Y') {
            ReadItem();
        } else if (lanjut == 't' || lanjut == 'T') {
            app();
        }
    }

    public void BuyItem() {
        System.out.println("+========================+");
        System.out.println("        Buy Item");
        System.out.println("+========================+");
        List listItem = new ArrayList();
        List<Transaksi> transaksi = new ArrayList<>();
        listItem = data.allData();
        DataItem dataItem = new DataItem();
        Item item = new Item();
        String id = "";
        System.out.print(" Banyak item yang ingin dibeli   :");
        short jumlah = get.nextShort();
        get.nextLine();
        for (int i = 0; i < jumlah; i++) {
            boolean status = false;
            Transaksi itemTrs = new Transaksi();
            while (!status) {
                while (!status) {
                    System.out.print("ID Item    :");
                    id = get.nextLine();
                    item = dataItem.search(listItem, id);
                    if (item != null) {
                        status = true;
                    } else {
                        System.out.println("Item tidak ditemukan! Silakan pilih item lain...");
                    }
                }
                status = false;
                System.out.print("Qty        :");
                int banyak = get.nextInt();
                get.nextLine();
                if (banyak > item.getStock()) {
                    System.out.println("Stok item tidak mencukupi! Silakan pilih item lain...");
                    banyak = 0;
                } else {
                    status = true;
                    item.setStock(item.getStock() - banyak);
                }
                boolean ada;
                ada = itemTrs.cariTransaksi(transaksi, id, banyak);

                if (!ada) {
                    transaksi.add(new Transaksi(item, banyak));
                }
            }
            System.out.println(" Transaksi berhaasil!");
            System.out.println("============================================================================");
            System.out.println("No     Id Item         Title                  Qty              Price");
            System.out.println("============================================================================");
            double total = 0;
            for (int j = 0; j < transaksi.size(); j++) {
                itemTrs = transaksi.get(j);
                System.out.printf("%-5d  %-10s    %-10s    %-10d   %.1f\n",
                        j + 1, itemTrs.getItem().getId(), itemTrs.getItem().getNama(), itemTrs.getQty(), itemTrs.getItem().getPrice());
                total += itemTrs.total(itemTrs.getQty());
            }
            System.out.println("============================================================================");
            System.out.printf("Total                                                               Rp.%.1f", total);
            System.out.println("\n============================================================================");
        }
        System.out.print(" Ingin Melakukan Transaksi Lagi?[Y/T] : ");
        char lanjut = get.next().charAt(0);
        get.nextLine();
        if (lanjut == 'y' || lanjut == 'Y') {
            BuyItem();
        } else if (lanjut == 't' || lanjut == 'T') {
            app();
        }
    }
}
