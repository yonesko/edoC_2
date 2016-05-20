package gleb;

import gleb.command.CeilingFanDecreaseSpeedCommand;
import gleb.command.CeilingFanIncreaseSpeedCommand;
import gleb.command.LightOffCommand;
import gleb.command.LightOnCommand;
import gleb.devices.CeilingFan;
import gleb.devices.GarageDoor;
import gleb.devices.Light;

public class Main {

    public static void main(String[] args) {
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        CeilingFan ceilingFan = new CeilingFan("Guest room");

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(0, new LightOnCommand(light), new LightOffCommand(light));
        remoteControl.setCommand(1, new CeilingFanIncreaseSpeedCommand(ceilingFan), new CeilingFanDecreaseSpeedCommand(ceilingFan));

        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOn(1);
        remoteControl.pressKeyOff(1);
        remoteControl.pressKeyOff(1);
        remoteControl.pressUndo();


    }
}
