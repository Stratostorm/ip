import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DukeFile {
    public static void addToFile(Task task) {
        File save = new File("./data/duke.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(save, true);
            fw.write(task.fileString() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromFile(int index) {
        File save = new File("./data/duke.txt");
        File temp = new File("./data/temp.txt");
        int ctr = 0;
        try {
            //create temp file
            temp.createNewFile();
            Scanner sc = new Scanner(save);
            FileWriter fw = new FileWriter(temp);
            //copy all lines except line to be deleted to temp
            while(sc.hasNextLine()) {
                //skip the line to be deleted
                if (ctr != index) {
                    fw.write(sc.nextLine() + System.lineSeparator());
                } else {
                    sc.nextLine();
                }
                ctr++;
            }
            fw.close();
            sc.close();
            //delete original save and rename temp to save
            save.delete();
            temp.renameTo(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void markDoneInFile(int index) {
        File save = new File("./data/duke.txt");
        File temp = new File("./data/temp.txt");
        int ctr = 0;
        try {
            //create temp file
            temp.createNewFile();
            Scanner sc = new Scanner(save);
            FileWriter fw = new FileWriter(temp);
            //copy all lines except line to be edited to temp
            while(sc.hasNextLine()) {
                //change line to be edited
                if (ctr != index) {
                    fw.write(sc.nextLine() + System.lineSeparator());
                } else {
                    sc.nextLine();
                }
                ctr++;
            }
            fw.close();
            sc.close();
            //delete original save and rename temp to save
            save.delete();
            temp.renameTo(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(List list) throws IOException {
        File directory = new File("data");
        File save = new File("./data/duke.txt");
        directory.mkdir();
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
            list.add(task);
        }
        sc.close();
    }
}
