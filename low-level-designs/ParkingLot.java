// ******************** VEHICLES ********************

import java.util.Date;
import java.util.List;


enum VehicleType {
    HEAVY, CAR, TWOWHEELER;
}

class Vehicle {
    String noPlate = "";
    String name = "";
    String company = "";
    VehicleType vehicleType;
}


//class Heavy extends Vehicle {
//    Heavy(String noPlate, String name, String company) {
//        this.noPlate = noPlate;
//        this.name =  name;
//        this.company = company;
//    }
//}
//
//class Car extends Vehicle {
//    Car(String noPlate, String name, String company) {
//        this.noPlate = noPlate;
//        this.name =  name;
//        this.company = company;
//    }
//}
//
//class TwoWheeler extends Vehicle {
//    TwoWheeler(String noPlate, String name, String company) {
//        this.noPlate = noPlate;
//        this.name =  name;
//        this.company = company;
//    }
//}

// ******************** TICKET ********************
/*
* there is fixed rate for every vehicle separately, and additional rate per hour
* keep in mind, vehicle types can increase over time
*
*
* */


class Ticket {
    static String details;
    Customer customer;
    Date date;
    ParkingSlot parkingSlot;
    Vehicle vehicle;

    public Ticket(Date date, Customer customer, ParkingSlot parkingSlot, Vehicle vehicle) {
        this.date = date;
        this.customer = customer;
        this.parkingSlot = parkingSlot;
        this.vehicle = vehicle;
    }

    double getFixRate() {
        double fixRate;
        if(vehicle.vehicleType == VehicleType.HEAVY) {
            return 23.4;
        } else if(vehicle.vehicleType == VehicleType.CAR) {
            return 11.1;
        }
        return 9.8;
    }
}




// ******************** USER ********************
/*
* first comes the customer (owner of the vehicle)
* then to keep focused on the mechanism of the parking itself, we consider two parking officials
* PO (A) --> takes in the customer details, books available slot and issues ticket
* PO (B) --> takes in a valid ticket, deallocates the booked parking slot and invalidates the ticket
* Parking company also has to maintain the logs of its own officials
* */

abstract class Person {
    String name = "";
    int age = 0;
    String phoneNo;
}

class Customer extends Person {
    Customer(String name, int age, String phoneNo) {
        this.age = age;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}

class ParkingOfficial extends Person {
    ParkingOfficial(String name, int age, String phoneNo) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
    }

    Ticket issueTicket(Customer customer, Vehicle vehicle) {
        // logic of issuing ticket goes here
        return new Ticket(...ticket_param_details);
    }

    void invalidateTicket(Customer customer, Ticket ticket) {
        // take in the ticket details and invalidate it
    }

}







// ******************** PARKING ********************

enum ParkingDimensions {
    HEAVY(12.3, 4.5),
    CAR(22.3, 34.5),
    TWOWHEELER(2.2, 4.5);
    private double length;
    private double width;

    ParkingDimensions(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}



class ParkingSlot {
    ParkingDimensions parkingDimensions;
    String uid;

    ParkingSlot(ParkingDimensions parkingDimensions, String uid) {
        this.parkingDimensions = parkingDimensions;
        this.uid = uid;
    }
}

class ParkingLevel {
    int level;
    List<ParkingSlot> parkingSlots;

    public ParkingLevel(int level, List<ParkingSlot> parkingSlots) {
        this.level = level;
        this.parkingSlots = parkingSlots;
    }
}

class ParkingBuilding {

}
