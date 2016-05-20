package gleb.command;

import gleb.devices.CeilingFan;

public class CeilingFanDecreaseSpeedCommand implements Command {
    CeilingFan ceilingFan;
    CeilingFan.Speed prevSpeed;


    public CeilingFanDecreaseSpeedCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        CeilingFan.Speed curr = ceilingFan.getSpeed();
        prevSpeed = curr;
        ceilingFan.setSpeed(curr.prev());
    }

    @Override
    public void undo() {
        ceilingFan.setSpeed(prevSpeed);
    }
}
