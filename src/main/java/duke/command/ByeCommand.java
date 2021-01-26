package duke.command;

import duke.Com;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String arguments) {
        super(arguments);
        this.isExit = true;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.print("I hope I have been of assistance. Goodbye. C:");
    }
}
