import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){


        /*

        Todo: Create FileReaders and datastructures and write a comparator 
         */


        File f = new File("C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\destination\\test");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
        ArrayList<String> fileStringNames = new ArrayList<String>(Arrays.asList(f.list()));




}}
