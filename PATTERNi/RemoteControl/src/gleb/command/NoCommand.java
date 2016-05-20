package gleb.command;

import gleb.Util;

public class NoCommand implements Command {
    @Override
    public void execute() {
        Util.log();
    }

    @Override
    public void undo() {
        Util.log();
    }
}
