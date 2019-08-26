import models.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {


        String filepath = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\node.csv";






        Map<String,String> mapper = getMapFromNodeCSV(filepath);
        //mapper.keySet().stream().
        //        forEach(System.out::println);


        mapper.values().stream()
                .forEach(System.out::println);




    }

    /* Just a test array so I can remember how to interact with a static method returning an arraylist --- disregard */
        public static ArrayList<Integer> listTest(int num1, int num2){
            ArrayList<Integer> arrayList1 = new ArrayList<>();
            arrayList1.add(num1);
            arrayList1.add(num2);
            return arrayList1;
        }


        public static ArrayList<Integer> lambdaTest(int num1,int num2) {
            ArrayList<Integer> arrayList  = new ArrayList<Integer>();
            arrayList.add(num1);
            arrayList.add(num2);
            //List<Integer> odd = arrayList.stream().map(s -> Integer.valueOf(s))
                    //.filter(number -)
            return arrayList;
        }


    /*Create Node.CSV File
    * Issue is with the .collect method only picking up a single column
    *
    * */
        public static Map<String,String> getMapFromNodeCSV(final String filepath)throws IOException {

            Stream<String> lines = Files.lines(Paths.get(filepath));
            Map<String,String> resultMap = lines.map(line -> line.split(","))
                    .collect(Collectors.toMap(line -> line[0], line -> line[3]));
            lines.close();
            return resultMap;
        }

        /*
        public static Map<String,String> getMapFromObjectsCSV(final String objectsFilePath) throws IOException {

            return

        }

*/
}

/*Create a method to read the objects csv ... WAY more complicated logic -.-*/



