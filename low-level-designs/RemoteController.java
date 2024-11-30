import java.util.Date;
import java.util.List;

class Command {
    Date datetime;
    String details;
    Command(Date datetime, String details) {
        this.datetime = datetime;
        this.details = details;
    }

    void display () {
        System.out.println("details are " + datetime + " " + details);
    }
}


class PowerSource {
    String company;
    double power;
    double voltage;
    double batteryLife;


    public PowerSource(String company, double power, double voltage, double batteryLife) {
        this.company = company;
        this.power = power;
        this.voltage = voltage;
        this.batteryLife = batteryLife;
    }
}

class IRSensor {
    double range;
    String type;

    void sendCommand(String details) {
        new Command(new Date(), details).display();
    }

}

abstract class RemoteButton {
    String color;
    int number;
    String text;
    abstract void onClick(IRSensor sensor);

}

class PowerButton extends RemoteButton {
    PowerButton(String color, int number, String text) {
        this.color = color;
        this.number = number;
        this.text = text;
    }

    @Override
    public void onClick(IRSensor sensor) {
        sensor.sendCommand("command from power button");
    }
}

class ChannelButton extends RemoteButton {
    ChannelButton(String color, int number, String text) {
        this.color = color;
        this.number = number;
        this.text = text;
    }

    @Override
    public void onClick(IRSensor sensor) {

    }
}


class RemoteController {
    List<RemoteButton> buttons;
    IRSensor sensor;
    PowerSource powerSource;

    RemoteController(RemoteControllerBuilder builder) {
        this.buttons = builder.buttons;
        this.sensor = builder.sensor;
        this.powerSource = builder.powerSource;
    }
    static class RemoteControllerBuilder {
        List<RemoteButton> buttons;
        IRSensor sensor;
        PowerSource powerSource;
        void mountButtons(List<RemoteButton> buttons) {
            this.buttons = buttons;
        }
        void addSensor(IRSensor sensor) {
            this.sensor = sensor;
        }
        void addPower(PowerSource powerSource) {
            this.powerSource = powerSource;
        }

        public RemoteController build() {
            return new RemoteController(this);
        }
    }

}