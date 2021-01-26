import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    private static ArrayList<Task> todoList;
    private static void addToFile(Task task) {
        File save = new File("./data/duke.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(save, true);
            fw.write(task.fileString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void deleteFromFile(int index) {
        File save = new File("./data/duke.txt");
    }
    private static void loadData() throws IOException {
        File save = new File("./data/duke.txt");
        save.createNewFile();
        Scanner sc = new Scanner(save);
        while (sc.hasNext()) {
            String taskString = sc.nextLine();
            String[] taskArgsArray = taskString.split(" [|] ");
            Task task;
            if (taskArgsArray[0].equals("T")) {
                task = new Todo(Boolean.parseBoolean(taskArgsArray[1]), taskArgsArray[2]);
            } else if (taskArgsArray[0].equals("D")) {
                task = new Deadline(Boolean.parseBoolean(taskArgsArray[1]), taskArgsArray[2], taskArgsArray[3]);
            } else {
                task = new Event(Boolean.parseBoolean(taskArgsArray[1]), taskArgsArray[2], taskArgsArray[3]);
            }
            todoList.add(task);
        }
        sc.close();
    }
    private static void processCommand(String command) {
        String strippedCommand = command.strip();
        if (strippedCommand.equals("list")) {
            System.out.println("This is your to-do list:");
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println((i+1) + ". " + todoList.get(i));
            }
        } else if (strippedCommand.startsWith("done")) {
            String indexString = strippedCommand.substring(4).strip();
            int index = Integer.parseInt(indexString) - 1;
            Task toDo = todoList.get(index);
            toDo.doTask();
            System.out.println("Affirmative. The following task has been marked as done: \n" + toDo);
        } else if (strippedCommand.startsWith("delete")) {
            String indexString = strippedCommand.substring(6).strip();
            int index = Integer.parseInt(indexString) - 1;
            Task toDo = todoList.get(index);
            todoList.remove(index);
            System.out.println("Affirmative. The following task has been removed: \n" + toDo);
        } else if (strippedCommand.startsWith("todo")) {
            String cmd = strippedCommand.substring(4).strip();
            if (cmd.isBlank()) {
                String msg = "I apologize, please input description for 'todo'.";
                DukeException exception = new DukeException(msg);
                System.out.println(exception);
            } else {
                Task newTask = new Todo(cmd);
                todoList.add(newTask);
                addToFile(newTask);
                System.out.println("Added to to-do list: \n" + newTask);
            }
        } else if (strippedCommand.startsWith("deadline")) {
            String cmd = strippedCommand.substring(8).strip();
            if (cmd.isBlank()) {
                String msg = "I apologize, please input description and time for 'deadline'.";
                DukeException exception = new DukeException(msg);
                System.out.println(exception);
            } else {
                String[] split = cmd.split("/by");
                if (cmd.equals(split[0])) {
                    String msg = "I apologize, please use '/by' argument to specify time for 'deadline'.";
                    DukeException exception = new DukeException(msg);
                    System.out.println(exception);
                } else {
                    Task newTask = new Deadline(split[0].strip(), split[1].strip());
                    todoList.add(newTask);
                    addToFile(newTask);
                    System.out.println("Added to to-do list: \n" + newTask);
                }
            }
        } else if (strippedCommand.startsWith("event")) {
            String cmd = strippedCommand.substring(5).strip();
            if (cmd.isBlank()) {
                String msg = "I apologize, please input description and time for 'event'.";
                DukeException exception = new DukeException(msg);
                System.out.println(exception);
            } else {
                String[] split = cmd.split("/at");
                if (cmd.equals(split[0])) {
                    String msg = "I apologize, please use '/at' argument to specify time for 'event'.";
                    DukeException exception = new DukeException(msg);
                    System.out.println(exception);
                } else {
                    Task newTask = new Event(split[0].strip(), split[1].strip());
                    todoList.add(newTask);
                    addToFile(newTask);
                    System.out.println("Added to to-do list: \n" + newTask);
                }
            }
        } else {
            String msg = "I apologize, I do not comprehend your command.";
            DukeException exception = new DukeException(msg);
            System.out.println(exception);
        }
    }

    public static void main(String[] args) {
        todoList = new ArrayList<>();
        Scanner sc =  new Scanner(System.in);
        System.out.println("Greetings. My name is I-01B, but you may call me DUKE.");
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved data successfully loaded. What can I assist you with?");
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("I hope I have been of assistance. Goodbye. C:");
                break;
            } else {
                processCommand(command);
            }
        }
    }
}
