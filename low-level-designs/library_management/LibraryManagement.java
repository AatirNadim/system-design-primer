package library_management;


import java.util.Date;
import java.util.List;

class Person {
    String name;
    int age;
    String phoneNo;

    public Person(String name, int age, String phoneNo) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
    }
}

enum LibrarianType {
    ISSUANCE, PAYMENT, MAINTAINANCE;
}


// leave the structural details for now and only focus on the entities that occupy the structures

class Book {

}

class Inventory {
    Book book;
    int number;
}

enum OrderStatus {
    SUBMITTED, IN_PROGRESS, COMPLETED, CANCELLED;
}

class Order {
    List<Inventory> inventoryList;
    Date orderDate;
    Date deliverDate;
    OrderStatus orderStatus;

    public Order(List<Inventory> inventoryList, Date orderDate, Date deliverDate) {
        this.inventoryList = inventoryList;
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
        this.orderStatus = OrderStatus.SUBMITTED;
    }
}




class Librarian extends Person {
    LibrarianType type;
    Librarian(String name, int age, String phoneNo, LibrarianType type) {
        super(name, age, phoneNo);
        this.type = type;
    }


}

class Customer extends Person {
    Customer(String name, int age, String phoneNo) {
        super(name, age, phoneNo);
    }
}

class Library {

}