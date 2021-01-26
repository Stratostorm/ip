package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        int delIndex = Integer.parseInt(arguments) - 1;
        Task toDelete = taskList.get(Integer.parseInt(arguments) - 1);
        taskList.remove(delIndex);
        storage.deleteFromFile(delIndex);
        ui.print("Affirmative. The following task has been removed: \n" + toDelete);
    }
}
