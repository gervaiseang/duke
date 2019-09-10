import java.io.IOException;
import java.util.ArrayList;
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
                System.out.println("Here are the tasks in your list:");
                if (!bookList.isEmpty()) {
                    for (int i = 1; i <= bookList.size(); i++) {
                        System.out.println(i + ". " + bookList.get(i - 1).toString());
                    }
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
            Todo tempTodo = new Todo(nextCommand);
            bookList.add(tempTodo);
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + tempTodo.toString());
            System.out.println("Now you have " + bookList.size() + " tasks in the list.");
        } else if (task.equals("event")) {
            String[] tempEvent = nextCommand.split(" /at ");
            Event event = new Event(tempEvent[0], tempEvent[1]);
            bookList.add(event);
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + event.toString());
            System.out.println("Now you have " + bookList.size() + " tasks in the list.");
        } else if (task.equals("deadline")) {
            String[] tempDeadline = nextCommand.split(" /by ");
            Deadline deadline = new Deadline(tempDeadline[0], tempDeadline[1]);
            bookList.add(deadline);
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + deadline.toString());
            System.out.println("Now you have " + bookList.size() + " tasks in the list.");
        }
    }

    private static void markDone(String taskNumber) {
        int taskNum = Integer.parseInt(taskNumber);
        Task mark = bookList.get(taskNum - 1);
        mark.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + mark.getStatusIcon() + "] " + mark.description);

    }

}
