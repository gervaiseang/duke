import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> bookList = new ArrayList<>();
    enum dateTimeFormat{
        NONE,
        DATE,
        TIME,
        DATE_TIME_FORMAT,
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/main/java/data/DukeTasks.txt");
        bookList = readFile(file);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();  // Read user input

            if (command.equals("list")) {
                try{
                    if (bookList.size() == 0) throw new DukeException("☹ OOPS!!! The description of a list cannot be empty.");
                    System.out.println("Here are the tasks in your list:");
                    if (!bookList.isEmpty()) {
                        for (int i = 1; i <= bookList.size(); i++) {
                            System.out.println(i + ". " + bookList.get(i - 1).toString());
                        }
                    }
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }

            } else if (command.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                saveFile(file);
                break;
            } else {
                String[] parts = command.split(" "); //Here the string it is divide taking as reference the space
                String cmd = parts[0];
                if (cmd.equals("done")){
                    markDone(parts[1]);
                }
                else if (cmd.equals("delete")){
                    deleteTask(parts[1]);
                }
                else if (cmd.equals("find")){
                    String sequence = parts[1];
                    int x = 1;
                    if (!bookList.isEmpty()) {
                        for (int i = 0; i < bookList.size(); i++) {
                            if(bookList.get(i).description.contains(sequence)) {
                                System.out.println(x + ". " + bookList.get(i).toString());
                                x++;
                            }
                        }
                    }
                }
                else{
                    addTaskList(command);
                }
            }
        }
    }

    private static void addTaskList(String command) {
        String[] parts = command.split(" ");
        String task = parts[0];
        String toReplace = task + " ";
        String nextCommand = command.replaceAll(toReplace, "");

        if (task.equals("todo")) {
            try{
                if (parts.length == 1) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                Todo tempTodo = new Todo(nextCommand);
                bookList.add(tempTodo);
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + tempTodo.toString());
                System.out.println("Now you have " + bookList.size() + " tasks in the list.");
            }
            catch (DukeException e){
                System.out.println(e.getMessage());
            }

        } else if (task.equals("event")) {
            try{
                if (parts.length == 1) throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                String[] tempEvent = nextCommand.split(" /at ");
                Event event = new Event(tempEvent[0], tempEvent[1]);
                bookList.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + event.toString());
                System.out.println("Now you have " + bookList.size() + " tasks in the list.");
            }
            catch (DukeException e){
                System.out.println(e.getMessage());
            }
        } else if (task.equals("deadline")) {
            try {
                if (parts.length == 1)
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                String[] tempDeadline = nextCommand.split(" /by ");
                if (!isValidDateTime(tempDeadline[1])) throw  new DukeException("Please enter correct date time format: dd/mm/yyyy hhmm");
                Deadline deadline = new Deadline(tempDeadline[0], tempDeadline[1]);
                bookList.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + deadline.toString());
                System.out.println("Now you have " + bookList.size() + " tasks in the list.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static void markDone(String taskNumber) {
        try {
            int taskNum = Integer.parseInt(taskNumber);
            if (taskNum == 0)
                throw new DukeException("☹ OOPS!!! Invalid input, please enter a number greater than 0.");
            Task mark = bookList.get(taskNum - 1);
            mark.setDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[" + mark.getStatusIcon() + "] " + mark.description);
        }
        catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Invalid input, please enter an integer.");
        }
    }

    private static void deleteTask(String taskNumber) {
        int index = Integer.parseInt(taskNumber) - 1;
        Task deleteTask = bookList.get(index);
        bookList.remove(deleteTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deleteTask.toString());
        System.out.println("Now you have " + bookList.size() + " tasks in the list.");
    }

    private static ArrayList<Task> readFile(File fr) throws FileNotFoundException {

        ArrayList<Task> arrayList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(fr);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" \\| ");

                if (words[0].equals("T")) {
                    Todo readT = new Todo(words[2]);
                    if (words[1].equals("1")) {
                        readT.isDone = true;
                    }
                    arrayList.add(readT);
                } else if (words[0].equals("D")) {
                    Deadline readD = new Deadline(words[2], words[3]);
                    if (words[1].equals("1")) {
                        readD.isDone = true;
                    }
                    arrayList.add(readD);
                } else { //event
                    Event readE = new Event(words[2], words[3]);
                    if (words[1].equals("1")) {
                        readE.isDone = true;
                    }
                    arrayList.add(readE);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return arrayList;
    }

    private static void saveFile(File file){
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < bookList.size(); i++) {
                fw.append(bookList.get(i) + System.lineSeparator());
            }
            fw.flush();
            fw.close();
        } catch (IOException IOE) {
            System.out.println("Something went wrong " + IOE.getMessage());
        }
    }

    private static boolean isValidDateTime (String dateTime){
        SimpleDateFormat dateOnlyFormat =  new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeOnlyFormat =  new SimpleDateFormat("HHmm");
        SimpleDateFormat dateHourFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        try {
            //dateOnlyFormat.parse(dateTime);
            //timeOnlyFormat.parse(dateTime);
            dateHourFormat.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

}