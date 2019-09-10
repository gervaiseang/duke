import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> bookList = new ArrayList<>();

    public static void main(String[] args) {
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
                break;
            } else {
                String[] parts = command.split(" "); //Here the string it is divide taking as reference the space
                String cmd = parts[0];
                if (cmd.equals("done")){
                        markDone(parts[1]);
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

}
