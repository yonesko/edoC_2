package gleb.command;

import gleb.devices.CeilingFan;

public class CeilingFanIncreaseSpeedCommand implements Command {
    CeilingFan ceilingFan;
    CeilingFan.Speed prevSpeed;


    public CeilingFanIncreaseSpeedCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        CeilingFan.Speed curr = ceilingFan.getSpeed();
        prevSpeed = curr;
        ceilingFan.setSpeed(curr.next());
    }

    @Override
    public void undo() {
        ceilingFan.setSpeed(prevSpeed);
    }
}
