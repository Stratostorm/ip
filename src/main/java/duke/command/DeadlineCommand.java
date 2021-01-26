package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command{
    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("I apologize, please input description and time for 'deadline'.");
        } else {
            String[] split = arguments.split("/by");
            if (arguments.equals(split[0])) {
                throw new DukeException("I apologize, please use '/by' argument to specify time for 'deadline'.");
            } else {
                Task newTask = new Deadline(split[0].strip(), split[1].strip());
                taskList.add(newTask);
                storage.addToFile(newTask);
                ui.print("Added to to-do list: \n" + newTask);
            }
        }
    }
}
