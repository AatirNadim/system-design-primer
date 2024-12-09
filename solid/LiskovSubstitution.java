// every child class should be substitutable to its parent class

import java.util.Date;

interface PaymentService2 {
    void pay(double val);
}

class PaypalService2 implements PaymentService2 {
    @Override
    public void pay(double val) {
        System.out.println("value to be payed : " + val);
    }
}

class PayUService2 implements PaymentService2 {
    @Override
    public void pay(double val) {
        System.out.println("value to be payed : " + val);
    }
}

class Order {
    String itemDetails;
    Date buyDate;
    PaymentService2 paymentService;

    public Order(String itemDetails, Date buyDate, PaymentService2 paymentService) {
        this.itemDetails = itemDetails;
        this.buyDate = buyDate;
        this.paymentService = paymentService;
    }
}