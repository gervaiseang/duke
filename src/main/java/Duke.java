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
    private static void echoMessage() throws IOException{

        boolean quit = false;
        final String BYE = "bye";
        List<String> bookList = new ArrayList<>();
        while(!quit) {
            Scanner readInput = new Scanner(System.in);
            String command = readInput.nextLine();  // Read user input

            switch (command) {
                case BYE:
                    System.out.println(" Bye. Hope to see you again soon!");
                    quit = true;
                    break;
                default:
                    System.out.println(command);
            }

        }
    }
}
