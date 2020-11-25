package pos.machine;

public class ItemDetail {
    private String barcode = "";
    private String name = "";
    private int price = 0;
    private long quantity = 0;
    private long subtotal = 0;

    public ItemDetail(String barcode, String name, int price, long quantity) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public long getSubtotal() {
        return subtotal;
    }

    public void generateSubtotal() {
        this.subtotal = this.price * this.quantity;
    }
}
