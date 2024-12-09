// implement only what is necessary to achieve the functionality


interface Printable {
    void print(String data);
}

interface Faxable {
    void fax(String data);
}

interface Scannable {
    void scan(String data);
}


class BasicPrinter implements Printable {
    @Override
    public void print(String data) {
        // print details
    }
}

class AdvancedPrinter implements Printable, Faxable, Scannable {
    @Override
    public void print(String data) {
        // print
    }

    @Override
    public void fax(String data) {
        // fax
    }

    @Override
    public void scan(String data) {
        // scan
    }
}