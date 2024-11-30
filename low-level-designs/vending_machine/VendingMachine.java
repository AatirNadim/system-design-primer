// vending machine has rows, and columns within each row, each [row][column] is a list of same stock count
// item list, each item list, has its position --> [row][column], item details and the count
// we just assume, each array at 2d position carries the same product


package vending_machine;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Item {
    double price;
    String title;
    String company;
    String uid;

    public Item(double price, String title, String company, String uid) {
        this.price = price;
        this.title = title;
        this.company = company;
        this.uid = uid;
    }
}

class ItemMapper {
    int level;
    int column;
    Item itemDetails;
    int count;

    public ItemMapper(int level, int column, Item itemDetails, int count) {
        this.level = level;
        this.column = column;
        this.itemDetails = itemDetails;
        this.count = count;
    }
}



// this is involved in the payment transactions and returning failure/success response, leading to possible refund
interface PaymentService {
    void pay(double amount);
}

class CardPayment implements PaymentService {

    String cardNo;
    int cvv;
    Date expireDate;

    public CardPayment(String cardNo, int cvv, Date expireDate) {
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expireDate = expireDate;
    }

    @Override
    public void pay(double amount) {
//        the payment utility function with the card details
    }
}

class DigitalPayment implements PaymentService {
    static String apiKey;
    @Override
    public void pay(double amount) {
//        make the necessary transactions
    }
}

class CashPayment implements PaymentService {

    double initAmount;
    CashPayment(double initAmount) {
        this.initAmount = initAmount;
    }

    @Override
    public void pay(double amount) {
//        instruct the return mechanism to return (initAmount - amount).
    }
}

class PaymentProcessingException extends Exception {
    PaymentProcessingException(String msg) {
        super("PaymentProcessingException: " + msg);
    }
}

class VendingMachine {
    List<ItemMapper> stocks;
    PaymentService paymentFront;

    public VendingMachine(List<ItemMapper> stocks, PaymentService paymentFront) {
        this.stocks = stocks;
        this.paymentFront = paymentFront;
    }

     void buyItem(Item item, int count) {
        try {
//            check if the itemCount is possible, else return item count not available exception
            paymentFront.pay(item.price * count);
//            get the itemNode, return the count, update the itemNode count
//            disburseItem(item.uid, count)
        } catch (PaymentProcessingException e) {
            System.out.println("Error in processing payment\nDetails: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

//    this is going to be manual
//    possible to maintain the consistency, for every stock item added manually, they log it digitally with this function
    void addItem(String itemId, int count) {
//        get the stockNode, update the count, to the stock length limit
        // for instance, if the max count is 15, the item count is already 8, so adding any item count greater than 7, throws exception

    }


}