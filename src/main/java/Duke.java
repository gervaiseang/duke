import java.io.*;
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
        System.out.println(logo + "\nHello! I'm Duke \n" + "What can I do for you?");
        echoMessage();
    }
    private static void echoMessage() {
            boolean quit = false;
            final String LIST = "list";
            final String BYE = "bye";
            List<String> bookList = new ArrayList<>();
            while(!quit) {
                Scanner readInput = new Scanner(System.in);
                String command = readInput.nextLine();  // Read user input

                switch (command) {
                    case LIST:
                        if(!bookList.isEmpty())  {
                            for(int i=1; i<=bookList.size(); i++) {
                                System.out.println(i + ". " + bookList.get(i-1));
                            }
                        }
                        break;
                    case BYE:
                        System.out.println(" Bye. Hope to see you again soon!");
                        quit = true;
                        break;
                    default:
                        bookList.add(command);
                        System.out.println("added: " + command);
                }

            }

    }

}
