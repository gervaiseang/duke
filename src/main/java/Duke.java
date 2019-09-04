import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke ");
        System.out.println("What can I do for you?");

        boolean quit = false;
        final String LIST = "list";
        final String BYE = "bye";
        final String DONE = "done";
        List<Task> bookList = new ArrayList<>();

        while(!quit) {
            int taskNum = 0;
            Scanner readInput = new Scanner(System.in);
            String command = readInput.nextLine();  // Read user input

            if (command.length() > 4) {
                String[] parts = command.split(" "); //Here the string it is divide taking as reference the space
                if(parts[0].equals(DONE)) {
                    command = DONE;
                    try {
                        taskNum = Integer.parseInt(parts[1]);
                    } catch (Exception ex) {
                        System.out.println("Not a valid number!");
                    }
                }
            }


            switch (command) {
                case LIST:
                    if(!bookList.isEmpty())  {
                        for(int i=1; i<=bookList.size(); i++) {
                            System.out.println(i + ". " + "[" + bookList.get(i-1).getStatusIcon() + "]" + bookList.get(i-1).getDescription() );
                        }
                    }
                    break;
                case BYE:
                    System.out.println(" Bye. Hope to see you again soon!");
                    quit = true;
                    break;
                case DONE:
                    if(bookList.size() >= taskNum) {
                        System.out.println("Nice! I've marked this task as done: ");
                        bookList.get(taskNum-1).setDone();
                        System.out.println("[" + bookList.get(taskNum-1).getStatusIcon() + "]" + bookList.get(taskNum-1).getDescription() );
                    } else {
                        System.out.println("Invalid task number!");
                    }

                    break;
                default:
                    Task myTask = new Task(command);
                    bookList.add(myTask);
                    System.out.println("added: " + command);
            }

        }

    }
}
