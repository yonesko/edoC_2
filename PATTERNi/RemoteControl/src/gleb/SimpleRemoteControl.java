package gleb;

import gleb.command.Command;

public class SimpleRemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressKeyOn() {
        command.execute();
    }
}
