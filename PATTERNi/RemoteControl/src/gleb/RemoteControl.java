package gleb;

import gleb.command.Command;
import gleb.command.NoCommand;

import java.util.Arrays;

public class RemoteControl {
    private static final int BUTT_NO = 7;
    private Command lastCmd;
    private Command onCommands[];
    private Command offCommands[];

    public RemoteControl() {
        onCommands = new Command[BUTT_NO];
        offCommands = new Command[BUTT_NO];
        Arrays.fill(onCommands, new NoCommand());
        Arrays.fill(offCommands, new NoCommand());
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressKeyOn(int i) {
        onCommands[i].execute();
        lastCmd = onCommands[i];
    }

    public void pressKeyOff(int i) {
        offCommands[i].execute();
        lastCmd = offCommands[i];
    }

    public void pressUndo() {
        lastCmd.undo();
    }
}
