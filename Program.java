import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    static ArrayList<String> webpages = new ArrayList<>();
    //This is the main program of the project
    public static void runProgram(AVLTree webpageIndex) throws FileNotFoundException {
        //The file and the AVL are first initialized
        webpageIndex = initialize(webpageIndex);
        Scanner console = new Scanner(System.in);
        System.out.println("Files are loaded correctly!");
        //The while loop is used as the main loop of the program
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                    ----------MENU----------
                    1-Display the full index
                    2-Search for a URL
                    3-Search for an IP Address
                    4-Exit
                    ------------------------
                    """);
            System.out.print("Option: ");
            int choice = console.nextInt();
            console.nextLine();
            switch (choice) {
                case 1:
                    //This simply prints the AVL in-order
                    webpageIndex.printInorder(webpageIndex.getRoot());
                    break;
                case 2:
                    //This is used to search the AVL for a certain webpage
                    System.out.print("Please enter the page: ");
                    String URL = console.nextLine();
                    AVLTree.Node target = webpageIndex.find(URL);
                    System.out.println("The List of IPs is: " + target.ips.toString());
                    break;
                case 3:
                    //This is used to search the AVL for a certain IP
                    System.out.print("Please enter the URL: ");
                    String IP=console.nextLine();
                    try {
                        System.out.println("The website Name is: " + webpageIndex.searchForIP(IP).websiteName);
                    }catch(NullPointerException e){
                        System.out.println("not found");
                    }
                    break;

                case 4:
                    //This is used to simply close the loop and the program
                    isRunning = false;
                    break;

                default:
                    System.out.println("Please choose a correct option!");
                    break;

            }
        }


    }

    private static AVLTree initialize(AVLTree webpageIndex) throws FileNotFoundException {
        try {
            //creating a file object to use in the scanner object which will read from the websites.txt file
            Scanner websites = new Scanner(new File("C:\\Users\\Spectre\\IdeaProjects\\Project2Final\\src\\websites.txt"));
            //This loop adds all the lines in the file to a temporary ArrayList
            while (websites.hasNextLine()) {
                webpages.add(websites.nextLine());
            }
            websites.close();
        } catch (FileNotFoundException e) {
            //In case of the file not being existant, the catch reads the file and doesnt allow the program to run
            System.out.println("An error occurred reading the file!");
            return null;
        }
        //This loop adds all the contents of the ArrayList into the AVL Tree
        for (int i = 0; i < webpages.size(); i++) {
            String[] insertedData = webpages.get(i).split(">");
            String nodeName = insertedData[0].trim().split("\\.")[0];
            String nodeIp = insertedData[1].trim();
            AVLTree.Node isExistNode = webpageIndex.find(nodeName);
            if (isExistNode != null) {
                isExistNode.ips.addNode(nodeIp);
            } else {
                webpageIndex.insert(nodeName, nodeIp);
            }
        }
        return webpageIndex;
    }
}