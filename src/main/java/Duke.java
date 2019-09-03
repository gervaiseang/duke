import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    private static void echoMessage() throws IOException {

        try {
            String line;
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(System.in));
            line = br.readLine();

            String input = "bye";
            if (line.equals(input))
                System.out.println("Bye. Hope to see you again soon!");
            else{
                System.out.println(line);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
