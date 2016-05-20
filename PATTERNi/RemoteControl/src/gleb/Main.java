package gleb;

import gleb.command.GarageDoorOpenCommand;
import gleb.command.LightOffCommand;
import gleb.command.LightOnCommand;
import gleb.devices.GarageDoor;
import gleb.devices.Light;

public class Main {

    public static void main(String[] args) {
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(0, new LightOnCommand(light), new LightOffCommand(light));

        remoteControl.pressKeyOn(0);
        remoteControl.pressKeyOff(0);

        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOff(1);

    }
}
