package duke.command;

import duke.*;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {

    public TodoCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            if (arguments.isBlank()) {
                throw new DukeException("I apologize, please input description for 'todo'.");
            } else {
                Task newTask = new Todo(arguments);
                taskList.add(newTask);
                storage.addToFile(newTask);
                ui.print("Added to to-do list: \n" + newTask);
            }
        } catch (DukeException e) {
            ui.showError(e);
        }
    }
}