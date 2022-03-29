import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //This is simply done to run the project from a separate Program class
        AVLTree webpageIndex = new AVLTree();
        Program.runProgram(webpageIndex);
    }
}
