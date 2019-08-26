import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args){


        /*

        Todo: Create FileReaders and datastructures and write a comparator
         */
        Map<String, List<String>> cols = new HashMap<String, List<String>>();

        List<String> colsnames = new ArrayList<String>();

        BufferedReader br = null;

        try{
            String currentLine;
            String file = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\node.csv";
            br = new BufferedReader(new FileReader(file));
            //why is it throwing a file not found exception...

            
        }




}}
