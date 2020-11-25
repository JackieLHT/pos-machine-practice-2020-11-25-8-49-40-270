package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ItemInfo>  itemInfos = ItemDataLoader.loadAllItemInfos();

        List<ItemDetail> allItemDetails = getAllItemDeail(barcodes,itemInfos);

        return renderReceipt(allItemDetails);
    }

    public List<ItemDetail> getAllItemDeail(List<String> barcodes,List<ItemInfo>  itemInfos) {
        List<ItemDetail> allItemDetail = new ArrayList<>();
        for(ItemInfo item:itemInfos) {
            if(barcodes.contains((item.getBarcode())))
            {
                long quantity = barcodes.stream().filter(barcode -> barcode.equals(item.getBarcode())).count();
                ItemDetail itemDetail = new ItemDetail(item.getBarcode(), item.getName(), item.getPrice(), quantity);
                itemDetail.generateSubtotal();
                allItemDetail.add(itemDetail);
            }
        }
        return allItemDetail;
    }

    private int calculateTotal(List<ItemDetail> allItemDetails) {
        int total = 0;
        for(ItemDetail item:allItemDetails) {
            total += item.getSubtotal();
        }
        return total;
    }

    private String renderReceipt(List<ItemDetail> allItemDetails) {
        String receipt = "***<store earning no money>Receipt***\n";
        for (ItemDetail item : allItemDetails) {
            receipt += String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", item.getName(), item.getQuantity(), item.getPrice(), item.getSubtotal());
        }
        receipt += String.format("----------------------\nTotal: %d (yuan)\n**********************", calculateTotal(allItemDetails));
        return receipt;
    }
}
