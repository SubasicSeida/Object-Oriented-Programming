package Week13;

interface Payment {
    void processPayment();
}


class CreditCardPayment implements Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing payment using Credit Card.");
    }
}


class PayPalPayment implements Payment {
    @Override
    public void processPayment() {
        System.out.println("Processing payment using PayPal.");
    }
}


interface PaymentFactory {
    Payment createPayment();
}


class CreditCardPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}


class PayPalPaymentFactory implements PaymentFactory {
    @Override
    public Payment createPayment() {
        return new PayPalPayment();
    }
}


class Main1 {
    public static void main(String[] args) {
        PaymentFactory creditCardFactory = new CreditCardPaymentFactory();
        Payment creditCardPayment = creditCardFactory.createPayment();
        creditCardPayment.processPayment();

        PaymentFactory payPalFactory = new PayPalPaymentFactory();
        Payment payPalPayment = payPalFactory.createPayment();
        payPalPayment.processPayment();
    }
}
