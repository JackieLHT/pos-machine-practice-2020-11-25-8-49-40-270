package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ItemInfo>  itemInfos = ItemDataLoader.loadAllItemInfos();

        List<ReceiptItem> allReceiptItems = getAllItemDetails(barcodes,itemInfos);

        Receipt receipt = new Receipt(allReceiptItems);

        return receipt.renderReceipt();
    }

    public List<ReceiptItem> getAllItemDetails(List<String> barcodes, List<ItemInfo>  itemInfos) {
        List<ReceiptItem> allReceiptItem = new ArrayList<>();
        for(ItemInfo item:itemInfos) {
            if(barcodes.contains((item.getBarcode())))
            {
                long quantity = barcodes.stream().filter(barcode -> barcode.equals(item.getBarcode())).count();
                ReceiptItem receiptItem = new ReceiptItem(item.getBarcode(), item.getName(), item.getPrice(), quantity);
                receiptItem.generateSubtotal();
                allReceiptItem.add(receiptItem);
            }
        }
        return allReceiptItem;
    }
}
