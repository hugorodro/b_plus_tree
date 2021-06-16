import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class bplustree {
    public static tree mytree;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Expecting only one argument.");
            System.exit(0);
        }

        String inputFile = args[0];
        ArrayList<String> commands = new ArrayList<String>();
        commands = readInput(inputFile);

        for (int i = 0; i < commands.size(); i++) {

            // if (i > 6) {
            // // printLevelOrder();
            // System.out.println("Step:" + (i));
            // System.exit(0);
            // }

            String command = commands.get(i).toLowerCase().replaceAll("\\s", "");
            command = command.replaceAll("[)()]", ",");
            String[] commandArr = command.split(",");

            String action = commandArr[0];
            int val1 = Integer.parseInt(commandArr[1]);

            if (i == 0 && action.equals("initialize") == false) {
                System.out.println("Error: Order must be initialized first.");
                System.exit(0);
            }

            if (commandArr.length == 2) {
                if (action.equals("initialize")) {
                    mytree = new tree(val1);

                } else if (action.equals("delete")) {
                    // delete(val1);

                } else if (action.equals("search")) {
                    writeSearchResults(mytree.search(val1, val1));
                }

                // System.out.println(action + " " + Integer.toString(val1));

            } else if (commandArr.length == 3) {

                if (action.equals("insert")) {
                    float val2 = Float.parseFloat(commandArr[2]);
                    mytree.insertVal(val1, val2);
                    // insert(val1, val2);

                } else if (action.equals("search")) {
                    int val2 = Integer.parseInt(commandArr[2]);

                    writeSearchResults(mytree.search(val1, val2));
                }

                // System.out.println(action + " " + Integer.toString(val1) + " " +
                // Float.toString(val2));

            } else {
                System.out.println("Error with input.txt");
            }
        }

    }

    // Write File
    public static void writeSearchResults(String results) {
        
        // File output = new File("c:/output.txt");
        // boolean exists = output.exists();

        try {
            FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bwriter = new BufferedWriter(writer);
            bwriter.write(results);
            bwriter.newLine();
            bwriter.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Read File
    public static ArrayList<String> readInput(String filename) {

        ArrayList<String> commands = new ArrayList<String>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                commands.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return commands;
    }
}
