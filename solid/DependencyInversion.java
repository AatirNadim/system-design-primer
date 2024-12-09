// high level modules should not depend on low level models, but rather both should depend on abstractions

interface Notifier {
    public void notify(String details);
}


class SMSNotifier implements Notifier {
    @Override
    public void notify(String details) {

    }
}

class EmailNotifier implements Notifier {
    @Override
    public void notify(String details) {

    }
}

class NotificationService {
    Notifier notifier;

    public NotificationService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void notify(String msg) {
        notifier.notify(msg);
    }

}