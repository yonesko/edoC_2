package gleb;

import gleb.command.Command;
import gleb.command.GarageDoorOpenCommand;
import gleb.command.LightOnCommand;
import gleb.devices.GarageDoor;
import gleb.devices.Light;

public class Main {

    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();

        simpleRemoteControl.setCommand(new LightOnCommand(new Light()));

        simpleRemoteControl.setCommand(new GarageDoorOpenCommand(new GarageDoor()));

        simpleRemoteControl.pressKeyOn();

    }
}
