package pos.machine;

import java.util.List;
import java.util.stream.Collectors;

public class Receipt {
    List<ReceiptItem> allReceiptItem;
    static final String receiptHeader = "***<store earning no money>Receipt***\n";
    static final String receiptLineBreak = "----------------------\n";
    static final String receiptFooter = "**********************";

    public Receipt(List<ReceiptItem> allReceiptItem) {
        this.allReceiptItem = allReceiptItem;
    }

    private int calculateTotal() {
        int total = 0;
        for (ReceiptItem receiptItem : allReceiptItem)
        {
            total += receiptItem.getSubtotal();
        }
        return total;
    }

    public String renderReceipt() {
        String receiptBody = allReceiptItem.stream().map(reciptItem -> String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)", reciptItem.getName(), reciptItem.getQuantity(), reciptItem.getPrice(), reciptItem.getSubtotal())).collect(Collectors.joining("\n")) + "\n";
        String receiptTotal = String.format("Total: %d (yuan)\n", calculateTotal());
        return receiptHeader + receiptBody + receiptLineBreak + receiptTotal + receiptFooter;
    }
}
