

interface PaymentService {
    void pay(double val);
}

class PaypalService implements PaymentService {
    @Override
    public void pay(double val) {
        System.out.println("value to be payed : " + val);
    }
}

class PayUService implements PaymentService {
    @Override
    public void pay(double val) {
        System.out.println("value to be payed : " + val);
    }
}